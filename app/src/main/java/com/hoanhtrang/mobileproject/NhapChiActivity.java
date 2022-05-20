package com.hoanhtrang.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import com.hoanhtrang.mobileproject.fragment.nhapchiFragment;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class NhapChiActivity extends AppCompatActivity {
    EditText txtChonNgay, txtGhiChu, txtSoTien, txtDanhMuc;
    Button btnKhoanChi;
    String _Ngay = "";
    String _GhiChu = "";
    String _SoTien = "";
    String _DanhMuc = "";
    public static Connection con;
    public static ConnectionClass connectionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_chi);

        Intent intent = getIntent();
        String ngay = intent.getStringExtra("date");
        String ghiChu = intent.getStringExtra("note");
        String soTien = intent.getStringExtra("amount");
        String danhMuc = intent.getStringExtra("type");
        txtChonNgay = (EditText) findViewById(R.id.txtChonNgay);
        txtGhiChu = (EditText) findViewById(R.id.txtGhiChu);
        txtSoTien = (EditText) findViewById(R.id.txtSoTien);
        txtDanhMuc = (EditText) findViewById(R.id.txtDanhMuc);
        btnKhoanChi = (Button) findViewById(R.id.btnKhoanChi);


        if(ListNhapChiActivity._Sua.equals("S")) {
//            txtChonNgay.setText(ListNhapChiActivity._Ngay);
//            txtGhiChu.setText(ListNhapChiActivity._GhiChu);
//            txtSoTien.setText(ListNhapChiActivity._SoTien);
//            txtDanhMuc.setText(ListNhapChiActivity._DanhMuc);
            txtChonNgay.setText(ngay);
            txtGhiChu.setText(ghiChu);
            txtSoTien.setText(soTien);
            txtDanhMuc.setText(danhMuc);
        }

        btnKhoanChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ListNhapChiActivity._Sua.equals("M")) {
                    try {
                        _Ngay = txtChonNgay.getText().toString();
                        _GhiChu = txtGhiChu.getText().toString();
                        _SoTien = txtSoTien.getText().toString();
                        _DanhMuc = txtDanhMuc.getText().toString();
                        CallableStatement stmThem = ListNhapChiActivity.con.prepareCall("{call NhapChi_get(?,?,?,?,?)}");
                        stmThem.setString(1, _Ngay);
                        stmThem.setString(2, _GhiChu);
                        stmThem.setString(3, _SoTien);
                        stmThem.setString(4, _DanhMuc);
                        stmThem.setString(5, ListNhapChiActivity._Sua);
                        stmThem.executeQuery();

                    } catch (SQLException throwables) {
//                    throwables.printStackTrace();
                    }
                    ListNhapChiActivity.listNhapChi.add(new NhapChi(_Ngay, _GhiChu,_SoTien,_DanhMuc));
                }
//                if(ListNhapChiActivity._Sua.equals("M")){
//
 //
//                }
                else {
                    try {
                        CallableStatement stmThem = ListNhapChiActivity.con.prepareCall("{call NhapChi_get(?,?,?,?,?)}");
                        stmThem.setString(1, txtChonNgay.getText().toString());
                        stmThem.setString(2, txtGhiChu.getText().toString());
                        stmThem.setString(3, txtSoTien.getText().toString());
                        stmThem.setString(4, txtDanhMuc.getText().toString());
                        stmThem.setString(5, ListNhapChiActivity._Sua);
                        stmThem.executeQuery();
                    }catch (SQLException throwables) {
//                    throwables.printStackTrace();
                        }
                    ListNhapChiActivity.listNhapChi.add(new NhapChi(_Ngay, _GhiChu,_SoTien,_DanhMuc));
                }

                ListNhapChiActivity.adapter.notifyDataSetChanged();
                finish();
            }
        });
    }

}

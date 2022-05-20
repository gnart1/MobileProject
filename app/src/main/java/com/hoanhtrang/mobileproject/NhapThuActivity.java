package com.hoanhtrang.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class NhapThuActivity extends AppCompatActivity {
    EditText txtChonNgay, txtGhiChu, txtSoTien, txtDanhMuc;
    Button btnKhoanThu;
    String _Ngay = "";
    String _GhiChu = "";
    String _SoTien = "";
    String _DanhMuc = "";
    public static Connection con;
    public static ConnectionClass connectionClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_thu);
        txtChonNgay = (EditText) findViewById(R.id.txtChonNgay);
        txtGhiChu = (EditText) findViewById(R.id.txtGhiChu);
        txtSoTien = (EditText) findViewById(R.id.txtSoTien);
        txtDanhMuc = (EditText) findViewById(R.id.txtDanhMuc);
        btnKhoanThu = (Button) findViewById(R.id.btnKhoanThu);


        if(ListNhapThuActivity._Sua.equals("S")) {
            txtChonNgay.setText(ListNhapThuActivity._Ngay);
            txtGhiChu.setText(ListNhapThuActivity._GhiChu);
            txtSoTien.setText(ListNhapThuActivity._SoTien);
            txtDanhMuc.setText(ListNhapThuActivity._DanhMuc);
        }

        btnKhoanThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    _Ngay  = txtChonNgay.getText().toString();
                    _GhiChu  = txtGhiChu.getText().toString();
                    _SoTien  = txtSoTien.getText().toString();
                    _DanhMuc  = txtDanhMuc.getText().toString();

                    CallableStatement stmThem = ListNhapThuActivity.con.prepareCall("{call NhapThu_get(?,?,?,?,?)}");
                    stmThem.setString(1, _Ngay);
                    stmThem.setString(2, _GhiChu);
                    stmThem.setString(3, _SoTien);
                    stmThem.setString(4, _DanhMuc);
                    stmThem.setString(5, ListNhapThuActivity._Sua);
                    stmThem.executeQuery();

                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
                }

                if(ListNhapThuActivity._Sua.equals("M")){

                    ListNhapThuActivity.listNhapThu.add(new NhapThu(_Ngay, _GhiChu,_SoTien,_DanhMuc));
                }
                else {


                }
                ListNhapThuActivity.adapter.notifyDataSetChanged();
                finish();
            }
        });
    }
}
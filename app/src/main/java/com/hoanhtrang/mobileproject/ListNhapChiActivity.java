package com.hoanhtrang.mobileproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ListNhapChiActivity extends AppCompatActivity {
    public static ListView lstNhapChi;
    public static ArrayList<NhapChi> listNhapChi;
    public static NhapChiAdapter adapter;
    public static ConnectionClass connectionClass;
    public static Connection con;
    public static String _Sua = "M";
    public static String _Ngay = "";
    public static String _GhiChu = "";
    public static String _SoTien = "";
    public static String _DanhMuc = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nhap_chi);

        lstNhapChi = (ListView) findViewById(R.id.lstNhapChi);
        listNhapChi = new ArrayList<>();


        lstNhapChi.setAdapter(adapter);
        LoadDanhMuc();

        ImageView imgAdd = (ImageView) findViewById(R.id.imgAdd);
        ImageView imgEdit = (ImageView) findViewById(R.id.imgEdit);
        ImageView imgDelete = (ImageView) findViewById(R.id.imgDelete);


        if (ListNhapChiActivity.listNhapChi != null){
//            for (int i=0; i<ListNhapChiActivity.listNhapChi.size(); i++){
//              Toast.makeText(ListNhapChiActivity.this, ListNhapChiActivity.listNhapChi.get(i).getDanhmuc().toString(), Toast.LENGTH_LONG).show();
//            }
        }else {
            ListNhapChiActivity.listNhapChi = new ArrayList<>();
        }

        adapter = new NhapChiAdapter(this, R.layout.activity_nhapchi_chitiet, listNhapChi);
        lstNhapChi.setAdapter(adapter);
        lstNhapChi.deferNotifyDataSetChanged();

        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _Sua = "M";
                Intent Chuyen = new Intent(ListNhapChiActivity.this,NhapChiActivity.class);
                startActivity(Chuyen);
            }
        });


//        imgDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }


    private void LoadDanhMuc() {
        listNhapChi.clear();
            //m_nhapthu.clear();

            connectionClass = new ConnectionClass();
            try {
                con = connectionClass.CONN();
                if (con == null){

                }else {
                    ResultSet rsNc;
                    String query = "SELECT * FROM dbo.receive_spend1 WHERE type = 2";
                    Statement stmNc = con.createStatement();
                    rsNc = stmNc.executeQuery(query);
                    while (rsNc.next()){
                        listNhapChi.add(new NhapChi(rsNc.getString("date"),rsNc.getString("note"),
                                rsNc.getString("amount"),rsNc.getString("type")));
                    }
//                    ResultSet rsNt;
//                    String query1 = "SELECT * FROM dbo.receive_spend1 WHERE type = 1";
//                    Statement stmNt = con.createStatement();
//                    rsNt = stmNt.executeQuery(query1);
//                    while (rsNt.next()){
//                        listNhapChi.add(new NhapThu(rsNt.getString("ngay"),rsNt.getString("ghichu"),
//                                rsNt.getString("sotien"),rsNt.getString("danhmuc")));
//                    }
//                ResultSet rsHh;
//                query = "SELECT * FROM DmHh";
//                Statement stmHh = con.createStatement();
//                rsHh = stmHh.executeQuery(query);
//                while (rsHh.next()){
//                    m_dmHh.add(new M_DmHh(rsHh.getString("Ma_Hang"),rsHh.getString("Ten_Hang"),
//                            rsHh.getString("Dvt"),rsHh.getDouble("Gia_Mua"),
//                            rsHh.getDouble("Gia_Ban")));
//                    //Toast.makeText(ManHinh.this, rsHh.getString("Ma_Hang"), Toast.LENGTH_LONG).show();
//                }
                }

            }catch (Exception se){

            }
    }
}
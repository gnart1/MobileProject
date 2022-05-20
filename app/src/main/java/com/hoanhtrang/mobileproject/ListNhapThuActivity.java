package com.hoanhtrang.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ListNhapThuActivity extends AppCompatActivity {
    ListView lstNhapThu;
    public static ArrayList<NhapThu> listNhapThu;
    public static NhapThuAdapter adapter;
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
        setContentView(R.layout.activity_list_nhap_thu);


        lstNhapThu = (ListView) findViewById(R.id.lstNhapThu);
        listNhapThu = new ArrayList<>();


        lstNhapThu.setAdapter(adapter);
        LoadDanhMuc();
        ImageView imgAdd = (ImageView) findViewById(R.id.imgAdd);
        ImageView imgEdit = (ImageView) findViewById(R.id.imgEdit);
        ImageView imgDelete = (ImageView) findViewById(R.id.imgDelete);
        if (ListNhapChiActivity.listNhapChi != null){

        }else {
            ListNhapChiActivity.listNhapChi = new ArrayList<>();
        }

        adapter = new NhapThuAdapter(this, R.layout.activity_nhapthu_chitiet, listNhapThu);
        lstNhapThu.setAdapter(adapter);
        lstNhapThu.deferNotifyDataSetChanged();

        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _Sua = "M";
                Intent Chuyen = new Intent(ListNhapThuActivity.this,NhapThuActivity.class);
                startActivity(Chuyen);
            }
        });
//        if (HomeActivity.m_nhapthu != null){
//            //for (int i=0; i<ManHinh.m_dmKh.size(); i++){
//            //  Toast.makeText(DmKh.this, ManHinh.m_dmKh.get(i).getMa_Kh().toString(), Toast.LENGTH_LONG).show();
//            //}
//        }else {
//            HomeActivity.m_nhapthu = new ArrayList<>();
//        }
//        adapter = new NhapThuAdapter(ListNhapThuActivity.this, HomeActivity.m_nhapchi);
//        lstNhapThu.setAdapter(adapter);
//        lstNhapThu.deferNotifyDataSetChanged();
//
//        imgAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                _Sua = "M";
//                Intent Chuyen = new Intent(ListNhapThuActivity.this,NhapThuActivity.class);
//                startActivity(Chuyen);
//            }
//        });
//        imgEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                _Sua = "S";
//                Intent Chuyen = new Intent(ListNhapThuActivity.this,NhapThuActivity.class);
//                startActivity(Chuyen);
//            }
//        });
    }

    private void LoadDanhMuc() {
        listNhapThu.clear();

        connectionClass = new ConnectionClass();
        try {
            con = connectionClass.CONN();
            if (con == null){

            }else {
                ResultSet rsNt;
                String query = "SELECT * FROM dbo.receive_spend1 WHERE type = 1";
                Statement stmNt = con.createStatement();
                rsNt = stmNt.executeQuery(query);
                while (rsNt.next()){
                    listNhapThu.add(new NhapThu(rsNt.getString("date"),rsNt.getString("note"),
                            rsNt.getString("amount"),rsNt.getString("type")));
                }
            }

        }catch (Exception se){

        }
    }
}
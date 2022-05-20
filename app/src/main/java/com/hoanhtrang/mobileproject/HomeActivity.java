package com.hoanhtrang.mobileproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.slider.Slider;
import com.hoanhtrang.mobileproject.fragment.nhapchiFragment;
import com.hoanhtrang.mobileproject.fragment.nhapthuFragment;
import com.hoanhtrang.mobileproject.fragment.thongkeFragment;
import com.hoanhtrang.mobileproject.fragment.thongtincanhanFragment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity  {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    ListView listView;
    public static ConnectionClass connectionClass;
    public static Connection con;
    ImageButton btnAdd;
    public static ArrayList<NhapChi> m_nhapchi;
    public static ArrayList<NhapThu> m_nhapthu;
    public static final int FRAGMENT_NHAPTHU = 0;
    public static final int FRAGMENT_NHAPCHI = 1;
    public static final int FRAGMENT_THONGKE = 2;
    public static final int FRAGMENT_THONGTINCANHAN = 4;
    Context context;
    private int currentFragment = FRAGMENT_NHAPTHU;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        anhxa();
        setSupportActionBar(toolbar);
        m_nhapchi = new ArrayList<>();
        m_nhapthu = new ArrayList<>();
        actionToolbar();
        //bat sk
        LoadDanhMuc();
        //navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.nav_nhapthu,R.string.nav_nhapchi);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_nhapthu:
                        Intent nhapthu = new Intent(HomeActivity.this, ListNhapThuActivity.class);
                        startActivity(nhapthu);
                        //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_nhapchi:
                        Intent nhapchi = new Intent(HomeActivity.this, ListNhapChiActivity.class);
                        startActivity(nhapchi);
                        //Toast.makeText(getApplicationContext(), "nhapchi open", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
//                    case R.id.nav_nhapthu:
//                        Toast.makeText(getApplicationContext(), "nhapthu open", Toast.LENGTH_SHORT).show();
//                        drawerLayout.closeDrawer(GravityCompat.START);
                }
                return true;
            }
        });
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            return false;
//        }
        //check
//        replaceFragment(new ListNhapThuActivity());
//        navigationView.getMenu().findItem(R.id.nav_nhapthu).setChecked(true);
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent it = new Intent(HomeActivity.this, MainActivity.class);
//                startActivity(it);
//            }
//        });
    }

    private void LoadDanhMuc() {
        m_nhapchi.clear();
        m_nhapthu.clear();

        connectionClass = new ConnectionClass();
        try {
            con = connectionClass.CONN();
            if (con == null){

            }else {
                ResultSet rsNc;
                String query = "SELECT * FROM dbo.receive_spend WHERE type = 2";
                Statement stmNc = con.createStatement();
                rsNc = stmNc.executeQuery(query);
                while (rsNc.next()){
                    m_nhapchi.add(new NhapChi(rsNc.getString("ngay"),rsNc.getString("ghichu"),
                            rsNc.getString("sotien"),rsNc.getString("danhmuc")));
                }
                ResultSet rsNt;
                String query1 = "SELECT * FROM dbo.receive_spend WHERE type = 1";
                Statement stmNt = con.createStatement();
                rsNt = stmNt.executeQuery(query1);
                while (rsNt.next()){
                    m_nhapthu.add(new NhapThu(rsNt.getString("ngay"),rsNt.getString("ghichu"),
                            rsNt.getString("sotien"),rsNt.getString("danhmuc")));
                }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nav, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void actionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_action_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void anhxa() {
        toolbar = (Toolbar) findViewById(R.id.Toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);
        navigationView = (NavigationView) findViewById(R.id.NavigationView);
        listView = (ListView) findViewById(R.id.ListMenu);

        //nut add
//        btnAdd = (ImageButton) findViewById(R.id.btnAdd);
    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//
//
//        if (id == R.id.nav_nhapthu){
//            if(currentFragment != FRAGMENT_NHAPTHU){
////                replaceFragment(new nhapthuFragment());
//                Intent it = new Intent(this, ListNhapThuActivity.class);
//                startActivity(it);
//                currentFragment = FRAGMENT_NHAPTHU;
//            }
//        }else if(id == R.id.nav_nhapchi){
//            if(currentFragment != FRAGMENT_NHAPCHI){
//                //replaceFragment(new nhapchiFragment());
//                Intent it = new Intent(this, ListNhapChiActivity.class);
//                startActivity(it);
//                currentFragment = FRAGMENT_NHAPCHI;
//            }
////        }else if(id == R.id.nav_thongke){
////            if(currentFragment != FRAGMENT_THONGKE){
////                replaceFragment(new thongkeFragment());
////                currentFragment = FRAGMENT_THONGKE;
////            }
////        }else if(id == R.id.nav_thongtincanhan){
////            if(currentFragment != FRAGMENT_THONGTINCANHAN){
////                replaceFragment(new thongtincanhanFragment());
////                currentFragment = FRAGMENT_THONGTINCANHAN;
////            }
//        }else if(id == R.id.nav_dangxuat){
//            showLogout();
//
//            return true;
//
//        }
//        drawerLayout.closeDrawer(GravityCompat.START);
//        return true;
    //}



    private void showLogout() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(HomeActivity.this);
        dialog.setTitle("Xác nhận");
        dialog.setMessage("Bạn có muốn đăng xuất ?");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                logout();
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });
        dialog.show();
    }

    private void logout() {
        Intent it = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(it);
        finish();
    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }


//    public void replaceFragment(nhapchiFragment fragment){
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.content, fragment);
//        transaction.commit();
//    } private void replaceFragment(ListNhapThuActivity context) {
//    }
}
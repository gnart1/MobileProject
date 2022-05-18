package com.hoanhtrang.mobileproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.material.navigation.NavigationView;
import com.hoanhtrang.mobileproject.fragment.nhapthuFragment;
import com.hoanhtrang.mobileproject.fragment.nhapchiFragment;
import com.hoanhtrang.mobileproject.fragment.thongkeFragment;
import com.hoanhtrang.mobileproject.fragment.thongtincanhanFragment;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ListView listView;
    ImageButton btnAdd;
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
        actionToolbar();
        //bat sk
        navigationView.setNavigationItemSelectedListener(this);

        //check
        replaceFragment(new nhapthuFragment());
        navigationView.getMenu().findItem(R.id.nav_nhapthu).setChecked(true);

//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent it = new Intent(HomeActivity.this, MainActivity.class);
//                startActivity(it);
//            }
//        });
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.nav_nhapthu){
            if(currentFragment != FRAGMENT_NHAPTHU){
                replaceFragment(new nhapthuFragment());
                currentFragment = FRAGMENT_NHAPTHU;
            }
        }else if(id == R.id.nav_nhapchi){
            if(currentFragment != FRAGMENT_NHAPCHI){
                replaceFragment(new nhapchiFragment());
                currentFragment = FRAGMENT_NHAPCHI;
            }
        }else if(id == R.id.nav_thongke){
            if(currentFragment != FRAGMENT_THONGKE){
                replaceFragment(new thongkeFragment());
                currentFragment = FRAGMENT_THONGKE;
            }
        }else if(id == R.id.nav_thongtincanhan){
            if(currentFragment != FRAGMENT_THONGTINCANHAN){
                replaceFragment(new thongtincanhanFragment());
                currentFragment = FRAGMENT_THONGTINCANHAN;
            }
        }else if(id == R.id.nav_dangxuat){
            showLogout();

            return true;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

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
    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }
}
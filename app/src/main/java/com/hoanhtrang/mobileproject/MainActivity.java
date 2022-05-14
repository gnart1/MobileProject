package com.hoanhtrang.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class MainActivity extends AppCompatActivity {
    public static ConnectionClass connectionClass;
    public static Connection con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectionClass = new ConnectionClass();
        EditText txtName = (EditText) findViewById(R.id.txtUserName);
        EditText txtPassword = (EditText) findViewById(R.id.txtPassword);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        Button btnRegister = (Button) findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (txtName.getText().toString().equals("admin") && txtPassword.getText().toString().equals("123")){
//                    Toast.makeText(MainActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
//                    Intent it = new Intent(MainActivity.this,HomeActivity.class);
//                    startActivity(it);
//                }else{
//                    Toast.makeText(MainActivity.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
//                }
                try {
                    con = connectionClass.CONN();
                    if(con == null){
                        //Toast.makeText(MainActivity.this, "Chưa nhập thông tin", Toast.LENGTH_SHORT).show();
                    }else {
                        ResultSet rs;
                        CallableStatement stm = con.prepareCall("{call CheckUser(?,?)}");
                        stm.setString(1, txtName.getText().toString());
                        stm.setString(2, txtPassword.getText().toString());
                        stm.executeQuery();
                        rs = stm.getResultSet();
//                        if(edtTen.getText().toString().equals("edtTen") && edtMatKhau.getText().toString().equals("edtMatKhau") ){
//
//                        }

                    }

                }catch (Exception ex){

                }
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }

        });

    }
}
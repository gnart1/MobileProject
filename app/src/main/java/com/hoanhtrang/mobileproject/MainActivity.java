package com.hoanhtrang.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.MediaRouteButton;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {
    String _Name = "";
    String _Password = "";
    public static ConnectionClass connectionClass;
    public static Connection con;
    EditText txtName, txtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtName = (EditText) findViewById(R.id.txtUserName);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        Button btnRegister = (Button) findViewById(R.id.btnRegister);
        //progressBar.setVisibility(View.VISIBLE);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(it);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _Name = txtName.getText().toString();
                _Password = txtPassword.getText().toString();
                CheckLogin checkLogin = new CheckLogin();

                checkLogin.execute("");
            }

        });


    }
    public class CheckLogin extends AsyncTask<String, String, String>{
        String z = "";
        Boolean isSuccess = false;

//        @Override
//        protected void onPreExecute(){
//
//            progressBar.setVisibility(View.VISIBLE);
//        }

        @Override
        protected String doInBackground(String... params) {
            connectionClass = new ConnectionClass();
        if (_Name.trim().equals("") && _Password.trim().equals("")){
            z="Vui lòng nhập tài khoản mật khẩu";
        }else{
            try {
                con = connectionClass.CONN();
                    if(con == null){
                       z = "Check connection";
                        //Toast.makeText(MainActivity.this, "Chưa nhập thông tin", Toast.LENGTH_SHORT).show();
                    }else {
                        ResultSet rs;
                        CallableStatement stm = con.prepareCall("{call CheckUser(?,?)}");
                        stm.setString(1, _Name);
                        stm.setString(2, _Password);
                        stm.executeQuery();
                        rs = stm.getResultSet();

                        while (rs.next()){
                            //Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
                            z = "Đăng nhập thành công!";
                            isSuccess = true;
                            //con.close();

                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    //finish();
                         }
                        if (isSuccess = true)
                        {

                        }
                        else{
                            z = "Đăng nhập thất bại!";
                            isSuccess = false;
                        }
                    }
            }catch (Exception ex){
                isSuccess = false;
                z = ex.getMessage();
            }
        }


            return null;
        }
    }
}

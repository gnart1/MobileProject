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
import android.widget.ProgressBar;
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
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtName = (EditText) findViewById(R.id.txtUserName);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        Button btnRegister = (Button) findViewById(R.id.btnRegister);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);
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

        @Override
        protected void onPreExecute(){

            progressBar.setVisibility(View.VISIBLE);
        }
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
                    }else {
                        ResultSet rs;
                        CallableStatement stm = con.prepareCall("{call CheckUser(?,?)}");
                        stm.setString(1, _Name);
                        stm.setString(2, _Password);
                        stm.executeQuery();
                        rs = stm.getResultSet();

                        if(rs.next()){

                            z = "Đăng nhập thành công!";
                            isSuccess = true;
                            //con.close();

                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    //finish();
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


            return z;
        }
        @Override
        protected void onPostExecute(String r){
            progressBar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, r, Toast.LENGTH_SHORT).show();
            if (isSuccess){
                Toast.makeText(MainActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

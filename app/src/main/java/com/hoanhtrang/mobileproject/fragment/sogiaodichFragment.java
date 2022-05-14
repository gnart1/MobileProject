package com.hoanhtrang.mobileproject.fragment;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hoanhtrang.mobileproject.HomeActivity;
import com.hoanhtrang.mobileproject.R;
import com.hoanhtrang.mobileproject.sogiaodich.AddSoGiaoDichActivity;

public class sogiaodichFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sogiaodich, container, false);
//        ImageButton btnAdd = (ImageButton) findViewById(R.id.Toolbar);
//                btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent it = new Intent(HomeActivity.this, MainActivity.class);
//                startActivity(it);
//            }
//        });
    }
}

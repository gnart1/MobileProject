package com.hoanhtrang.mobileproject.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hoanhtrang.mobileproject.HomeActivity;
import com.hoanhtrang.mobileproject.MainActivity;
import com.hoanhtrang.mobileproject.NhapChi;
import com.hoanhtrang.mobileproject.NhapChiAdapter;
import com.hoanhtrang.mobileproject.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class nhapchiFragment extends Fragment {
    ListView lstNhapChi;
    ArrayList<NhapChi> listNhapChi;
    NhapChiAdapter adapter;
    private View mView;
    final Calendar myCalendar= Calendar.getInstance();
    EditText editText;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_nhapchi, container, false);
//        editText = (EditText) mView.findViewById(R.id.date);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
//        lstNhapChi = mView.findViewById(R.id.lstNhapChi);
//        listNhapChi = new ArrayList<>();
//        adapter = new NhapChiAdapter(getActivity(), R.layout.activity_nhapchi_chitiet, listNhapChi);
//
//        lstNhapChi.setAdapter(adapter);
//        editText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new DatePickerDialog(nhapchiFragment.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
//            }
//        });
        return mView;
    }

    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        editText.setText(dateFormat.format(myCalendar.getTime()));
    }
}

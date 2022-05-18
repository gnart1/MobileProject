package com.hoanhtrang.mobileproject.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.hoanhtrang.mobileproject.HomeActivity;
import com.hoanhtrang.mobileproject.R;

import java.text.DateFormat;
import java.util.Calendar;

public class thongkeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thongke, container, false);
    }
    public class thongke extends AppCompatActivity {
        DateFormat fmtDateAndTime = DateFormat.getDateTimeInstance();
        TextView lblDateTime;
        Calendar myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fragment_thongke);

            lblDateTime = (TextView) findViewById(R.id.lblDateTime);
            Button btnDate = (Button) findViewById(R.id.btnDate);
            btnDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new DatePickerDialog(HomeActivity.this, d, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            });
            updateLabel();
        }

        private void updateLabel() {
            lblDateTime.setText(fmtDateAndTime.format(myCalendar.getTime()));
        }
    }
}

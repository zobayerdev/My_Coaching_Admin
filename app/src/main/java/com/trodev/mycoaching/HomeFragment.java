package com.trodev.mycoaching;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.trodev.mycoaching.R;
import com.trodev.mycoaching.activity.TeacherRoutineActivity;


public class HomeFragment extends Fragment {

    LinearLayout routineLl, studentAttendanceLl, studentpaymentLl;

    public HomeFragment() {
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        routineLl = view.findViewById(R.id.routineLl);
        studentAttendanceLl = view.findViewById(R.id.studentAttendanceLl);
        studentpaymentLl = view.findViewById(R.id.studentpaymentLl);

        studentAttendanceLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), StudentAttendenceActivity.class);
                getContext().startActivity(intent);
            }
        });

        studentpaymentLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), StudentPaymentActivity.class);
                getContext().startActivity(intent);
            }
        });

        routineLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TeacherRoutineActivity.class);
                getContext().startActivity(intent);
            }
        });

        return view;
    }
}
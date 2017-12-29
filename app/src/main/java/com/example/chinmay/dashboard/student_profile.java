package com.example.chinmay.dashboard;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chinmay.dashboard.Profile_JSON;

import static android.R.attr.fragment;

/**
 * Created by chinmay on 27/9/17.
 */

public class student_profile extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        TextView mail,name,roll,year,contact,dept;
        final View view = inflater.inflate(R.layout.student_profile, container, false);



        mail= (TextView) view.findViewById(R.id.student_mail);
        name= (TextView) view.findViewById(R.id.student_name);
        roll= (TextView) view.findViewById(R.id.student_roll);
        year= (TextView) view.findViewById(R.id.student_year);
        contact= (TextView) view.findViewById(R.id.student_phone);
        dept= (TextView) view.findViewById(R.id.student_dept);

        String student_mail = getArguments().getString("Student_Mail_Id");
        String student_name = getArguments().getString("Student_Name");
        String student_roll = getArguments().getString("Student_Roll");
        String student_year = getArguments().getString("Student_Year");
        String student_contact = getArguments().getString("Student_contact");
        String student_dep = getArguments().getString("Student_Dept_Id");

        mail.setText(student_mail);
        name.setText(student_name);
        roll.setText(student_roll);
        year.setText(student_year);
        contact.setText(student_contact);

        //Toast.makeText(getActivity(),student_mail+student_dep,Toast.LENGTH_SHORT).show();

        switch(student_dep) {
            case "1":
                dept.setText("Computer");
                break;
            case "2":
                dept.setText("IT");
                break;
            case "3":
                dept.setText("EnTC");
                break;
            default:
                dept.setText("No dept specified");
                break;

        }


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event )
            {
                if( keyCode == KeyEvent.KEYCODE_BACK )
                {
                        Fragment fragment = new student_dashboard();
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.dashboard_fragment, fragment)
                                .commit();

                    return true;
                }
                return false;
            }
        } );

        return  view;
    }



    }

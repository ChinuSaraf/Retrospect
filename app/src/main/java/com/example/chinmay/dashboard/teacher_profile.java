package com.example.chinmay.dashboard;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by chinmay on 3/10/17.
 */

public class teacher_profile extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView mail,name,tid,contact,dept;
        final View view = inflater.inflate(R.layout.teacher_profile, container, false);

        mail= (TextView) view.findViewById(R.id.teacher_mail);
        name= (TextView) view.findViewById(R.id.teacher_name);
        tid= (TextView) view.findViewById(R.id.teacher_id);
        contact= (TextView) view.findViewById(R.id.teacher_phone);
        dept= (TextView) view.findViewById(R.id.teacher_dept);

        String teacher_mail = getArguments().getString("Teacher_Mail_Id");
        String teacher_name = getArguments().getString("Teacher_Name");
        String teacher_id = getArguments().getString("Teacher_Id");
        String teacher_contact = getArguments().getString("Teacher_contact");
        String teacher_dept = getArguments().getString("Teacher_Dept_Id");


        mail.setText(teacher_mail);
        name.setText(teacher_name);
        tid.setText(teacher_id);
        contact.setText(teacher_contact);

        switch(teacher_dept) {
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
                    Fragment fragment = new teacher_dashboard();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.dashboard_fragment, fragment)
                            .commit();
                    return true;
                }
                return false;
            }
        } );

        return view;
    }


    }

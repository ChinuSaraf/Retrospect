package com.example.chinmay.dashboard;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


/**
 * Created by chinmay on 22/8/17.
 */

public class student_registration_frag extends android.app.Fragment {
    Toolbar toolbar;
    EditText fname, mname, lname, email,pass, roll, contact,year;
    String fnam,mnam,lnam, mail,passw, cont, dept_str, rol_str, yea_str;
    RadioButton choice;
    RadioButton comp, it, entc, applied;
    Button submit;
    RadioGroup rg;
    int flag=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        final View view= inflater.inflate(R.layout.student_register, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getActivity(),MainActivity.class);
                startActivity(i);
            }
        });

        rg= (RadioGroup) view.findViewById(R.id.student_departments);
        fname= (EditText) view.findViewById(R.id.student_first);
        mname= (EditText) view.findViewById(R.id.student_middle);
        lname= (EditText) view.findViewById(R.id.student_last);
        email= (EditText) view.findViewById(R.id.stu_mail);
        pass= (EditText) view.findViewById(R.id.stu_pass);
        roll= (EditText) view.findViewById(R.id.stu_roll);
        contact= (EditText) view.findViewById(R.id.contact);
        year= (EditText) view.findViewById(R.id.stu_year);

        comp= (RadioButton) view.findViewById(R.id.stu_computer);
        it= (RadioButton) view.findViewById(R.id.stu_it);
        entc= (RadioButton) view.findViewById(R.id.stu_entc);
        applied= (RadioButton) view.findViewById(R.id.stu_applied);

        submit= (Button) view.findViewById(R.id.stu_submit);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

                if (activeNetwork != null) {
                    flag = 0;
                    int selectedId = rg.getCheckedRadioButtonId();

                    if (selectedId == -1) {
                        flag = 1;
                    } else {
                        // find the radiobutton by returned id
                        choice = (RadioButton) view.findViewById(selectedId);
                        flag = 0;
                        if (choice.getText().toString().equals("Computer")) {
                            dept_str = "1";
                        } else if (choice.getText().toString().equals("IT")) {
                            dept_str = "2";
                        } else if (choice.getText().toString().equals("EnTC")) {
                            dept_str = "3";
                        } else if (choice.getText().toString().equals("Applied Science")) {
                            dept_str = "4";
                        }
                    }

                    fnam = fname.getText().toString();
                    mnam = mname.getText().toString();
                    lnam = lname.getText().toString();
                    mail = email.getText().toString();
                    passw = pass.getText().toString();
                    cont = contact.getText().toString();
                    rol_str = roll.getText().toString();
                    yea_str = year.getText().toString();

                    if (flag == 1 || TextUtils.isEmpty(fnam) || TextUtils.isEmpty(mnam) || TextUtils.isEmpty(lnam) || TextUtils.isEmpty(mail) || TextUtils.isEmpty(passw) || TextUtils.isEmpty(cont) || TextUtils.isEmpty(rol_str) || TextUtils.isEmpty(yea_str) || TextUtils.isEmpty(dept_str)) {
                        Toast.makeText(getActivity(), "Enter all data!!", Toast.LENGTH_LONG).show();
                    } else {
                        String type = "registration_student";
                        BackgroundWorker backgroundWorker = new BackgroundWorker(getActivity());
                        backgroundWorker.execute(type, fnam, mnam, lnam, mail, passw, cont, rol_str, yea_str, dept_str);
                    }
                }
                else
                {
                    Toast.makeText(getActivity(), "Check your Internet connection!!", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;

    }


}

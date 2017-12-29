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

public class teacher_registration_frag extends android.app.Fragment {
    Toolbar toolbar;
    EditText fname, mname, lname, email,pass, teacher_id, contact,year;
    String fnam,mnam,lnam, mail,passw, cont, dept_str, id_str, yea_str;
    RadioButton comp, it, entc, applied;
    RadioButton choice;
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

        final View view= inflater.inflate(R.layout.teacher_register, container, false);
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


        rg= (RadioGroup) view.findViewById(R.id.teacher_departments);
        fname= (EditText) view.findViewById(R.id.teacher_first);
        mname= (EditText) view.findViewById(R.id.teacher_middle);
        lname= (EditText) view.findViewById(R.id.teacher_last);
        email= (EditText) view.findViewById(R.id.teacher_mail);
        pass= (EditText) view.findViewById(R.id.teacher_pass);
        teacher_id= (EditText) view.findViewById(R.id.teacher_id);
        contact= (EditText) view.findViewById(R.id.teacher_contact);

        comp= (RadioButton) view.findViewById(R.id.teacher_computer);
        it= (RadioButton) view.findViewById(R.id.teacher_it);
        entc= (RadioButton) view.findViewById(R.id.teacher_entc);
        applied= (RadioButton) view.findViewById(R.id.teacher_applied);

        submit= (Button) view.findViewById(R.id.teacher_submit);



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
                    id_str = teacher_id.getText().toString();

                    if (flag == 1 || TextUtils.isEmpty(fnam) || TextUtils.isEmpty(mnam) || TextUtils.isEmpty(lnam) || TextUtils.isEmpty(mail) || TextUtils.isEmpty(passw) || TextUtils.isEmpty(cont) || TextUtils.isEmpty(id_str)) {
                        Toast.makeText(getActivity(), "Enter all data!!", Toast.LENGTH_LONG).show();
                    } else {
                        String type = "registration_teacher";
                        BackgroundWorker backgroundWorker = new BackgroundWorker(getActivity());
                        backgroundWorker.execute(type, fnam, mnam, lnam, mail, passw, cont, id_str, dept_str);
                    }

                }
                else
                {
                    Toast.makeText(getActivity(), "Check your internet connection!!", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;

    }


}

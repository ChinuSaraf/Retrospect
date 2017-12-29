package com.example.chinmay.dashboard;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by chinmay on 22/8/17.
 */

public class Teacher_login_frag extends Fragment {
    EditText Username_teacher, Password_teacher;
    Button login_button;
    TextView forget,signup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
       
        View view= inflater.inflate(R.layout.teacher_login, container, false);
        Username_teacher = (EditText)view.findViewById(R.id.mail);
        Password_teacher = (EditText)view.findViewById(R.id.pass);
        login_button= (Button)view.findViewById(R.id.teacher_login_button);
        forget= (TextView) view.findViewById(R.id.forget_teacher);
        signup= (TextView) view.findViewById(R.id.signup_teacher);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

                if (activeNetwork != null)
                {
                    //we are connected to a network

                    String username = Username_teacher.getText().toString();
                    String password = Password_teacher.getText().toString();
                    String type = "login_teacher";
                    if(TextUtils.isEmpty(username) ||TextUtils.isEmpty(password) )
                    {
                        Toast.makeText(getActivity(),"Enter all data!!",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        BackgroundWorker backgroundWorker = new BackgroundWorker(getActivity());
                        backgroundWorker.execute(type, username, password);
                        Username_teacher.setText("");
                        Password_teacher.setText("");
                    }
                }
                else
                {
                    Toast.makeText(getActivity(),"Check your internet connection!!",Toast.LENGTH_SHORT).show();
                }


            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getActivity(),Registration.class);
                i.putExtra("Category","2");
                startActivity(i);
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

                if (activeNetwork != null)
                {

                    String username = Username_teacher.getText().toString();
                    if (TextUtils.isEmpty(username))
                    {
                        Toast.makeText(getActivity(), "Please Enter your mail id first!!", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        String type="forget_teacher";
                        BackgroundWorker backgroundWorker = new BackgroundWorker(getActivity());
                        backgroundWorker.execute(type, username);

                    }
                }
                else
                {
                    Toast.makeText(getActivity(),"Check your internet connection!!",Toast.LENGTH_SHORT).show();
                }


            }
        });

        return view;

    }


}

package com.example.chinmay.dashboard;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import static android.R.attr.fragment;
import static android.content.ContentValues.TAG;

/**
 * Created by chinmay on 28/9/17.
 */

public class Profile_JSON extends AsyncTask<String,Void,String> {

    Context context;
    static String type;
    static String name,year,email,roll,contact,depart;

   static public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

   static public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   static public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

   static public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    static public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    static public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

   static public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }


    Profile_JSON (Context ctx) {
        context = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Showing progress dialog
//        pDialog = new ProgressDialog(con);
//        pDialog.setMessage("Please wait...");
//        pDialog.setCancelable(false);
//        pDialog.show();

    }

    @Override
    protected String doInBackground(String... params) {

        type = params[0];
        String mail_id = params[1];
        String result = "";


if(type.equals("2")){
        String profile_url = "http://.../Teacher_Profile_API.php";      //Teacher Profile php file
        try {
            URL url = new URL(profile_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(mail_id, "UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
}

        else{
            String profile_url = "http://.../Student_Profile_API.php";      //Student profile php
            try {
                URL url = new URL(profile_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(mail_id, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;

        }




    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
if(!TextUtils.isEmpty(result)) {
    if(type.equals("2")) {

        try {
            JSONObject jsonObj = new JSONObject(result);

            String fname = jsonObj.getString("First_Name");
            String mname = jsonObj.getString("Middle_Name");
            String lname = jsonObj.getString("Last_Name");
            String tid = jsonObj.getString("TeacherID");
            String did = jsonObj.getString("Dept_ID");
            String contact = jsonObj.getString("Contact");
            String email = jsonObj.getString("Email_ID");


            setName(fname+ " "+ mname+" "+lname);
            setDepart(did);
            setContact(contact);
            setRoll(tid);
            setEmail(email);
            setType("2");

        } catch (final JSONException e) {
            Log.e(TAG, "Json parsing error: " + e.getMessage());
        }
    }

    else
    {
        try {
            JSONObject jsonObj = new JSONObject(result);

            String fname = jsonObj.getString("First_Name");
            String mname = jsonObj.getString("Middle_Name");
            String lname = jsonObj.getString("Last_Name");
            String roll = jsonObj.getString("Roll_no");
            String did = jsonObj.getString("Dept_ID");
            String contact = jsonObj.getString("Contact");
            String email = jsonObj.getString("Email_ID");
            String year=jsonObj.getString("Year");


            setName(fname+ " "+ mname+" "+lname);
            setDepart(did);
            setContact(contact);
            setRoll(roll);
            setEmail(email);
            setYear(year);
            setType("1");



        } catch (final JSONException e) {
            Log.e(TAG, "Json parsing error: " + e.getMessage());
        }
    }
}
else
{
    Toast.makeText(context,"Something went wrong!!",Toast.LENGTH_LONG).show();
}


    }

}


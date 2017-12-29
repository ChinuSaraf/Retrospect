package com.example.chinmay.dashboard;

/**
 * Created by chinmay on 30/8/17.
 */

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    String user_name,type;
    ProgressDialog dialog;
    BackgroundWorker (Context ctx) {
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
         type = params[0];

        if(type.equals("login_student")) {
            String login_url = "http://.../Student_Login.php";          //URL for student login php file
            try {
                 user_name = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
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

        else if(type.equals("login_teacher")) {
            String login_url = "http://.../Teacher_Login.php";          //Teacher_login php file
            try {
                 user_name = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
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

        else if(type.equals("registration_teacher")){

            String student_signup_url = "http://.../Teacher_SignUp.php";        //Teacher Signup php file
            try {
                String first_name = params[1];
                String middle_name = params[2];
                String last_name = params[3];
                String email = params[4];
                String password = params[5];
                String contact = params[6];
                String tid = params[7];
                String dept = params[8];



                URL url = new URL(student_signup_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("first_name","UTF-8")+"="+URLEncoder.encode(first_name,"UTF-8")+"&"
                        +URLEncoder.encode("middle_name","UTF-8")+"="+URLEncoder.encode(middle_name,"UTF-8")+"&"
                        +URLEncoder.encode("last_name","UTF-8")+"="+URLEncoder.encode(last_name,"UTF-8")+"&"
                        +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                        +URLEncoder.encode("contact","UTF-8")+"="+URLEncoder.encode(contact,"UTF-8")+"&"
                        +URLEncoder.encode("tid","UTF-8")+"="+URLEncoder.encode(tid,"UTF-8")+"&"
                        +URLEncoder.encode("dept","UTF-8")+"="+URLEncoder.encode(dept,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
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

        else if(type.equals("registration_student")) {
            String teacher_signup_url = "http://.../Student_SignUp.php";        //Student signup php file
            try {
                String first_name = params[1];
                String middle_name = params[2];
                String last_name = params[3];
                String email = params[4];
                String password = params[5];
                String contact = params[6];
                String rollno = params[7];
                String year = params[8];
                String dept = params[9];


                URL url = new URL(teacher_signup_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("first_name","UTF-8")+"="+URLEncoder.encode(first_name,"UTF-8")+"&"
                        +URLEncoder.encode("middle_name","UTF-8")+"="+URLEncoder.encode(middle_name,"UTF-8")+"&"
                        +URLEncoder.encode("last_name","UTF-8")+"="+URLEncoder.encode(last_name,"UTF-8")+"&"
                        +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                        +URLEncoder.encode("contact","UTF-8")+"="+URLEncoder.encode(contact,"UTF-8")+"&"
                        +URLEncoder.encode("rollno","UTF-8")+"="+URLEncoder.encode(rollno,"UTF-8")+"&"
                        +URLEncoder.encode("year","UTF-8")+"="+URLEncoder.encode(year,"UTF-8")+"&"
                        +URLEncoder.encode("dept","UTF-8")+"="+URLEncoder.encode(dept,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
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

        else if(type.equals("forget_teacher")) {
            String teacher_forget_url = "http://.../Teacher_Forgot_Password_API.php";       //Teacher forgot password
            try {
                user_name = params[1];

                URL url = new URL(teacher_forget_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
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

        else if(type.equals("forget_student")) {
            String student_forget_url = "http://.../Student_Forgot_Password_API.php";       //Student forgot password
            try {
                user_name = params[1];

                URL url = new URL(student_forget_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
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


//        else if(type.equals("session_info")){
//
//            String session_url = "http://naitik-com.stackstaging.com/android_login_api/include/Session_Info.php";
//            try {
//                String id = params[1];
//                String category = params[2];
//
//                URL url = new URL(session_url);
//                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
//                httpURLConnection.setRequestMethod("POST");
//                httpURLConnection.setDoOutput(true);
//                httpURLConnection.setDoInput(true);
//                OutputStream outputStream = httpURLConnection.getOutputStream();
//                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
//                String post_data = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8")+"&"
//                        +URLEncoder.encode("category","UTF-8")+"="+URLEncoder.encode(category,"UTF-8");
//
//                bufferedWriter.write(post_data);
//                bufferedWriter.flush();
//                bufferedWriter.close();
//                outputStream.close();
//                InputStream inputStream = httpURLConnection.getInputStream();
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
//                String result="";
//                String line="";
//                while((line = bufferedReader.readLine())!= null) {
//                    result += line;
//                }
//                bufferedReader.close();
//                inputStream.close();
//                httpURLConnection.disconnect();
//                return result;
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        return null;
    }

    @Override
    protected void onPreExecute() {

        dialog = new ProgressDialog(context);
        dialog.setTitle("Processing....");
        dialog.show();

//        alertDialog = new AlertDialog.Builder(context).create();
//        alertDialog.setTitle("*****Status******");
    }

    @Override
    protected void onPostExecute(String result) {
        dialog.dismiss();
        if(result.equals("s2") ||result.equals("t2") )
        {
            Intent i= new Intent(context, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
            Toast.makeText(context, "Signup Successful!!", Toast.LENGTH_LONG).show();
        }
        else if(result.equals("s1"))
        {
            Intent i= new Intent(context, Dashboard.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("Category","1");
            i.putExtra("mail",user_name);                          //Sending Session Info to backend
            context.startActivity(i);
            Toast.makeText(context, "Login Successful!!", Toast.LENGTH_LONG).show();

            SharedPreferences preferences;
            SharedPreferences.Editor editor;
            preferences = context.getSharedPreferences("com.example.chinmay.dashboard", Context.MODE_PRIVATE);

            editor = preferences.edit();
            editor.putString("student_id",user_name);
            editor.putString("category","1");
            editor.apply();
        }
        else if(result.equals("t1"))
        {
            Intent i= new Intent(context, Dashboard.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("Category","2");
            i.putExtra("mail",user_name);                              //Sending Session Info to backend

            //Toast.makeText(context, user_name, Toast.LENGTH_LONG).show();
            context.startActivity(i);
            Toast.makeText(context, "Login Successful!!", Toast.LENGTH_LONG).show();

            //Cookie adding

            SharedPreferences preferences;
            SharedPreferences.Editor editor;
            preferences = context.getSharedPreferences("com.example.chinmay.dashboard", Context.MODE_PRIVATE);

            editor = preferences.edit();
            editor.putString("teacher_id",user_name);
            editor.putString("category","2");
            editor.apply();
        }
        else if(result.equals("s10") || result.equals("t10"))
        {
            Toast.makeText(context, "Invalid username or password!!", Toast.LENGTH_LONG).show();
        }
        else if(result.equals("s20") || result.equals("t20"))
        {
            Toast.makeText(context, "Mail id already exist!!", Toast.LENGTH_LONG).show();
        }
        else if(result.equals("s21"))
        {
            Toast.makeText(context, "Roll No. already exist!!", Toast.LENGTH_LONG).show();
        }
        else if(result.equals("t21"))
        {
            Toast.makeText(context, "Teacher ID already exist!!", Toast.LENGTH_LONG).show();
        }

        else if(result.equals("s22") || result.equals("t22"))
        {
            Toast.makeText(context, "Something went wrong!!", Toast.LENGTH_LONG).show();
        }
        else if(result.equals("ms") )
        {
            Toast.makeText(context, "Password sent to your mail id!!", Toast.LENGTH_LONG).show();
        }
        else if(result.equals("mns"))
        {
            Toast.makeText(context, "Unable to send to mail!!", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

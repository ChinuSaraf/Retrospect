package com.example.chinmay.dashboard;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chinmay on 4/10/17.
 */

public class Student_Notes_List extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    int no_files;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.student_notes, container, false);

        final ListView listview;
        Button disp;
        TextView tname;
        final String id = getArguments().getString("Teacher_Id");
        String name= getArguments().getString("Teacher_Name");

        disp= (Button) view.findViewById(R.id.list_disp);
        tname= (TextView) view.findViewById(R.id.teacher_name);
        tname.setText(name);

        final String[] ListElements = new String[]{};

        listview = (ListView) view.findViewById(R.id.listView3);



        disp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager cm = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

                if (activeNetwork != null) {
                    file_names ft = new file_names(getActivity());
                    ft.execute(id);


                    final List<String> ListElementsArrayList = new ArrayList<String>(Arrays.asList(ListElements));


                    //ListView display
                    final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                            (getActivity(), android.R.layout.simple_list_item_1, ListElementsArrayList);
                    listview.setAdapter(adapter);

                    int no_files = file_names.n;

                    for (int i = 0; i < no_files; i++) {
                        ListElementsArrayList.add(file_names.files[i + 1]);

                    }
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getActivity(),"Check your internet connection!!",Toast.LENGTH_SHORT).show();
                }
            }
        });


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position,
                                    long id) {


                new android.support.v7.app.AlertDialog.Builder(getActivity())
                        .setIcon(R.drawable.download)
                        .setTitle("Download")
                        .setMessage("Do u want to download?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                ConnectivityManager cm = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

                                if (activeNetwork != null) {
                                    String file_name = listview.getItemAtPosition(position).toString();

                                    Download dw = new Download(getActivity());
                                    dw.execute(file_name);
                                }
                                else {
                                    Toast.makeText(getActivity(),"Check your internet connection!!",Toast.LENGTH_SHORT).show();
                                }

                            }

                        })
                        .setNegativeButton("No", null)
                        .show();





            }
        });

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

        return view;
    }
}

class file_names extends AsyncTask<String,Void,String> {
    Context context;
    static  int n;
    static  String[] files;
    AlertDialog alertDialog;
    String id,category;
    file_names (Context ctx) {
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String id = params[0];

            String send_url = "http://.../Session_Info.php";        //Session info php file
            try {
                id=params[0];
                URL url = new URL(send_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");

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


        return null;
    }

    @Override
    protected void onPreExecute() {

//        alertDialog = new AlertDialog.Builder(context).create();
//        alertDialog.setTitle("*****Status******");
    }

    @Override
    protected void onPostExecute(String result) {
        if(!TextUtils.isEmpty(result)) {

                files= result.split("\\$");
                n=Integer.parseInt(files[0]);

        }
        else if(TextUtils.isEmpty(result))
        {
            Toast.makeText(context, "Something went wrong!!", Toast.LENGTH_SHORT).show();
        }
//        alertDialog.setMessage(result);
//        alertDialog.show();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}


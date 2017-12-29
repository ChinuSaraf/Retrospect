package com.example.chinmay.dashboard;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
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

import static android.content.ContentValues.TAG;

/**
 * Created by chinmay on 5/9/17.
 */

public class student_dashboard extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    static HashMap<String, String> name_id = new HashMap<>();
    TextView display;
    String[] single_set;
    String[] names;
    String id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       final ListView listview;

        final View view = inflater.inflate(R.layout.student_dashboard_fragment, container, false);
        listview = (ListView) view.findViewById(R.id.listView1);
        final String[] ListElements = new String[]{};




        display= (TextView) view.findViewById(R.id.display_list);

        SharedPreferences preferences;
        SharedPreferences.Editor editor;
        preferences = getActivity().getSharedPreferences("com.example.chinmay.dashboard", Context.MODE_PRIVATE);

       final String mail = preferences.getString("student_id", null);


        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cookie "mail" accessing

//                final List<String> ListElementsArrayList = new ArrayList<String>(Arrays.asList(ListElements));
//
//
//                //ListView display
//                final ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                        (getActivity(), android.R.layout.simple_list_item_1, ListElementsArrayList);
//                listview.setAdapter(adapter);
                ConnectivityManager cm = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

                if (activeNetwork != null) {

                    teacher_disp td = new teacher_disp(getActivity());
                    td.execute(mail);
                    final List<String> ListElementsArrayList = new ArrayList<String>(Arrays.asList(ListElements));
                    final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                            (getActivity(), android.R.layout.simple_list_item_1, ListElementsArrayList);
                    listview.setAdapter(adapter);

                    for (int i = 0; i < teacher_disp.getNo(); i++) {
                        single_set = teacher_disp.getCombine()[i + 1].split("\\$");
                        ListElementsArrayList.add(single_set[0]);
                        name_id.put(single_set[0], single_set[1]);
                    }
                    //ListView display
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getActivity(),"Check your internet connection!!",Toast.LENGTH_SHORT).show();
                }

            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {


                String teacher_name = listview.getItemAtPosition(position).toString();
                String teacher_id= name_id.get(teacher_name);
                Fragment fragment = new Student_Notes_List();
// Insert the fragment by replacing any existing fragment
                Bundle args = new Bundle();
                args.putString("Teacher_Id", teacher_id);
                args.putString("Teacher_Name",teacher_name);
                fragment.setArguments(args);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.dashboard_fragment, fragment)
                        .commit();


            }
        });

        return view;
    }


}

class teacher_disp extends AsyncTask<String,Void,String> {

    Context context;
     static String[] combine;

    static public String[] getCombine() {
        return combine;
    }

    public void setCombine(String[] combine) {
        this.combine = combine;
    }

    static int no;
    String id;


    teacher_disp (Context ctx) {
        context = ctx;
    }

    static public int getNo() {
        return no;
    }

   public void setNo(int no) {
        this.no = no;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        String mail_id = params[0];
        String result = "";
            String profile_url = "http://.../Teachers_List_API.php";        //Teacher list returning php file
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


        return null;

    }




    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if(!TextUtils.isEmpty(result)) {
            combine=result.split("\\%");
            setCombine(combine);
            no=Integer.parseInt(combine[0]);
            setNo(no);

        }
        else
        {
            Toast.makeText(context,"Something went wrong!!",Toast.LENGTH_LONG).show();
        }


    }

}

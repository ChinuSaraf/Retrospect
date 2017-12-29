package com.example.chinmay.dashboard;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chinmay on 7/10/17.
 */

public class Downloaded_Files extends Fragment{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    static HashMap<String, String> name_id = new HashMap<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final ListView listview;
        final View view = inflater.inflate(R.layout.downloaded_files, container, false);
        listview= (ListView) view.findViewById(R.id.list);
       final File sdcard = Environment.getExternalStorageDirectory();

        // SDCard Path
        final String MEDIA_PATH = new String(sdcard+"/Dashboard/download/");
        final String[] ListElements = new String[]{};
        final List<String> ListElementsArrayList = new ArrayList<String>(Arrays.asList(ListElements));
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_list_item_1, ListElementsArrayList);
        listview.setAdapter(adapter);


        /**
         * Function to read all  files from sdcard
         * and store the details in ArrayList
         * */

            File home = new File(MEDIA_PATH);

        if(home.exists()) {
            if (home.listFiles().length > 0) {
                for (File file : home.listFiles()) {
                    // Adding each file to List
                    ListElementsArrayList.add(file.getName());
                }
                adapter.notifyDataSetChanged();
            }
        }
        else {
            Toast.makeText(getActivity(),"No files downloaded!!",Toast.LENGTH_SHORT).show();
        }

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String file_name = listview.getItemAtPosition(position).toString();
                    File file = new File(sdcard+"/Dashboard/download/"+file_name);

                        MimeTypeMap map = MimeTypeMap.getSingleton();
                        String ext = MimeTypeMap.getFileExtensionFromUrl(file.getName());
                        String type = map.getMimeTypeFromExtension(ext);

                        if (type == null)
                            type = "*/*";

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        Uri data = Uri.fromFile(file);

                        intent.setDataAndType(data, type);
                        startActivity(intent);


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
                    SharedPreferences preferences;
                    SharedPreferences.Editor editor;
                    preferences = getActivity().getSharedPreferences("com.example.chinmay.dashboard", Context.MODE_PRIVATE);

                    String cat = preferences.getString("category", null);

                    if(cat.equals("1")) {
                        Fragment fragment = new student_dashboard();
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.dashboard_fragment, fragment)
                                .commit();
                    }
                    else {

                        Fragment fragment = new teacher_dashboard();
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.dashboard_fragment, fragment)
                                .commit();
                    }

                    return true;

                }





                return false;
            }
        } );

        return  view;
    }


}





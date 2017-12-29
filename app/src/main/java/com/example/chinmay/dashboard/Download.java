package com.example.chinmay.dashboard;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;



/**
 * Created by chinmay on 30/8/17.
 */

public class Download extends AsyncTask<String, Void, Integer> {
    String file_name = "";
    Context context;
   ProgressDialog dialog;
    File sdcard = Environment.getExternalStorageDirectory();
    Download (Context ctx) {
         context = ctx;
    }


    @Override
    protected Integer doInBackground(String... params) {
        // TODO Auto-generated method stub
        file_name = "";
        file_name = params[0];

        try {

            HttpURLConnection url_conn = null;
            byte[] bffr;

            long totalSize = 0;
            File directory = new File(
                    Environment.getExternalStorageDirectory()
                            + "/Dashboard/download");
            directory.mkdirs();
            // 06-03 17:57:41.160: D/file name(6882):

            Log.d("file name", file_name.toString());
            url_conn = (HttpURLConnection) (new URL("http://.../uploads/" + file_name)).openConnection();       //File stored location
            url_conn.setRequestMethod("GET");
            url_conn.setDoOutput(true);
            url_conn.connect();

            if (url_conn.getContentLength() > 0) {
                File imgFile = new File(sdcard+"/Dashboard/download/",file_name);
                FileOutputStream fos = new FileOutputStream(imgFile);
                InputStream is = url_conn.getInputStream();
                totalSize = url_conn.getContentLength();
                // Log.d("File Download Size ",totalSize+"");
                long total = 0;
                bffr = new byte[1024];
                int bufferLength = 0;
                while ((bufferLength = is.read(bffr)) > 0) {
                    total += bufferLength;
                    publishProgress("" + (int) ((total * 100) / totalSize));
                    fos.write(bffr, 0, bufferLength);
                }
                fos.close();
            } else
                Log.w(file_name.toString(), "FILE NOT FOUND");
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    private void publishProgress(String... process) {
        // TODO Auto-generated method stub

       dialog.setProgress(Integer.parseInt(process[0]));
    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Downloading....");
        dialog.show();
    }

    protected void onPostExecute(Integer unused) {
        Log.d("after downloading file ", "file downloaded ");
        switch (unused) {
            case 0:
               dialog.dismiss();
                Toast.makeText(context,"Download Complete",Toast.LENGTH_SHORT).show();
                File file = new File(sdcard+"/Dashboard/download/"+file_name);
                MimeTypeMap map = MimeTypeMap.getSingleton();
                String ext = MimeTypeMap.getFileExtensionFromUrl(file.getName());
                String type = map.getMimeTypeFromExtension(ext);

                if (type == null)
                    type = "*/*";

                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.fromFile(file);

                intent.setDataAndType(data, type);
                context.startActivity(intent);

                break;
        }
    }
}
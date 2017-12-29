package com.example.chinmay.dashboard;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.ULocale;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String category;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
/*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
        TextView email = (TextView)header.findViewById(R.id.logged_in_id);


        //Fragment according to student or teacher
        Intent i=getIntent();
       category= i.getStringExtra("Category");

        if(category.equals("1"))
        {
            String id= i.getStringExtra("mail");
            email.setText(id);
            android.app.Fragment fragment = new student_dashboard();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.popBackStack();
            Bundle bundle=new Bundle();
            bundle.putString("mail", id);
            fragment.setArguments(bundle);

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragment);
            fragmentTransaction.add(R.id.dashboard_fragment, fragment);
            fragmentTransaction.commit();
        }

        else
        {
            String id= i.getStringExtra("mail");
            email.setText(id);
            android.app.Fragment fragment = new teacher_dashboard();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.popBackStack();
            Bundle bundle=new Bundle();
            bundle.putString("mail", id);
            fragment.setArguments(bundle);

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragment);
            fragmentTransaction.add(R.id.dashboard_fragment, fragment);
            fragmentTransaction.commit();
        }



//        Bundle bundle=new Bundle();
//        bundle.putString("message", "From Activity");
//        //set Fragmentclass Arguments
//        Fragmentclass fragobj=new Fragmentclass();
//        fragobj.setArguments(bundle);
//        and to receive in fragment in Fragment onCreateView method:
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                Bundle savedInstanceState) {
//            String strtext=getArguments().getString("message");
//
//            return inflater.inflate(R.layout.fragment, container, false);
//        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.logout)
                    .setTitle("Logout?")
                    .setMessage("Are you sure you want to logout?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            SharedPreferences preferences;
                            SharedPreferences.Editor editor;
                            preferences =getSharedPreferences("com.example.chinmay.dashboard", Context.MODE_PRIVATE);

                            editor = preferences.edit();
                            editor.putString("teacher_id"," ");
                            editor.putString("student_id"," ");
                            editor.apply();

                            Intent intent=new Intent(Dashboard.this, MainActivity.class);
                            startActivity(intent);


                        }

                    })
                    .setNegativeButton("No", null)
                    .show();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //Cookie "mail" accessing
        SharedPreferences preferences;
        SharedPreferences.Editor editor;
        preferences = getSharedPreferences("com.example.chinmay.dashboard", Context.MODE_PRIVATE);

        String mail = preferences.getString("teacher_id", null);
        final String type,name,year,contact,mailid,dept;


        if (id == R.id.nav_dashboard) {

            Intent i=getIntent();
            if(category.equals("1"))
            {
                String id1= i.getStringExtra("mail");
                android.app.Fragment fragment = new student_dashboard();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStack();
                Bundle bundle=new Bundle();
                bundle.putString("mail", id1);
                fragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(fragment);
                fragmentTransaction.add(R.id.dashboard_fragment, fragment);
                fragmentTransaction.commit();
            }

            else
            {
                String id2= i.getStringExtra("mail");
                android.app.Fragment fragment = new teacher_dashboard();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStack();
                Bundle bundle=new Bundle();
                bundle.putString("mail", id2);
                fragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(fragment);
                fragmentTransaction.add(R.id.dashboard_fragment, fragment);
                fragmentTransaction.commit();
            }
        }
        else if (id == R.id.nav_profile) {

            ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

            if (activeNetwork != null)
            {
                if(category.equals("1"))
                {
                    type="1";
                    mail = preferences.getString("student_id", null);
                }
                else
                {
                    type="2";
                    mail = preferences.getString("teacher_id", null);
                }

                final Profile_JSON pr = new Profile_JSON(this);
                pr.execute(type, mail);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        if(type.equals("2")) {
                            Fragment fragment = new teacher_profile();

                            Bundle args = new Bundle();
                            args.putString("Teacher_Mail_Id", pr.getEmail());
                            args.putString("Teacher_Name", pr.getName());
                            args.putString("Teacher_Id", pr.getRoll());
                            args.putString("Teacher_Dept_Id", pr.getDepart());
                            args.putString("Teacher_contact", pr.getContact());
                            fragment.setArguments(args);
                            FragmentManager fragmentManager = getFragmentManager();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.dashboard_fragment, fragment)
                                    .commit();
                        }

                        else
                        {
                            Fragment fragment = new student_profile();

                            Bundle args = new Bundle();
                            args.putString("Student_Mail_Id", pr.getEmail());
                            args.putString("Student_Name",pr.getName());
                            args.putString("Student_Roll", pr.getRoll());
                            args.putString("Student_Dept_Id", pr.getDepart());
                            args.putString("Student_contact", pr.getContact());
                            args.putString("Student_Year", pr.getYear());

                            fragment.setArguments(args);
                            FragmentManager fragmentManager = getFragmentManager();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.dashboard_fragment, fragment)
                                    .commit();
                        }
                    }
                }, 1500);
            }
            else
            {
                Toast.makeText(Dashboard.this,"Check your internet connection!!",Toast.LENGTH_SHORT).show();
            }

        }

        else if (id == R.id.nav_logout) {

            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.logout)
                    .setTitle("Logout?")
                    .setMessage("Are you sure you want to logout?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            SharedPreferences preferences;
                            SharedPreferences.Editor editor;
                            preferences =getSharedPreferences("com.example.chinmay.dashboard", Context.MODE_PRIVATE);

                            editor = preferences.edit();
                            editor.putString("teacher_id"," ");
                            editor.putString("student_id"," ");
                            editor.apply();

                            Intent intent=new Intent(Dashboard.this, MainActivity.class);
                            startActivity(intent);


                        }

                    })
                    .setNegativeButton("No", null)
                    .show();

        }

        else if(id == R.id.nav_downloads)
        {
            Fragment fragment = new Downloaded_Files();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.dashboard_fragment, fragment)
                    .commit();
        }
        else if (id == R.id.nav_share) {

            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "Retrospect");
            String sAux = "\nHey check this app. I got it very useful for notes. Link for app is-:\n\n";
            sAux = sAux + "https://play.google.com/store/apps/details?id=com.example.chinmay.dashboard\n\n";
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivity(Intent.createChooser(i, "Share with"));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

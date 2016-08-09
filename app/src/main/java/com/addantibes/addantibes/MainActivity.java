package com.addantibes.addantibes;

import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Alarme deuxieme culte
        Calendar c = Calendar.getInstance();
        Date temp = c.getTime();
        Calendar calendar = Calendar.getInstance();

        //calendar.set(Calendar.DAY_OF_WEEK, 1); // Que le dimanche
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.AM_PM,Calendar.AM);
        Date pm = calendar.getTime();

        // Alarme réunion dimance apres midi
        Calendar c2 = Calendar.getInstance();
        Date temp2 = c2.getTime();
        Calendar calendar2 = Calendar.getInstance();

        //calendar2.set(Calendar.DAY_OF_WEEK, 1); // Que le dimanche
        calendar2.set(Calendar.HOUR_OF_DAY, 15);
        calendar2.set(Calendar.MINUTE, 00);
        calendar2.set(Calendar.SECOND, 0);
        calendar2.set(Calendar.AM_PM,Calendar.PM);
        Date pm2 = calendar2.getTime();

        // Alarme réunion mardi apres midi
        Calendar c3 = Calendar.getInstance();
        Date temp3 = c3.getTime();
        Calendar calendar3 = Calendar.getInstance();

        //calendar2.set(Calendar.DAY_OF_WEEK, 3); // Que le mardi
        calendar3.set(Calendar.HOUR_OF_DAY, 18);
        calendar3.set(Calendar.MINUTE, 45);
        calendar3.set(Calendar.SECOND, 0);
        calendar3.set(Calendar.AM_PM,Calendar.PM);
        Date pm3 = calendar3.getTime();


        if(pm.after(temp))
        {
            Intent myIntent = new Intent(MainActivity.this, MyReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent,0);

            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
        }

        if(pm2.after(temp))
        {
            Intent myIntent = new Intent(MainActivity.this, MyReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent,0);

            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
        }

        if(pm3.after(temp))
        {
            Intent myIntent = new Intent(MainActivity.this, MyReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent,0);

            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_accueil) {
            // Handle the camera action
        } else if (id == R.id.nav_reunions) {

        } else if (id == R.id.nav_venir) {

        } else if (id == R.id.nav_annonces) {

        } else if (id == R.id.nav_events) {

        } else if (id == R.id.nav_video) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new class_video())
                    .commit();

        } else if (id == R.id.nav_audio) {

        } else if (id == R.id.nav_ecrit) {

        } else if (id == R.id.nav_appeler) {
            // Nous appeller
            Intent appel = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0781177721"));
            startActivity(appel);

        } else if (id == R.id.nav_mail) {
            // Envoyer un mail
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","fifi774@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Message depuis l'application mobile");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Votre message");
            startActivity(Intent.createChooser(emailIntent, "Envoyer un mail avec"));

        }else if (id == R.id.nav_site) {
            // Visitez le site
            String url = "http://www.addantibes.com";
            Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
            startActivity(intent);

        }else if (id == R.id.nav_youtube) {
            // Visitez le site
            String url = "https://www.youtube.com/user/addantibes";
            Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
            startActivity(intent);

        }else if (id == R.id.nav_facebook) {
            // Visitez le site
            String url = "https://www.facebook.com/Eglise-Evang%C3%A9lique-Add-Antibes-423206644513009/?ref=ts&fref=ts";
            Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

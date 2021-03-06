package com.example.scholerswheel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.Objects;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Button faculties;
    Button Schedule;
    Button Notifications;
    Button Track;

    public static DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;

    private ActionBarDrawerToggle mDrawerToggle;

    FirebaseAuth firebaseAuth;
    DatabaseReference db;
    private DrawerLayout.DrawerListener drawerListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_drawer);

        NavigationView navigationView =findViewById(R.id.drawer);
        navigationView.setNavigationItemSelectedListener(this);

        ImageView imageView2 =findViewById(R.id.imageView2);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView2.getDrawable();
        animationDrawable.start();

        setupToolbar();


        Track = findViewById(R.id.btn_track);
        Track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, Track.class));

            }
        });


        Notifications = findViewById(R.id.btn_notification);
        Notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, Notifications.class));

            }
        });


        Schedule = findViewById(R.id.btn_schedule);
        Schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, Schedule.class));

            }
        });



        faculties = findViewById(R.id.btn_faculties);
        faculties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, FacultyActivity.class));

            }
        });


    }

    private void setupToolbar() {
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setTitle("Home");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Setup Navigation Drawer Layout
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);


        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_logout:
                firebaseAuth.signOut();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Intent intent;

        if (menuItem.getItemId()==R.id.action_profile)
        {
            intent =new Intent(this,Profile.class);
            startActivity(intent);
        }

        else if (menuItem.getItemId()==R.id.action_settings_drawer)
        {
            intent =new Intent(this,Settings.class);
            startActivity(intent);
        }

        else if (menuItem.getItemId()==R.id.action_settings_developers)
        {
            intent =new Intent(this,Developers.class);
            startActivity(intent);
        }

        return false;
    }
}
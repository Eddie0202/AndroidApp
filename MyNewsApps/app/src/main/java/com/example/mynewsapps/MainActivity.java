package com.example.mynewsapps;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mynewsapps.Model.CurretUser;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    SettingFragment settingsFragment = new SettingFragment();
    NotificationFragment notificationFragment = new NotificationFragment();
    UserFragment userFragment = new UserFragment();
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         actionBar=getSupportActionBar();
        actionBar.hide();


        bottomNavigationView  = findViewById(R.id.bottom_navigation);


        Intent intent = getIntent();
        String username_for_tv = intent.getStringExtra("username");
        Bundle b = new Bundle();
        b.putString("username", (String) username_for_tv);
        homeFragment.setArguments(b);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();

        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.notification);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(8);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:{
                        Intent intent = getIntent();
                        String username_for_tv = intent.getStringExtra("username");
                        Bundle b = new Bundle();
                        b.putString("username", (String) username_for_tv);
                        homeFragment.setArguments(b);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                        return true;
                    }
                    case R.id.notification: {
                        Intent intent = getIntent();
                        String username_for_tv = intent.getStringExtra("username");
                        Bundle b = new Bundle();
                        b.putString("username", (String) username_for_tv);
                        notificationFragment.setArguments(b);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, notificationFragment).commit();
                        return true;
                    }
                    case R.id.settings: {
                        Intent intent = getIntent();
                        String username_for_tv = intent.getStringExtra("username");
                        Bundle b = new Bundle();
                        b.putString("username", (String) username_for_tv);
                        settingsFragment.setArguments(b);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, settingsFragment).commit();
                        return true;
                    }
                    case R.id.users:{
                        Intent intent = getIntent();
                        String username_for_tv = intent.getStringExtra("username");
                        Bundle b = new Bundle();
                        b.putString("username", (String) username_for_tv);
                        userFragment.setArguments(b);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,userFragment).commit();
                        return true;
                    }
                }

                return false;
            }
        });
    }
}
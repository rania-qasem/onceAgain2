package com.example.onceagain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class Home extends AppCompatActivity {

//    Toolbar toolbar;
    BottomNavigationView bottomNavigation;
    Fragment selectedFragment;
    FloatingActionButton Add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Add Toolbar to the home page
//        toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle("");
//        toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
//        setSupportActionBar(toolbar);


        selectedFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        //Add Bottom Navigation Bar to the home page
        bottomNavigation = findViewById(R.id.Bottom_Navigation);
        bottomNavigation.setBackground(null);
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home_nav: selectedFragment = new HomeFragment();
                        break;

                    case R.id.chat_nav: selectedFragment = new ChatFragment();
                        break;

                    case R.id.ads_nav: selectedFragment = new MyAdsFragment();
                        break;

                    case R.id.profile_nav: selectedFragment = new ProfileFragment();
                        break;

                }//End of switch statement

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                return true;
            }
        });//End of onNavigationItemSelected method

        Add = findViewById(R.id.addAd);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Add_ad.class);
                startActivity(intent);
            }
        });

    }//End of onCreate method

    //display items in the toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }
}
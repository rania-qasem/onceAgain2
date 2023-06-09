package com.example.onceagain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;

public class Home extends AppCompatActivity {

//    Toolbar toolbar;
    BottomNavigationView bottomNavigation;
    Fragment selectedFragment;
    FloatingActionButton Add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("shared", getApplicationContext().MODE_PRIVATE);

//        boolean isSigned = sharedPref.getBoolean("isSignedIn",false);
//        if (!isSigned){
//            Intent intent = new Intent(this, SignIn.class);
//            startActivity(intent);
//        }

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
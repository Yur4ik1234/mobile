package com.example.first_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class FeelActivity extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener {
    private Button unmotiv;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feel2);

        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setSelectedItemId(R.id.navigation_account);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }
    Account account_fragment = new Account();
    Favorite favorite_fragment = new Favorite();
    Text text_fragment = new Text();
    Settings settings_fragment = new Settings();


       /*

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }*/

        @Override
        public boolean onNavigationItemSelected (@NonNull MenuItem item){
            switch (item.getItemId())
            {
                case R.id.navigation_account:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,account_fragment).commit();
                    return true;

                case R.id.navigation_favorite:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,favorite_fragment).commit();
                    return true;

            case R.id.navigation_text:
            getSupportFragmentManager().beginTransaction().replace(R.id.container,text_fragment).commit();
            return true;

                case R.id.navigation_settings:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,settings_fragment).commit();
                    return true;
            }
            return false;
        }




}
package com.sih.app1.kisaanmitra.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.sih.app1.kisaanmitra.R;
import com.sih.app1.kisaanmitra.fragments.NewsFeedFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationViewEx navigationView;
    private NewsFeedFragment newsFeedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        navigationView.enableShiftingMode(false);
        navigationView.enableItemShiftingMode(false);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.news_feed:
                        newsFeedFragment = NewsFeedFragment.newInstance();
                        createFragment(newsFeedFragment, "Open News", true);
                        break;
                    case R.id.schemes:
                        break;
                    case R.id.blogs:
                        break;
                    case R.id.suggestions:
                        break;
                    case R.id.products:
                        break;
                }
                return false;
            }
        });

    }


    private void initialize(){
        navigationView = findViewById(R.id.navigation);
    }


    public void createFragment(Fragment fragment, String tag, boolean addToBackStack) {
        if (fragment != null) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment, tag);
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(tag);
            }
            if (!isFinishing() && !isDestroyed()) {
                fragmentTransaction.commitAllowingStateLoss();
            }
        }
    }

}

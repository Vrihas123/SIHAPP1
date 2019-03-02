package com.sih.app1.kisaanmitra.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.sih.app1.kisaanmitra.R;
import com.sih.app1.kisaanmitra.fragments.AdvisoryFragment;
import com.sih.app1.kisaanmitra.fragments.NewsFeedFragment;
import com.sih.app1.kisaanmitra.fragments.ProductTypeFragment;
import com.sih.app1.kisaanmitra.fragments.ProfileFragment;
import com.sih.app1.kisaanmitra.fragments.ProductFragment;
import com.sih.app1.kisaanmitra.fragments.SchemesFragment;
import com.sih.app1.kisaanmitra.fragments.NewsFeedFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationViewEx navigationView;
    private NewsFeedFragment newsFeedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        loadFragment(new NewsFeedFragment());
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
                        createFragment(new SchemesFragment(), "SchemesFragment", false);
                        return true;
                    case R.id.advisor:
                        createFragment(new AdvisoryFragment(), "AdvisoryFragment", false);
                        return true;
                    case R.id.products:
                        createFragment(new ProductTypeFragment(), "ProductTypeFragment", false);
                        return true;
                    case R.id.profile:
                        createFragment(new ProfileFragment(), "ProfileFragment", false);
                        return true;
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

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}

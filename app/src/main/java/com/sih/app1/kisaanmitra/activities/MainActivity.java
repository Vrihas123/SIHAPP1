package com.sih.app1.kisaanmitra.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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
import com.sih.app1.kisaanmitra.fragments.RentFragment;
import com.sih.app1.kisaanmitra.fragments.SchemesFragment;
import com.sih.app1.kisaanmitra.fragments.NewsFeedFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationViewEx navigationView;
    private NewsFeedFragment newsFeedFragment;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.activity_main_drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navView = findViewById(R.id.nv);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id){
                    case R.id.weather:
                    case R.id.nearby_places:
                    case R.id.privacy:
                    case R.id.help_support:
                    default:
                        return true;
                }
            }
        });


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
                        createFragment(new RentFragment(), "SchemesFragment", false);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            if (drawerToggle.onOptionsItemSelected(item)) {
                return true;
            }
        return super.onOptionsItemSelected(item);

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

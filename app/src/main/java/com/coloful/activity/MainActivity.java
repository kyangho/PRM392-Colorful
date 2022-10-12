package com.coloful.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.MenuItem;

import com.coloful.R;
import com.coloful.fragments.ProfileFragment;
import com.coloful.fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.coloful.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private ActionBar toolbar;
    private BottomNavigationView navigation;

    public MainActivity() {
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = getSupportActionBar();

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);
        getDataFromOtherActivity(getIntent().getStringExtra("backScreen"));
    }

    private void getDataFromOtherActivity(String backScreen) {
        if (backScreen == null) {
            backScreen = new String();
        }
        switch (backScreen) {
            case "Profile":
                loadFragment(new ProfileFragment(), "Profile");
                navigation.getMenu().getItem(3).setChecked(true);
                break;
            default:
                loadFragment(new HomeFragment(), "Home");
                navigation.getMenu().getItem(0).setChecked(true);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                loadFragment(fragment, "Home");
                return true;
            case R.id.navigation_search:
                fragment = new SearchFragment();
                loadFragment(fragment, "Search");
                return true;
            case R.id.navigation_new_set:
                fragment = new HomeFragment();
                loadFragment(fragment, "Create Set");
                return true;
            case R.id.navigation_profile:
                fragment = new ProfileFragment();
                loadFragment(fragment, "Profile");
                return true;
        }
        return false;
    }

    private void loadFragment(Fragment fragment, String title) {
        if (!title.equalsIgnoreCase("Home")) {
            toolbar.hide();
        } else {
            toolbar.show();
        }

        toolbar.setTitle(title);
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
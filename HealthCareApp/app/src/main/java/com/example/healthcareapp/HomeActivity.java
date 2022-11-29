package com.example.healthcareapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {
    NavigationBarView bottomNav;
    FrameLayout contentContainer;
    Fragment homeFragment, newsFragment, chatFragment, userFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNav = findViewById(R.id.bottomNav);

        bottomNav.setOnItemSelectedListener(mOnItemSelectedListener);

        homeFragment = new HomeFragment();
        newsFragment = new NewsFragment();
        chatFragment = new ChatFragment();
        userFragment = new UserFragment();

        loadFragment(homeFragment);
    }

    private NavigationBarView.OnItemSelectedListener mOnItemSelectedListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.homeItem:
                    loadFragment(homeFragment);
                    return true;

                case R.id.newsItem:
                    loadFragment(newsFragment);
                    return true;

                case R.id.chatItem:
                    loadFragment(chatFragment);
                    return true;

                case R.id.userItem:
                    loadFragment(userFragment);
                    return true;

                default:
                    return false;
            }
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
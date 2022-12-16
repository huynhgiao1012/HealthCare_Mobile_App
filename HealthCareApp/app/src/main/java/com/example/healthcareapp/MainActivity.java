package com.example.healthcareapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    NavigationBarView bottomNav;
    FrameLayout contentContainer;
    Fragment homeFragment, newsFragment, chatFragment, userFragment;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        bottomNav = findViewById(R.id.bottomNav);

        bottomNav.setOnItemSelectedListener(mOnItemSelectedListener);

        homeFragment = new HomeFragment();
        newsFragment = new NewsFragment();
        chatFragment = new ChatFragment();
        userFragment = new UserFragment();

        loadFragment(homeFragment);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            startActivity(new Intent(getApplicationContext(), SignInActivity.class));
        }
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
        transaction.commit();
    }
}
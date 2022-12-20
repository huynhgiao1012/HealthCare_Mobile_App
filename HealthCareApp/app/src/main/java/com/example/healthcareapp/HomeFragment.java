package com.example.healthcareapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private Button toSignInPg, toSignUpPg, testBtn;
    private RecyclerView topNewsRV;
    private ArrayList<NewsArticle> topNewsList;
    private TopNewsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toSignInPg = getView().findViewById(R.id.toSignInPageBtn);
        toSignUpPg = getView().findViewById(R.id.toSignUpPageBtn);
        testBtn = getView().findViewById(R.id.testBtn);

        toSignInPg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toIntent(SignInActivity.class);
            }
        });

        toSignUpPg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toIntent(SignUpActivity.class);
            }
        });

        testBtn.setOnClickListener(testDBHander);

        topNewsRV = getView().findViewById(R.id.topNewsRV);
        topNewsRV.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        topNewsList = new ArrayList<>();
        adapter = new TopNewsAdapter(getContext(), topNewsList);
        topNewsRV.setAdapter(adapter);

        populateData();
    }

    private void toIntent(Class activityClass) {
        Intent intent = new Intent(getActivity(), activityClass);
        startActivity(intent);
    }

    private void populateData() {
        String[] titles = getResources().getStringArray(R.array.news_titles);

        for (int i = 0; i < titles.length; i++) {
            topNewsList.add(new NewsArticle(getContext(), titles[i]));
        }

        adapter.notifyDataSetChanged();
    }

    View.OnClickListener testDBHander = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            DBTest.testConnection();
        }
    };
}
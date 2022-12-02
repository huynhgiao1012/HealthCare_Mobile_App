package com.example.healthcareapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewsFragment extends Fragment {
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private ArrayList<NewsArticle> newsArticles;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = getView().findViewById(R.id.newsRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        newsArticles = new ArrayList<>();
        adapter = new NewsAdapter(this.getContext(), newsArticles);
        recyclerView.setAdapter(adapter);

        populateData();
    }

    // TODO: Replace this method with logics that pull data from articles' API
    private void populateData() {
        String[] titles = getResources().getStringArray(R.array.news_titles);

        for (int i = 0; i < titles.length; i++) {
            newsArticles.add(new NewsArticle(getContext(), titles[i]));
        }

        adapter.notifyDataSetChanged();
    }
}
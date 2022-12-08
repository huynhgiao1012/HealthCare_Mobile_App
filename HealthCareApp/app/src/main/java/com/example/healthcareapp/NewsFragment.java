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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
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

        try {
            populateData();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    // TODO: Replace this method with logics that pull data from articles' API
    private void populateData() throws IOException, JSONException {
        String GET_URL = "http://192.168.1.7:8080/api/news/getNewsFromApi";
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        String content = "";
        while ((inputLine = in.readLine()) != null) {
            content += inputLine;
        }
        JSONArray newsList = new JSONArray(content);

        for (int i=0; i<newsList.length(); i++) {
            JSONObject news = newsList.getJSONObject(i);

            newsArticles.add(new NewsArticle(news.getString("title"), news.getString("description"), 0));
        }
        in.close();


        adapter.notifyDataSetChanged();
    }
}
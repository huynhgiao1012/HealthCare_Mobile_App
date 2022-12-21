package com.example.healthcareapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

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

        topNewsRV = getView().findViewById(R.id.topNewsRV);
        topNewsRV.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        topNewsList = new ArrayList<>();
        adapter = new TopNewsAdapter(getContext(), topNewsList);
        topNewsRV.setAdapter(adapter);

        Handler handler = new Handler();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    populateData();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
        thread.start();
    }

    private void toIntent(Class activityClass) {
        Intent intent = new Intent(getActivity(), activityClass);
        startActivity(intent);
    }

    private void populateData() throws IOException, JSONException {
        String GET_URL = "http://192.168.1.12:8080/api/news/getNewsFromApi";
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        String content = "";
        while ((inputLine = in.readLine()) != null) {
            content += inputLine;
        }
        Log.d("News", content);
        JSONArray newsList = new JSONArray(content);

        for (int i = 0; i < newsList.length(); i++) {
            JSONObject news = newsList.getJSONObject(i);

            topNewsList.add(new NewsArticle(
                    news.getString("title"),
                    news.getString("description"),
                    0));
        }
        in.close();
    }
}
package noskurt.com.hasilat.news;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import noskurt.com.hasilat.R;
import noskurt.com.hasilat.ygznsl.News;
import noskurt.com.hasilat.ygznsl.NewsCollection;

public class NewsFragmentView extends Fragment {

    public static final String TAG = "RecyclerView";
    private List<FeedItem> feedsList;

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter adapter;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (container != null) container.removeAllViews();

        View view = inflater.inflate(R.layout.news_layout, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);

        final String url = String.format(getResources().getString(R.string.news_list_url), 1);
        new DownloadTask().execute(url);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClick(view.getContext(), mRecyclerView, new RecyclerItemClick.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(view.getContext(), NewsOpen.class);
                FeedItem item = feedsList.get(position);
                intent.putExtra("DATA", item);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {
            }
        }));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                new DownloadTask().execute(url);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return view;
    }

    private class DownloadTask extends AsyncTask<String, Void, Integer> {
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Integer doInBackground(String... params) {
            Integer result = 0;
            HttpURLConnection urlConnection;

            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int statusCode = urlConnection.getResponseCode();

                if (statusCode == 200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
                    parseResult(response.toString());
                    result = 1;
                } else {
                    result = 0;
                }
            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }

            return result;
        }

        @Override
        protected void onPostExecute(Integer result) {
            progressBar.setVisibility(View.GONE);

            if (result == 1) {
                adapter = new RecyclerViewAdapter(getContext(), feedsList);
                mRecyclerView.setAdapter(adapter);
            } else {
                Toast.makeText(getContext(), "Failed to fetch data!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void parseResult(String result) {
        try {
            NewsCollection collection = new NewsCollection(this);
            feedsList = new ArrayList<>();

            for (News news : collection){
                FeedItem item = new FeedItem();
                item.setContent(news.getContent());
                item.setDate(news.getDate());
                item.setPreview(news.getPreviewText());
                item.setThumbnail(news.getImageURL());
                item.setTitle(news.getTitle());
                feedsList.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
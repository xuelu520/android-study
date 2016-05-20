package makyu.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import makyu.view.bean.Actor;

public class RecyclerViewActivity extends AppCompatActivity {
    List<Actor> actors = new ArrayList<Actor>();
    String[] picUrls = {"http://pic2.178.com/58/580446/month_1104/4351c091b76a44fb93257bfbda623ef0.jpg",
            "http://pic2.178.com/58/580446/month_1104/4351c091b76a44fb93257bfbda623ef0.jpg",
            "http://pic2.178.com/58/580446/month_1104/4351c091b76a44fb93257bfbda623ef0.jpg",
            "http://pic2.178.com/58/580446/month_1104/4351c091b76a44fb93257bfbda623ef0.jpg",
            "http://pic2.178.com/58/580446/month_1104/4351c091b76a44fb93257bfbda623ef0.jpg",
            "http://pic2.178.com/58/580446/month_1104/4351c091b76a44fb93257bfbda623ef0.jpg",
            "http://pic2.178.com/58/580446/month_1104/4351c091b76a44fb93257bfbda623ef0.jpg",
            "http://pic2.178.com/58/580446/month_1104/4351c091b76a44fb93257bfbda623ef0.jpg",
            "http://pic2.178.com/58/580446/month_1104/4351c091b76a44fb93257bfbda623ef0.jpg",
            "http://pic2.178.com/58/580446/month_1104/4351c091b76a44fb93257bfbda623ef0.jpg",
            "http://pic2.178.com/58/580446/month_1104/4351c091b76a44fb93257bfbda623ef0.jpg",
            "http://pic2.178.com/58/580446/month_1104/4351c091b76a44fb93257bfbda623ef0.jpg",
    };

    RecyclerView mRecyclerView;
    SwipeRefreshLayout swipeLayout;
    MyRVAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);

        adapter = new MyRVAdapter(getApplicationContext(),picUrls);
        mRecyclerView.setAdapter(adapter);

        // 模拟下拉刷新
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeLayout.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                    }
                }, 2000);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}

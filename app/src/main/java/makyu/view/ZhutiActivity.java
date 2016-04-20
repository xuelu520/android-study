package makyu.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import makyu.view.R;
import makyu.view.help.ZhutiAsyncTask;

public class ZhutiActivity extends AppCompatActivity {
    public TextView textView;
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuti);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String subject = intent.getStringExtra("subject");
//        String author = intent.getStringExtra("author");
        String tpurl = intent.getStringExtra("tpurl");

        initView();
        initData();
    }

    private void initView() {
        this.textView = (TextView) findViewById(R.id.zhutiTextView);
        this.listView = (ListView) findViewById(R.id.zhutiListView);
    }

    private void initData() {
        Intent intent = getIntent();
        String subject = intent.getStringExtra("subject");
        String tpurl = intent.getStringExtra("tpurl");
        textView.setText(subject);
        ZhutiAsyncTask zhutiAsyncTask = new ZhutiAsyncTask(ZhutiActivity.this, listView);
        zhutiAsyncTask.execute(tpurl);
    }
}

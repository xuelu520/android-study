package makyu.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button fragmentDemoButton;
    Button dataDemoButton;
    Button listViewButton;
    Button viewPagerButton;
    Button cardViewButton;
    Button swipeRefreshButton;
    Button myListViewButton;
    Button volleyViewButton;
    Button recyclerViewButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentDemoButton = (Button) findViewById(R.id.fragment_demo);
        dataDemoButton = (Button) findViewById(R.id.data_demo);
        listViewButton = (Button) findViewById(R.id.listView);
        viewPagerButton = (Button) findViewById(R.id.viewPagerView);
        swipeRefreshButton = (Button) findViewById(R.id.swipeRefreshView);
        cardViewButton = (Button) findViewById(R.id.cardView);
        myListViewButton = (Button) findViewById(R.id.myListView);
        volleyViewButton = (Button) findViewById(R.id.VolleyView);
        recyclerViewButton = (Button) findViewById(R.id.RecyclerView);

        fragmentDemoButton.setOnClickListener(this);
        dataDemoButton.setOnClickListener(this);
        listViewButton.setOnClickListener(this);
        viewPagerButton.setOnClickListener(this);
        swipeRefreshButton.setOnClickListener(this);
        cardViewButton.setOnClickListener(this);
        myListViewButton.setOnClickListener(this);
        volleyViewButton.setOnClickListener(this);
        recyclerViewButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            show("点击了设置菜单");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.fragment_demo :
                show("跳转Fragment Demo 页面");
                intent.setClass(MainActivity.this, FragmentActivity.class);
                break;
            case R.id.data_demo :
                show("跳转Data Demo 页面");
                intent.setClass(MainActivity.this, DataActivity.class);
                break;
            case R.id.listView :
                show("跳转ListView Demo 页面");
                intent.setClass(MainActivity.this, ListViewActivity.class);
                break;
            case R.id.viewPagerView :
                show("跳转viewPagerView Demo 页面");
                intent.setClass(MainActivity.this, ViewPagerActivity.class);
                break;
            case R.id.swipeRefreshView :
                show("跳转下拉刷新 Demo 页面");
                intent.setClass(MainActivity.this, SwipeRefreshActivity.class);
                break;
            case R.id.cardView :
                show("跳转卡片式布局 Demo 页面");
                intent.setClass(MainActivity.this, CardViewActivity.class);
                break;
            case R.id.myListView :
                show("跳转自定义listView 适配器页面");
                intent.setClass(MainActivity.this, MyListViewActivity.class);
                break;
            case R.id.VolleyView :
                show("跳转Volley测试页面");
                intent.setClass(MainActivity.this, VolleyActivity.class);
                break;
            case R.id.RecyclerView :
                show("跳转RecyclerView测试页面");
                intent.setClass(MainActivity.this, RecyclerViewActivity.class);
                break;

        }
        MainActivity.this.startActivity(intent);
    }

    private void show(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}

package makyu.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyListViewActivity extends AppCompatActivity implements ListView.OnItemClickListener{
    ListView listView;
    ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();
        initData();

    }

    public void initView() {
        listView = (ListView) findViewById(R.id.myListView2);
    }

    public void initData() {

        for (int i = 1; i <= 50; ++i) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("user_name", "zhangsan" + i);
            map.put("user_ip", "192.168.0." + i);
            list.add(map);
        }
        MyAdapter listAdapter = new MyAdapter(this, list,
                R.layout.user, new String[] { "user_name", "user_ip" },
                new int[] { R.id.user_name,R.id.user_ip});
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Map<String, String> map = list.get(position);
        String userName = map.get("user_name");
        String userIp = map.get("user_ip");
        Toast.makeText(MyListViewActivity.this, userName + " : " + userIp, Toast.LENGTH_SHORT).show();
    }
}

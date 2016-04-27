package makyu.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import makyu.view.bean.Phone;

public class MyListViewActivity extends AppCompatActivity{
    ListView listView;
    private List<Phone> mDatas;
    private MyAdapter mAdapter;

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
        mDatas = new ArrayList<Phone>();
        //将数据装到集合中去

        for(int i = 1; i <= 30; ++i) {
            Phone phone = new Phone("Android新技能" + i,
                    "Android为ListView和GridView打造万能适配器", "2015-05-04", "10086");
            mDatas.add(phone);
        }

        //为数据绑定适配器
        mAdapter = new MyAdapter(this,mDatas);
        listView.setAdapter(mAdapter);
    }
}

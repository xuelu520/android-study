package makyu.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import makyu.view.util.Http;
import makyu.view.util.TieziHelper;

/**
 * Created by -(^_^)- on 2016/4/14.
 */
public class NetAsyncTask extends AsyncTask<Integer, Integer, String> {
    private TextView textView;
    private ListView listView;
    private AppCompatActivity activity;
    private List<Map<String, String>> list = new ArrayList<Map<String, String>>();

    private static final String RANK_API_URL = "http://bbs.nga.cn/thread.php?fid=335&lite=js";
    private static final String ACTIVITY_TAG = "NetworkDemo";

    public NetAsyncTask(TextView textView) {
        super();
        this.textView = textView;
    }

    public NetAsyncTask(ListView listView) {
        super();
        this.listView = listView;
    }

    public NetAsyncTask(AppCompatActivity a, ListView listView){
        super();
        this.listView = listView;
        this.activity = a;
    }

    @Override
    protected String doInBackground(Integer... params) {
        return getData();
    }

    /**
     * 这里的String参数对应AsyncTask中的第三个参数（也就是接收doInBackground的返回值）
     * 在doInBackground方法执行结束之后在运行，并且运行在UI线程当中 可以对UI空间进行设置
     */
    @Override
    protected void onPostExecute(String result) {

        TieziHelper thlper = new TieziHelper();
        Map.Entry[] entries = thlper.parse(result);
        for (Map.Entry<String, JSONObject> entry : entries) {
            Map<String, String> keyValuePair = new HashMap<String, String>();
            Map map = (Map)entry.getValue();
            System.out.println(entry.getKey() + ":" + ((Map)entry.getValue()).get("subject"));
            keyValuePair.put("subject", map.get("subject").toString());
            keyValuePair.put("author", map.get("author").toString());
            keyValuePair.put("tpcurl", map.get("tpcurl").toString());
            list.add(keyValuePair);
        }
        ListAdapter adapter = new SimpleAdapter(activity, list,
                android.R.layout.simple_list_item_2, new String[] { "subject",
                "author" }, new int[] { android.R.id.text1,
                android.R.id.text2 });
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Map<String, String> map = list.get(position);
                String subject = map.get("subject");
                String author = map.get("author");
                String tpcurl = map.get("tpcurl");
                Toast.makeText(activity, subject + "/" + author + ">>" + tpcurl, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("subject", subject);
                intent.putExtra("author", author);
                intent.putExtra("tpcurl", tpcurl);
                intent.setClass(activity, ZhutiActivity.class);
                activity.startActivity(intent);
            }
        });
    }


    //该方法运行在UI线程当中,并且运行在UI线程当中 可以对UI空间进行设置
    @Override
    protected void onPreExecute() {
//        textView.setText("开始执行异步线程");
        Toast.makeText(activity, "开始执行异步线程", Toast.LENGTH_SHORT).show();
    }

    public String getData() {
        Http http = new Http();
        return http.getData(RANK_API_URL);
    }
}

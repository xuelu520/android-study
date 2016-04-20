package makyu.view.help;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import makyu.view.ZhutiActivity;
import makyu.view.util.Constant;
import makyu.view.util.Http;
import makyu.view.util.TieziHelper;
import makyu.view.util.ZhutiHelper;

/**
 * Created by -(^_^)- on 2016/4/19.
 */
public class ZhutiAsyncTask extends AsyncTask<Integer, Integer, String> {
    String url;
    Activity activity;
    ListView listView;
    private List<Map<String, String>> list = new ArrayList<Map<String, String>>();
    private Map users;
    public ZhutiAsyncTask(AppCompatActivity activity, ListView listView, String url){
        super();
        this.activity = activity;
        this.listView = listView;
        this.url = url;
    }

    @Override
    protected String doInBackground(Integer... params) {
        return getData();
    }

    private String getData() {
        String url = Constant.NGA_URL + this.url + Constant.URL_END;
        Http http = new Http();
        return http.getData(url);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        ZhutiHelper thlper = new ZhutiHelper();
        HashMap res = thlper.parse(result);

        Map.Entry[] entries = (Map.Entry[]) res.get("__T");
        users = (Map)res.get("__U");


        for (Map.Entry<String, JSONObject> entry : entries) {
            Map keyValuePair = new HashMap();
            Map map = (Map)entry.getValue();
            String content = map.get("content").toString();
            keyValuePair.put("content", Html.fromHtml(content));
            String username = ((Map)users.get(map.get("authorid").toString())).get("username").toString();
            keyValuePair.put("username", username);
            list.add(keyValuePair);
        }
        ListAdapter adapter = new SimpleAdapter(activity, list,
                android.R.layout.simple_list_item_2, new String[] { "content","username"
                 }, new int[] { android.R.id.text1,
                android.R.id.text2 });
        listView.setAdapter(adapter);
    }
}

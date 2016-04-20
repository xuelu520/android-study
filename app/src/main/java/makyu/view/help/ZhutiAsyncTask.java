package makyu.view.help;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import makyu.view.util.Http;

/**
 * Created by -(^_^)- on 2016/4/19.
 */
public class ZhutiAsyncTask extends AsyncTask<Integer, Integer, String> {
    String url;
    Activity activity;
    ListView listView;

    public ZhutiAsyncTask(AppCompatActivity activity, ListView listView, String url){
        super();
        this.activity = activity;
        this.listView = listView;
        this.url = url;
    }

    @Override
    protected String doInBackground(Integer... params) {
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}

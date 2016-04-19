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
    Activity activity;
    ListView listView;

    @Override
    protected String doInBackground(Integer... params) {
        Http http = new Http();
        http.getData();
        return null;
    }

    public ZhutiAsyncTask(AppCompatActivity activity, ListView listView){
        super();
        this.listView = listView;
        this.activity = activity;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}

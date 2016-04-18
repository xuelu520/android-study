package makyu.view;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import makyu.view.util.TieziHelper;

/**
 * Created by -(^_^)- on 2016/4/14.
 */
public class NetAsyncTask extends AsyncTask<Integer, Integer, String> {
    private TextView textView;
    private static final String RANK_API_URL = "http://bbs.nga.cn/thread.php?fid=335&lite=js";
    private static final String ACTIVITY_TAG = "NetworkDemo";

    public NetAsyncTask(TextView textView) {
        super();
        this.textView = textView;
    }

    @Override
    protected String doInBackground(Integer... params) {
        String msg = getData();
        return msg;
    }

    /**
     * 这里的String参数对应AsyncTask中的第三个参数（也就是接收doInBackground的返回值）
     * 在doInBackground方法执行结束之后在运行，并且运行在UI线程当中 可以对UI空间进行设置
     */
    @Override
    protected void onPostExecute(String result) {
        textView.setText("异步操作执行结束\n" + result);

    }


    //该方法运行在UI线程当中,并且运行在UI线程当中 可以对UI空间进行设置
    @Override
    protected void onPreExecute() {
        textView.setText("开始执行异步线程");
    }

    public String getData() {
        String msg;
        try {
            URL url = new URL(RANK_API_URL);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置超时
            conn.setConnectTimeout(6 * 1000);
            conn.setRequestProperty("Charset", "UTF-8");
            //对响应码进行判断
            int code = conn.getResponseCode();
            if(code != 200) {
                msg = "响应码为：" + code;
            }else{
                //流转换
                InputStream inputStream = conn.getInputStream();
                String res = streamToString(inputStream);
                msg = "响应码为：" + code + "\n" + res;

                TieziHelper thlper = new TieziHelper();
                thlper.parse(res);
            }
        } catch (Exception e) {
            msg = "网络错误。";
            e.printStackTrace();
        }
        return msg;
    }



    /**
     * 将输入流转换成字符串
     * @param is
     * @return
     */
    public static String streamToString(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            is.close();
            byte[] byteArray = baos.toByteArray();
            return new String(byteArray, "gbk");
        } catch (Exception e) {
            Log.e(ACTIVITY_TAG, e.toString());
            return null;
        }
    }
}

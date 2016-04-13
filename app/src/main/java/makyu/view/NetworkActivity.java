package makyu.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String RANK_API_URL = "http://yige.cc/api/rs_rank100";
    private static final String ACTIVITY_TAG = "NetworkDemo";

    public Button getDataButton;
    public TextView text;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            text.setText((String) msg.obj);
        }

    };

    Runnable newTread = new Runnable() {
        @Override
        public void run() {
            Message msg = handler.obtainMessage();
            try {
                URL url = new URL(RANK_API_URL);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                //设置超时
                conn.setConnectTimeout(6 * 1000);
                conn.setRequestProperty("Charset", "UTF-8");
                //对响应码进行判断
                int code = conn.getResponseCode();
                if(code != 200) {
                    throw new RuntimeException("请求url失败");
                }
                //流转换
                InputStream inputStream = conn.getInputStream();
                String res = streamToString(inputStream);
                msg.obj = "响应码为：" + code + "\n" + res;
                handler.sendMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(NetworkActivity.this, "访问网络失败", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        text = (TextView) findViewById(R.id.text_res);

        getDataButton = (Button) findViewById(R.id.getNetData);
        getDataButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getNetData :
                getData();
                break;
        }
    }

    private void getData() {
        Thread t = new Thread(newTread);
        t.start();
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
            return new String(byteArray);
        } catch (Exception e) {
            Log.e(ACTIVITY_TAG, e.toString());
            return null;
        }
    }
}

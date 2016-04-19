package makyu.view.util;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by -(^_^)- on 2016/4/19.
 */
public class Http {
    public String getData(String link) {
        String res = "";
        try {
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置超时
            conn.setConnectTimeout(6 * 1000);
            conn.setRequestProperty("Charset", "UTF-8");
            //对响应码进行判断
            int code = conn.getResponseCode();
            if(code == 200) {
                //流转换
                InputStream inputStream = conn.getInputStream();
                res = streamToString(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
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
            return null;
        }
    }
}

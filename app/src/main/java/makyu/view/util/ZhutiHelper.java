package makyu.view.util;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import makyu.view.Model.Bankuai;
import makyu.view.Model.Tiezi;

/**
 * Created by -(^_^)- on 2016/4/19.
 */
public class ZhutiHelper {
    public static final String tzURL = "http://bbs.nga.cn/thread.php?fid=335&lite=xml";
    private final static String TAG = TieziHelper.class.getSimpleName();
    // 使用栈这个数据结构来保存
    private Stack<String> stack = new Stack<String>();

    // 数据
    private static final int INIT_COUNT = 0; //初始化总数量
    private static final int INIT_PER_PAGE = 25; //初始化每页数量
    private static final int INIT_TOTAL_PAGE = 0; //初始化总页数

    public HashMap parse(String js) {
        String start = "get_var_store=";
        String dataString = js.substring(js.indexOf(start)+14);

        JSONObject o = null;
        try {
            o = (JSONObject) JSON.parseObject(dataString).get("data");
        } catch (Exception e) {
            Log.e(TAG, "can not parse :\n" + js);
        }
        if (o == null) {
            return null;
        }

        JSONObject data;
        data = (JSONObject) o.get("__R");
        if (data == null)
            return null;
        Set set = data.entrySet();
        Map.Entry[] entries = (Map.Entry[]) set.toArray(new Map.Entry[set.size()]);
        Arrays.sort(entries, new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry lhs, Map.Entry rhs) {
                Integer left = Integer.parseInt(lhs.getKey().toString());
                Integer right = Integer.parseInt(rhs.getKey().toString());
                return left > right ? 1 : -1;
            }
        });

        HashMap hashMap = new HashMap();
        hashMap.put("__T", entries);
        hashMap.put("__U", o.get("__U"));
        return hashMap;
    }
}

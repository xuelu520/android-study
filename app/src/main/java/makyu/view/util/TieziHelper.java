package makyu.view.util;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import makyu.view.Model.Bankuai;
import makyu.view.Model.Tiezi;

/**
 * Created by -(^_^)- on 2016/4/18.
 */
public class TieziHelper{
    public static final String tzURL = "http://bbs.nga.cn/thread.php?fid=335&lite=xml";
    private final static String TAG = TieziHelper.class.getSimpleName();
    // 使用栈这个数据结构来保存
    private Stack<String> stack = new Stack<String>();

    // 数据
    private static final int INIT_COUNT = 0; //初始化总数量
    private static final int INIT_PER_PAGE = 25; //初始化每页数量
    private static final int INIT_TOTAL_PAGE = 0; //初始化总页数

    private List<Bankuai> bankuais = null;
    private HashMap<String,Object> TieziData = null;
    private List<Tiezi> tiezis = null;
    private Tiezi tiezi;
    private String elementName = null;

    public List<Bankuai> getBankuais() {
        return bankuais;
    }

    public List<Tiezi> getTiezis() {
        return tiezis;
    }

    {
        initTiziData();
    }

    private void initTiziData() {
        TieziData = new HashMap<>();
        TieziData.put("data", new ArrayList<Tiezi>());
//        TieziData.put("count", INIT_COUNT);
//        TieziData.put("perPage", INIT_PER_PAGE);
//        TieziData.put("totalPage", INIT_TOTAL_PAGE);
    }

    public String parse(String js) {
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
        data = (JSONObject) o.get("__T");
        if (data == null)
            return null;
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        return null;
    }
}

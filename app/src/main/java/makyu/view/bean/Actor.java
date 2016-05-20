package makyu.view.bean;

import android.content.Context;

/**
 * Created by -(^_^)- on 2016/5/19.
 */
public class Actor {
    public String name;
    public String picName;

    public Actor(String name, String picName) {
        this.name = name;
        this.picName = picName;
    }

    public int getImgResourceId (Context context) {
        try {
            return context.getResources().getIdentifier(this.picName, "drawable", context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}

package makyu.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

/**
 * Created by -(^_^)- on 2016/4/26.
 */
public class MyAdapter extends SimpleAdapter{

    private LayoutInflater inflater = null;
    private List<Map<String, Object>> styles = null;

    public List<Map<String, Object>> getStyles() {
        return  styles;
    }

    public void setStyles(List<Map<String, Object>> styles) {
        this.styles = styles;
    }

    public MyAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        if(view != null) {
            inflater.inflate(R.layout.user, null);
        }
        return view;
    }
}

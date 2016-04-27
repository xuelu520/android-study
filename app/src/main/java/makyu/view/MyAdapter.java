package makyu.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import makyu.view.bean.Phone;

/**
 * Created by -(^_^)- on 2016/4/26.
 */
public class MyAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private List<Phone> mDatas;

    public MyAdapter(Context context, List<Phone> datas) {
        this.mInflater = LayoutInflater.from(context);
        this.mDatas    = datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.my_list_view_item, parent, false); //加载布局
            holder = new ViewHolder();

            holder.titleTv = (TextView) convertView.findViewById(R.id.titleTv);
            holder.descTv = (TextView) convertView.findViewById(R.id.descTv);
            holder.timeTv = (TextView) convertView.findViewById(R.id.timeTv);
            holder.phoneTv = (TextView) convertView.findViewById(R.id.phoneTv);

            convertView.setTag(holder);
        } else {
            //else里面说明，convertView已经被复用了，说明convertView中已经设置过tag了，即holder
            holder = (ViewHolder) convertView.getTag();
        }

        Phone phone = mDatas.get(position);
        holder.titleTv.setText(phone.getTitle());
        holder.descTv.setText(phone.getDesc());
        holder.timeTv.setText(phone.getTime());
        holder.phoneTv.setText(phone.getPhone());

        return convertView;
    }

    private class ViewHolder {
        TextView titleTv;
        TextView descTv;
        TextView timeTv;
        TextView phoneTv;
     }
}

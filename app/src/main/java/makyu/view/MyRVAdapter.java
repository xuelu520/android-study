package makyu.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.List;

import makyu.view.bean.Actor;

/**
 * Created by -(^_^)- on 2016/5/19.
 */
public class MyRVAdapter extends RecyclerView.Adapter<MyRVAdapter.DemoViewHolder>{
    private String[] picUrls;

    private Context mContext;

    public MyRVAdapter( Context context , String[] picUrls)
    {
        this.mContext = context;
        this.picUrls = picUrls;
    }


    @Override
    public DemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new DemoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DemoViewHolder holder, int position) {
        displayImg(holder.imavPic, "http://pic2.178.com/58/580446/month_1104/4351c091b76a44fb93257bfbda623ef0.jpg");
        holder.tvUrl.setText("测试");
    }

    @Override
    public int getItemCount()
    {
        // 返回数据总数
        return picUrls == null ? 0 : picUrls.length;
    }

    // 重写的自定义ViewHolder
    // 可复用的VH
    public static class DemoViewHolder extends RecyclerView.ViewHolder {
        // 大图
        public ImageView imavPic;
        // 图片url
        public TextView tvUrl;

        public DemoViewHolder(View itemView) {
            super(itemView);
            imavPic = (ImageView) itemView.findViewById(R.id.imavPic);
            tvUrl = (TextView) itemView.findViewById(R.id.tvUrl);
        }
    }

    public void displayImg(ImageView imageView, String url){
        RequestQueue mQueue = Volley.newRequestQueue(mContext);

        ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());

        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView,R.drawable.ic_phone_black_18dp, R.drawable.ic_phone_black_18dp);
        imageLoader.get(url, listener);
    }

    public class BitmapCache implements ImageLoader.ImageCache {

        private LruCache<String, Bitmap> cache;

        public BitmapCache() {
            cache = new LruCache<String, Bitmap>(8 * 1024 * 1024) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    return bitmap.getRowBytes() * bitmap.getHeight();
                }
            };
        }

        @Override
        public Bitmap getBitmap(String url) {
            return cache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            cache.put(url, bitmap);
        }
    }
}

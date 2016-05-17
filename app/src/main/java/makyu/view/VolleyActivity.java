package makyu.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class VolleyActivity extends AppCompatActivity implements View.OnClickListener{
    Button getRequestButton;
    Button getImgButton;
    Button imgLoaderButton;
    ImageView imgView;
    NetworkImageView networkImageView;
    RequestQueue mQueue;
    ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initVolley();
        initView();

    }

    private void initVolley() {
        mQueue = Volley.newRequestQueue(getApplicationContext());
    }

    private void initView() {
        getRequestButton = (Button) findViewById(R.id.getRequest);
        getRequestButton.setOnClickListener(this);

        getImgButton = (Button) findViewById(R.id.getImage);
        getImgButton.setOnClickListener(this);

        imgLoaderButton = (Button) findViewById(R.id.imgLoader);
        imgLoaderButton.setOnClickListener(this);

        imgView = (ImageView) findViewById(R.id.img);
        networkImageView = (NetworkImageView) findViewById(R.id.network_image_view);
        byNetworkImageView("http://pic2.178.com/58/580446/month_1104/4351c091b76a44fb93257bfbda623ef0.jpg");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getRequest :
                getRequest("http://data.marathon.tysoul.com/api/rs_money20");
                break;
            case R.id.getImage :
                getImg("http://pic2.178.com/58/580446/month_1104/4351c091b76a44fb93257bfbda623ef0.jpg");
                break;

            case R.id.imgLoader :
                byImageLoader("http://pic2.178.com/58/580446/month_1104/4351c091b76a44fb93257bfbda623ef0.jpg");
                break;
        }
    }

    /**
     * 获取图片
     * @param imgUrl
     */
    private void getImg(String imgUrl) {
        ImageRequest imageRequest = new ImageRequest(
                imgUrl,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imgView.setImageBitmap(response);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VolleyTag", error.getMessage());
                imgView.setImageResource(R.drawable.ic_phone_black_18dp);
            }
        });
        mQueue.add(imageRequest);
    }

    /**
     * 正常请求数据
     */
    private void getRequest(String url) {
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", response);
                        Toast.makeText(VolleyActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG", error.getMessage(), error);
                        Toast.makeText(VolleyActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        mQueue.add(stringRequest);
    }

    private void byImageLoader(String url) {
        imageLoader = new ImageLoader(mQueue, new BitmapCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imgView,
                R.drawable.ic_phone_black_18dp,
                R.drawable.ic_phone_black_18dp);

        imageLoader.get(url, listener);
    }

    private void byNetworkImageView(String url) {
        imageLoader = new ImageLoader(mQueue, new BitmapCache());
        networkImageView.setDefaultImageResId(R.drawable.ic_phone_black_18dp);
        networkImageView.setErrorImageResId(R.drawable.ic_phone_black_18dp);
        networkImageView.setImageUrl(url,
                imageLoader);
    }
}

class BitmapCache implements ImageLoader.ImageCache {

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

package makyu.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import makyu.view.util.VolleyController;

public class VolleyActivity extends AppCompatActivity implements View.OnClickListener{
    Button getRequestButton;
    Button getImgButton;
    TextView resultTv;
    ImageView imgView;
    RequestQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();

    }

    private void initView() {
        mQueue = Volley.newRequestQueue(getApplicationContext());
        getRequestButton = (Button) findViewById(R.id.getRequest);
        getRequestButton.setOnClickListener(this);

        getImgButton = (Button) findViewById(R.id.getImage);
        getImgButton.setOnClickListener(this);
        resultTv = (TextView) findViewById(R.id.result);
        imgView = (ImageView) findViewById(R.id.img);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getRequest :
                getRequest();
                break;
            case R.id.getImage :
                getImg("http://pic2.178.com/58/580446/month_1104/4351c091b76a44fb93257bfbda623ef0.jpg", imgView);
                break;
        }
    }

    private void getImg(String imgUrl, final ImageView imgView) {
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

    private void getRequest() {
        StringRequest stringRequest = new StringRequest("http://data.marathon.tysoul.com/api/rs_money20",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", response);
                        resultTv.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
                resultTv.setText(error.getMessage());
            }
        });
        mQueue.add(stringRequest);
    }
}

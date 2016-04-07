package makyu.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DataActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        et = (EditText) findViewById(R.id.et);
        Button bt = (Button) findViewById(R.id.bt);
        Button bt2 = (Button) findViewById(R.id.bt2);
        bt.setOnClickListener(this);
        bt2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt:
                String etString = et.getText().toString();
                saveData(this,etString);
                break;
            case R.id.bt2:
                loadData(this);
                break;
            default:
                break;
        }

    }
    private void loadData(Context context) {
        SharedPreferences sp = context.getSharedPreferences("config", MODE_PRIVATE);
        Toast.makeText(this, sp.getString("content", "").toString(), Toast.LENGTH_SHORT).show();
    }
    private void saveData(Context context,String string){
        SharedPreferences sp = context.getSharedPreferences("config", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("content", string);
        editor.apply();
    }

}

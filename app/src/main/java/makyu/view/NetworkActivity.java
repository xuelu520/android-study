package makyu.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NetworkActivity extends AppCompatActivity implements View.OnClickListener{

    public Button getDataButton;
    public TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        text = (TextView) findViewById(R.id.text_res);

        getDataButton = (Button) findViewById(R.id.getNetData);
        getDataButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getNetData :
                NetAsyncTask asyncTask = new NetAsyncTask(text);
                asyncTask.execute();
                break;
        }
    }
}

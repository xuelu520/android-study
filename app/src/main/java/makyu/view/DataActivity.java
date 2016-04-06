package makyu.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class DataActivity extends AppCompatActivity {

    public Button addButton;
    public Button getButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addButton = (Button) findViewById(R.id.addPerferenceData);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPerferenceData();
            }
        });

        getButton = (Button) findViewById(R.id.getPerferenceData);
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String data = getPerferenceData();
            }
        });
    }

    private void addPerferenceData() {

    }

    private String getPerferenceData() {
        return "";
    }

}

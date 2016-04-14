package makyu.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button fragmentDemoButton;
    private Button dataDemoButton;
    private Button listViewButton;
    private Button netViewButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentDemoButton = (Button) findViewById(R.id.fragment_demo);
        dataDemoButton = (Button) findViewById(R.id.data_demo);
        listViewButton = (Button) findViewById(R.id.listView);
        netViewButton = (Button) findViewById(R.id.netView);

        fragmentDemoButton.setOnClickListener(this);
        dataDemoButton.setOnClickListener(this);
        listViewButton.setOnClickListener(this);
        netViewButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            show("点击了设置菜单");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.fragment_demo :
                show("跳转Fragment Demo 页面");
                intent.setClass(MainActivity.this, FragmentActivity.class);
                break;
            case R.id.data_demo :
                show("跳转Data Demo 页面");
                intent.setClass(MainActivity.this, DataActivity.class);
                break;
            case R.id.listView :
                show("跳转ListView Demo 页面");
                intent.setClass(MainActivity.this, ListViewActivity.class);
                break;
            case R.id.netView :
                show("跳转NetworkView Demo 页面");
                intent.setClass(MainActivity.this, NetworkActivity.class);
                break;
        }
        MainActivity.this.startActivity(intent);
    }

    private void show(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}

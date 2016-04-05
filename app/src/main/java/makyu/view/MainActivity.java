package makyu.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button addFragment1;
    Button addFragment2;
    Button removeFragment2;
    Button relpaceFragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addFragment1 = (Button) findViewById(R.id.btn_add_frag1);
        addFragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment1 fragment1 = new Fragment1();
                addFragment(fragment1, "fragment1");
                Toast.makeText(MainActivity.this, "添加Fragment1", Toast.LENGTH_SHORT).show();
            }
        });

        addFragment2 = (Button) findViewById(R.id.btn_add_frag2);
        addFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment2 fragment2 = new Fragment2();
                addFragment(fragment2, "fragment2");
                Toast.makeText(MainActivity.this, "添加Fragment2", Toast.LENGTH_SHORT).show();
            }
        });

        removeFragment2 = (Button) findViewById(R.id.btn_remove_frag2);
        removeFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragment2();
                Toast.makeText(MainActivity.this, "移除Fragment2", Toast.LENGTH_SHORT).show();
            }
        });

        relpaceFragment1 = (Button) findViewById(R.id.btn_repalce_frag1);
        relpaceFragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment1();
                Toast.makeText(MainActivity.this, "替换Fragment1", Toast.LENGTH_SHORT).show();
            }
        });
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
            Toast.makeText(MainActivity.this, "点击了设置菜单", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void addFragment(Fragment fragment, String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_container, fragment, tag);
        transaction.commit();
    }

    private void removeFragment2() {
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentByTag("fragment2");
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(fragment);
        transaction.commit();
    }

    private void replaceFragment1() {
        FragmentManager manager = getSupportFragmentManager();
        Fragment2 fragment2 = new Fragment2();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment2);
        transaction.commit();
    }
}

package makyu.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FragmentActivity extends AppCompatActivity {

    Button addFragment1;
    Button addFragment2;
    Button removeFragment2;
    Button relpaceFragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addFragment1 = (Button) findViewById(R.id.btn_add_frag1);
        addFragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment1 fragment1 = new Fragment1();
                addFragment(fragment1, "fragment1");
                Toast.makeText(FragmentActivity.this, "添加Fragment1", Toast.LENGTH_SHORT).show();
            }
        });

        addFragment2 = (Button) findViewById(R.id.btn_add_frag2);
        addFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment2 fragment2 = new Fragment2();
                addFragment(fragment2, "fragment2");
                Toast.makeText(FragmentActivity.this, "添加Fragment2", Toast.LENGTH_SHORT).show();
            }
        });

        removeFragment2 = (Button) findViewById(R.id.btn_remove_frag2);
        removeFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragment2();
                Toast.makeText(FragmentActivity.this, "移除Fragment2", Toast.LENGTH_SHORT).show();
            }
        });

        relpaceFragment1 = (Button) findViewById(R.id.btn_repalce_frag1);
        relpaceFragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment1();
                Toast.makeText(FragmentActivity.this, "替换Fragment1", Toast.LENGTH_SHORT).show();
            }
        });
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

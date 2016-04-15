package makyu.view;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {
    private View view1;
    private View view2;
    private View view3;
    private ViewPager viewPager;
    private List<View> viewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();
        initData();
    }

    private void initView() {
        LayoutInflater inflater = getLayoutInflater();
        view1 = inflater.inflate(R.layout.content_main, null);
        view2 = inflater.inflate(R.layout.content_main, null);
        view3 = inflater.inflate(R.layout.content_main, null);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewList = new ArrayList<View>();
    }

    private void initData() {
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewPager.setAdapter(pagerAdapter);
    }

    //适配器
    private PagerAdapter pagerAdapter = new PagerAdapter() {
        //返回当前视图的个数
        @Override
        public int getCount() {
            return viewList.size();
        }

        /**
         * 功能：该函数用来判断instantiateItem(ViewGroup, int)函数所返回来的Key与一个页面视图是否是代表的同一个视图(即它俩是否是对应的，对应的表示同一个View)
         *返回值：如果对应的是同一个View，返回True，否则返回False。
         * @param view
         * @param object
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
            //return view == viewList.get((int) Integer.parseInt(object.toString()));
        }

        //移除当前某个视图
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }

        /**
         * 在这里，我们做了两件事
         *第一：将参数里给定的position的视图，增加到conatiner中，供其创建并显示、。
         * 第二：返回当前position的View做为此视图的Key。
         * @param container
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
            //return position;
        }
    };
}

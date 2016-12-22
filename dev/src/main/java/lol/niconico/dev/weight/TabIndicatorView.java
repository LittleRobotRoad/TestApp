package lol.niconico.dev.weight;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import lol.niconico.dev.R;
import lol.niconico.dev.util.DiUtil;


/**
 * Created by ZhangQianqian on 2016/11/12  20:00.
 * email 415692689@qq.com
 */

public class TabIndicatorView extends RelativeLayout implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private static final String TAG = TabIndicatorView.class.getSimpleName();

    private ViewPager viewPager;
    private final TextView tvItem1;
    private final TextView tvItem2;
    private final View indicator;
    private ObjectAnimator x;

    public TabIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.widget_tablayout, this, true);
        indicator = findViewById(R.id.v_indicator);
        tvItem1 = (TextView) findViewById(R.id.tvItem1);
        tvItem2 = (TextView) findViewById(R.id.tvItem2);
        tvItem1.setOnClickListener(this);
        tvItem2.setOnClickListener(this);

    }

    public void setViewPager(ViewPager viewPager) {


        final int screenWidth = getScreenWidth();
        final int start = screenWidth / 4 - DiUtil.dip2px(40);
        final int end = screenWidth / 4 * 3 -DiUtil.dip2px(40);
        this.viewPager = viewPager;
        tvItem1.setText(viewPager.getAdapter().getPageTitle(0));
        tvItem2.setText(viewPager.getAdapter().getPageTitle(1));
        x = ObjectAnimator.ofFloat(indicator, "x", start, end);
        x.setDuration(500);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset > 0.02f) {
            x.setCurrentPlayTime((long) (positionOffset * 500));
        }
    }

    @Override
    public void onPageSelected(int position) {
        toggle(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void toggle(int position) {
        switch (position) {
            case 0:
                tvItem1.setTextColor(getResources().getColor(R.color.app_main_color));
                tvItem2.setTextColor(getResources().getColor(R.color.black));
                break;
            case 1:
                tvItem1.setTextColor(getResources().getColor(R.color.black));
                tvItem2.setTextColor(getResources().getColor(R.color.app_main_color));
                break;
        }

    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tvItem1) {
            toggle(0);
            viewPager.setCurrentItem(0);

        } else if (i == R.id.tvItem2) {
            toggle(1);
            viewPager.setCurrentItem(1);

        }
    }

    public int getScreenWidth() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();// 创建了一张白纸
        windowManager.getDefaultDisplay().getMetrics(dm);// 给白纸设置宽高
        return dm.widthPixels;
    }
}

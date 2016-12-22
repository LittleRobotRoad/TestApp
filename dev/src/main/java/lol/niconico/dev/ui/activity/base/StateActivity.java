package lol.niconico.dev.ui.activity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import lol.niconico.dev.R;


/**
 * 1. Activity定义了许多的状态，用户可以通过set(status)来切换状态，每个状态对应一个View，用户还可以自定义这个View
 * <p>
 * 每个状态对应一个UI 布局,用户可以自定义这些布局,通过setXX方法（e.g setEmptyView）
 * <p>
 * 2. 提供了title的控制,User可以使用Toolbar,也可以自定义自己的tool bar, Activity默认显示toolbar
 *
 */
public abstract class StateActivity extends AppCompatActivity implements IComponentStatable {

    private RelativeLayout mContentView;
    private ComponentStateHelper mHelper;
    private CompState mCompStatus = CompState.DATA;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(currentTheme());
        setContentView(R.layout.ac_base_status);

        mContentView = (RelativeLayout) findViewById(R.id.rlytContent);
        // 设置标题栏
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getToolbar().setNavigationIcon(R.drawable.icon_back);

        mHelper = new ComponentStateHelper(this, mContentView);
        View newContentView = createDataView(getLayoutInflater(), null, savedInstanceState);
        mHelper.setDataContentView(newContentView);
        mHelper.setEmptyErrorView(createEmptyErrorView((getLayoutInflater())));
        mHelper.setEmptyInvalidNetView(createEmptyInvalidNetView((getLayoutInflater())));
        mHelper.setEmptyRefreshing(createEmptyRefreshingView((getLayoutInflater())));
        mHelper.setEmptyView(createEmptyView((getLayoutInflater())));
        mHelper.changeState(mCompStatus);

    }


    public Toolbar getToolbar() {
        return mToolbar;
    }


    /**
     * 请确保在onCreateView之后调用
     *
     * @param state
     */
    public void setState(CompState state) {
        if (mCompStatus != state) {
            mCompStatus = state;
            mHelper.changeState(mCompStatus);
        }
    }


    public CompState getState() {
        return mCompStatus;
    }


    public View getEmptyView() {
        return mHelper.getEmptyView();
    }


    public View getDataView() {
        return mHelper.getDataContentView();
    }


    public View getEmptyRefreshingView() {
        return mHelper.getEmptyRefreshingView();
    }


    public View getEmptyInvalidNetView() {
        return mHelper.getEmptyInvalidNetView();
    }


    public View getEmptyErrorView() {
        return mHelper.getEmptyErrorView();
    }


    public View createEmptyView(LayoutInflater inflater) {
        return mHelper.getEmptyView();
    }


    public View createEmptyInvalidNetView(LayoutInflater inflater) {
        return mHelper.getEmptyInvalidNetView();
    }


    public View createEmptyRefreshingView(LayoutInflater inflater) {
        return mHelper.getEmptyRefreshingView();
    }


    public View createEmptyErrorView(LayoutInflater inflater) {
        return mHelper.getEmptyErrorView();
    }


    /**
     * 设置自己的title
     *
     * @param title
     */
    public void setCustomToolbar(View title) {
        // hide toolbar
        getToolbar().removeAllViews();
        getToolbar().setVisibility(View.GONE);

        // show custom bar
        FrameLayout lytCustomTitleContainer = (FrameLayout) findViewById(R.id.lytCustomTitleContainer);
        lytCustomTitleContainer.addView(title);

		/*Toolbar.LayoutParams params = new Toolbar.LayoutParams(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.MATCH_PARENT);
        getToolbar().addView(title, params);*/

    }

    public View getCustomToolbar() {
        FrameLayout lytCustomTitleContainer = (FrameLayout) findViewById(R.id.lytCustomTitleContainer);
        return lytCustomTitleContainer.getChildAt(0);
    }


    /**
     * 隐藏标题栏， 不论是custom的还是toolbar
     */
    public void hideToolbar() {
        View customToolbar = getCustomToolbar();
        if (customToolbar != null && customToolbar.getVisibility() == View.VISIBLE) {
            customToolbar.setVisibility(View.GONE);
        }

        if (getToolbar() != null && getToolbar().getVisibility() == View.VISIBLE) {
            getToolbar().setVisibility(View.GONE);
        }
    }


    public int currentTheme() {
        return -1;
    }

}

package lol.niconico.dev.ui.activity.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import lol.niconico.dev.R;

/**
 * 组件状态的辅助类
 *
 * 1. 提供set和get各种状态UI的方法
 * 2. 提供缺省的UI
 */
public class ComponentStateHelper {

    private final static int POSI_EMPTY = 0;
    private final static int POSI_EMPTY_INVALIDE_NET = 1;
    private final static int POSI_DATA_CONTENT = 2;
    private final static int POSI_EMPTY_ERROR = 3;
    private final static int POSI_REFRESHING = 4;


    private Context mContext;
    private ViewGroup mMainView;
    private View mViewEmpty;
    private View mViewEmptyInvalidNet;
    private View mViewEmptyErrorView;
    private View mViewEmptyRefreshing;
    private View mViewContent;


    public ComponentStateHelper(Context context, ViewGroup mainView) {
        mContext = context;
        mMainView = mainView;
        addStateViews();
    }


    private void addStateViews() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        mMainView.addView(getEmptyView(), POSI_EMPTY, params);
        mMainView.addView(getEmptyInvalidNetView(), POSI_EMPTY_INVALIDE_NET, params);
        mMainView.addView(getDataContentView(), POSI_DATA_CONTENT, params);
        mMainView.addView(getEmptyErrorView(), POSI_EMPTY_ERROR, params);
        mMainView.addView(getEmptyRefreshingView(), POSI_REFRESHING, params);

    }


    private void addViewAt(int position, View view) {
        mMainView.removeViewAt(position);
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        /*if (lp != null) {
            params.width = lp.width;
            params.height = lp.height;
        }*/

        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        mMainView.addView(view, position, params);
        mMainView.invalidate();
    }


    private View createDefaultContentView() {
        TextView tv = new TextView(mContext);
        tv.setText("Default Content View");
        return tv;
    }

    private View createDefaultEmptyView() {
        return View.inflate(mContext, R.layout.default_empty, null);
    }

    private View createDefaultEmptyRefreshingView() {
        return View.inflate(mContext, R.layout.default_refreshing, null);
    }

    private View createDefaultEmptyInvalidNetView() {
        return View.inflate(mContext, R.layout.default_empty_invalid_net, null);
    }

    private View createDefaultEmptyErrorView() {
        return View.inflate(mContext, R.layout.default_empty_error, null);
    }

    public View getEmptyView() {
        if (mViewEmpty == null) {
            mViewEmpty = createDefaultEmptyView();
        }
        return mViewEmpty;
    }

    public void setEmptyView(View emptyView) {
        mViewEmpty = emptyView;
        addViewAt(POSI_EMPTY, emptyView);
    }

    public View getDataContentView() {
        if (mViewContent == null) {
            mViewContent = createDefaultContentView();
        }
        return mViewContent;
    }

    public void setDataContentView(View contentView) {
        mViewContent = contentView;
        mMainView.removeViewAt(POSI_DATA_CONTENT);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        mMainView.addView(mViewContent, POSI_DATA_CONTENT, params);
        mMainView.invalidate();
    }

    public View getEmptyRefreshingView() {
        if (mViewEmptyRefreshing == null) {
            mViewEmptyRefreshing = createDefaultEmptyRefreshingView();
        }
        return mViewEmptyRefreshing;
    }

    public void setEmptyRefreshing(View view) {
        mViewEmptyRefreshing = view;
        addViewAt(POSI_REFRESHING, view);
    }

    public View getEmptyInvalidNetView() {
        if (mViewEmptyInvalidNet == null) {
            mViewEmptyInvalidNet = createDefaultEmptyInvalidNetView();
        }
        return mViewEmptyInvalidNet;
    }

    public void setEmptyInvalidNetView(View view) {
        mViewEmptyInvalidNet = view;
        addViewAt(POSI_EMPTY_INVALIDE_NET, view);
    }

    public View getEmptyErrorView() {
        if (mViewEmptyErrorView == null) {
            mViewEmptyErrorView = createDefaultEmptyErrorView();
        }
        return mViewEmptyErrorView;
    }

    public void setEmptyErrorView(View view) {
        mViewEmptyErrorView = view;
        addViewAt(POSI_EMPTY_ERROR, view);
    }

    public void changeState(CompState state) {
        // 要求刷新状态的时候，刷新的UI在覆盖在其他状态UI上。所以切换为刷新状态UI的时候就立刻return，不要将其他状态UI隐藏了。
        if (state == CompState.EMPTY_REFRESHING) {
            getEmptyRefreshingView().setVisibility(View.VISIBLE);
            return;
        } else {
            getEmptyRefreshingView().setVisibility(View.GONE);
        }

        if (state == CompState.EMPTY) {
            getEmptyView().setVisibility(View.VISIBLE);
        } else {
            getEmptyView().setVisibility(View.GONE);
        }

        if (state == CompState.DATA) {
            getDataContentView().setVisibility(View.VISIBLE);
        } else {
            getDataContentView().setVisibility(View.GONE);
        }

        if (state == CompState.EMPTY_INVALID_NEWWORK) {
            getEmptyInvalidNetView().setVisibility(View.VISIBLE);
        } else {
            getEmptyInvalidNetView().setVisibility(View.GONE);
        }

        if (state == CompState.EMPTY_ERROR) {
            getEmptyErrorView().setVisibility(View.VISIBLE);
        } else {
            getEmptyErrorView().setVisibility(View.GONE);
        }
    }


    private View getViewOfState(CompState state) {
        switch (state) {
            case EMPTY:
                return getEmptyView();

            case EMPTY_INVALID_NEWWORK:
                return getEmptyInvalidNetView();

            case EMPTY_REFRESHING:
                return getEmptyRefreshingView();

            case EMPTY_ERROR:
                return getEmptyErrorView();

            case DATA:
                return getDataContentView();

            default:
                return getEmptyView();
        }
    }

}

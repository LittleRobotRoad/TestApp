package lol.niconico.dev.ui.adapter.base;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


import lol.niconico.dev.app.BaseApplication;

import java.util.ArrayList;

public abstract class RecyclerViewAdapter<E, T extends ViewHolder> extends
        RecyclerView.Adapter<ViewHolder> {

    private static final String TAG = RecyclerViewAdapter.class.getSimpleName();

    private static final int FOOTER = -1000;
    private static final int HEADER = -2000;
    private static final int INSERT = -3000;
    private static final int DEFAULT = 0;

    private ArrayList<E> mData;
    private ArrayList<View> mHeaderViews;
    private ArrayList<View> mFooterViews;
    private View mInsertView;
    private int mInsertViewPosition = -1;
    private OnItemClickListener l;

    public void setOnItemClickListener(OnItemClickListener l) {
        this.l = l;
    }

    public interface OnItemClickListener {
        void onItemClick(View views, int position);

        boolean onItemLongClick(View v, int position);
    }

    public Resources getRes() {
        return BaseApplication.mContext.getResources();
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int i) {
        if (isHeader(i) || isFooter(i)) {
            return;
        }
        int position = i - getHeaderSize();
        if (position > mInsertViewPosition && mInsertView != null) {
            position--;
        } else if (position == mInsertViewPosition) {
            return;
        }
        //position大于 data长度时return
        if (getData().size() - 1 < position) {
            Log.w(TAG,"onBindViewHolder position长度超过 datas 长度,返回数据泛型 为 null");
            T t = (T) holder;
            onBindHolder(t, position, null);
            return;
        }

        E e = mData.get(position);
        T t = (T) holder;
        onBindHolder(t, position, e);

    }

    public void addHeader(View v) {
        if (mHeaderViews == null) {
            mHeaderViews = new ArrayList<>();
        }
        mHeaderViews.add(v);
    }

    public int getHeaderSize() {
        if (mHeaderViews == null) return 0;
        else return mHeaderViews.size();
    }

    private boolean isHeader(int position) {
        return getHeaderSize() != 0 && position >= 0 && position < getHeaderSize();
    }

    public void addFooter(View v) {
        if (mFooterViews == null) {
            mFooterViews = new ArrayList<>();
        }
        mFooterViews.add(v);
    }

    public int getFooterSize() {
        if (mFooterViews == null) return 0;
        else return mFooterViews.size();
    }

    private boolean isFooter(int position) {
        return getFooterSize() != 0 && position < getItemCount() && position >= getItemCount() - getFooterSize();
    }

    private int getInsertSize() {
        return mInsertView == null ? 0 : 1;
    }

    /**
     * @param v 增加的view
     */
    public void insertView(int position, View v) {
        if (position < getDataSize()) {
            mInsertView = v;
            mInsertViewPosition = position;
        }
    }

    /**
     * @param position 增加只可以升序增加
     */
    public int getDataPosition(int position) {
        int i = position - getHeaderSize();
        if (i > mInsertViewPosition && mInsertView != null) {
            i--;
        }
        return i;
    }


    private boolean isInsertView(int position) {
        return mInsertView != null && position - getHeaderSize() == mInsertViewPosition;
    }

    @Override
    public int getItemCount() {
        final int other = getFooterSize() + getHeaderSize() + getInsertSize();
        return mData == null ? other : getDataSize() + other;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)) {
            return HEADER - position;
        } else if (isFooter(position)) {
            final int footerPosition = position - (getItemCount() - mFooterViews.size());
            return FOOTER - footerPosition;
        } else if (isInsertView(position)) {
            return INSERT;
        } else {
            return getItemType(position);
        }
    }

    public int getItemType(int position) {
        return DEFAULT;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == INSERT) {
            return new BaseViewHolder(mInsertView);
        } else if (viewType < HEADER + 1 && viewType > INSERT) {
            final int headerPosition = HEADER - viewType;
            return new BaseViewHolder(mHeaderViews.get(headerPosition));
        } else if (viewType < FOOTER + 1 && viewType > HEADER) {
            final int footerPosition = FOOTER - viewType;
            return new BaseViewHolder(mFooterViews.get(footerPosition));
        } else {
            final T viewHolder;
            viewHolder = getViewHolder(parent, viewType);
            if (l != null) {
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int realPosition = viewHolder.getAdapterPosition();
                        final int dataPosition = getDataPosition(realPosition);
                        l.onItemClick(viewHolder.itemView, dataPosition);
                    }
                });
                viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        final int realPosition = viewHolder.getAdapterPosition();
                        final int dataPosition = getDataPosition(realPosition);
                        boolean isclick = l.onItemLongClick(viewHolder.itemView, dataPosition);
                        return isclick;
                    }
                });

            }
            return viewHolder;
        }
    }

    public abstract void onBindHolder(T holder, int i, E e);

    public abstract T getViewHolder(ViewGroup parent, int viewType);


    public ArrayList<E> getData() {
        return mData;
    }

    public int getDataSize() {
        return mData == null ? 0 : mData.size();
    }

    public void setData(ArrayList<E> mData) {
        this.mData = mData;
    }

    public void add(int position, E item) {
        mData.add(position+getHeaderSize(), item);
        notifyItemInserted(position);
    }

    public void addMore(ArrayList<E> data) {
        int startPosition = mData.size()+getHeaderSize();
        mData.addAll(data);
        notifyItemRangeInserted(startPosition, mData.size());
    }

    public void delete(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    public class BaseViewHolder extends ViewHolder {
        public BaseViewHolder(View itemView) {
            super(itemView);
        }
    }
}

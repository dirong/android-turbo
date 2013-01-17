package net.dirong.turbo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dirong
 * Date: 17.01.13
 * Time: 16:21
 */
public abstract class TurboAdapter<T, H extends TurboAdapter.TurboHolder> extends BaseAdapter{

    private List<T> items;
    private final LayoutInflater inflater;
    private final int layout;
    private boolean needNotifyOnChange = false;

    public TurboAdapter(Context context, int layout) {
        this(context, new LinkedList<T>(), layout);
    }

    public TurboAdapter(Context context, List<T> data, int layout) {
        super();
        this.layout = layout;
        inflater = LayoutInflater.from(context);
        setData(data);
    }

    protected List<T> getData() {
        return items;
    }

    public void setNotifyDataOnChange(boolean value) {
        needNotifyOnChange = value;
    }

    public void setData(List<T> data) {
        items = data;
        notifyDataSetChanged();
    }

    public void remove(int position) {
        items.remove(position);
        if (needNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    public void add(T item) {
        items.add(item);
        if (needNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    public void add(T item, int position) {
        items.add(position, item);
        if (needNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View row;
        TurboHolder holder;
        if (view == null) {
            row = inflater.inflate(layout, null);
            holder = createHolder();
            holder.fillHolder(row);
        } else {
            row = view;
            holder = (TurboHolder) row.getTag();
        }
        fillHolder((H) holder, getItem(position), position);
        return row;
    }

    public static abstract class TurboHolder {

        private View view;

        abstract protected void onFillHolder();

        private void fillHolder(View view) {
            this.view = view;
            view.setTag(this);
        }

        protected View findViewById(int id){
            return view.findViewById(id);
        }
    }

    abstract public TurboHolder createHolder();

    abstract public void fillHolder(H holder, T item, int position);
}

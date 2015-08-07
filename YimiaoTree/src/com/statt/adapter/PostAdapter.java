
package com.statt.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.statt.serializable.Post;
import com.statt.util.PostViewHolder;
import com.statt.yimiaotree.R;

public class PostAdapter extends BaseAdapter {

    private static final String TAG = "PostAdapter";
    private static final int TYPE_TOP = 0;
    private static final int TYPE_GENERAL = 1;
    private static final int TYPE_MAX = 2;
    private List<Post> mListPost = null;
    private Context mContext;

    public PostAdapter(Context mContext, List<Post> list) {
        this.mContext = mContext;
        this.mListPost = list;
    }

    public void updateListView(List<Post> list) {
        this.mListPost = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mListPost.size();
    }

    @Override
    public Object getItem(int position) {
        return mListPost.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return mListPost.get(position).isTop() ?
                TYPE_TOP : TYPE_GENERAL;
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_MAX;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        PostViewHolder viewHolder = null;
        int type = getItemViewType(position);
        final Post post = mListPost.get(position);
        if (view == null) {
            viewHolder = new PostViewHolder();
            int id = (type == TYPE_TOP) ? R.layout.item_post_top : R.layout.item_post_general;
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(id, null);
            //view = getItemView(inflater, position);
            viewHolder.initViews(view, post.isTop());
            view.setTag(viewHolder);
        } else {
            viewHolder = (PostViewHolder) view.getTag();
        }
        viewHolder.setDateToItem(post);
        return view;
    }

}

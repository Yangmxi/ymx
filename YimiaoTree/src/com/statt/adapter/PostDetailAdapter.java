
package com.statt.adapter;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.statt.serializable.Parent;
import com.statt.serializable.Post;
import com.statt.serializable.Reply;
import com.statt.util.BitmapTask;
import com.statt.util.PostViewHolder;
import com.statt.yimiaotree.R;

public class PostDetailAdapter extends BaseAdapter {

    private static final int POST_TOP = 0;
    private static final int TYPE_POST = 0;
    private static final int TYPE_REPLY = 1;
    private static final int TYPE_MAX = 2;
    private Context mContext;
    private Post mPost;
    private ArrayList<Reply> mListReply;
    private String mLoginID;

    public PostDetailAdapter(Context context, Post post, ArrayList<Reply> list, String loginID) {
        mContext = context;
        mPost = post;
        mListReply = list;
        mLoginID = loginID;
    }

    private String getLoginID() {
        return this.mLoginID;
    }

    public void updateListView(ArrayList<Reply> list) {
        mListReply = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mListReply.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return (position == POST_TOP) ? mPost : mListReply.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == POST_TOP) ? TYPE_POST : TYPE_REPLY;
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_MAX;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        PostDetailViewHolder viewHolder = null;
        int type = getItemViewType(position);

        if (view == null) {
            viewHolder = new PostDetailViewHolder();
            int id = (type == TYPE_POST) ? R.layout.item_reply_top : R.layout.item_reply_general;
            view = LayoutInflater.from(mContext).inflate(id, null);
            viewHolder.initViews(view, false);
            view.setTag(viewHolder);
        } else {
            viewHolder = (PostDetailViewHolder) view.getTag();
        }
        if (type == TYPE_POST && mPost != null) {
            viewHolder.setDateToItem(mPost, true);
            if (getLoginID().equals(mPost.getParent().getLoginID())) {
                viewHolder.delete.setVisibility(View.VISIBLE);
            }
        } else if (type == TYPE_REPLY) {
            final Reply reply = mListReply.get(position - 1);
            viewHolder.setDateToItem(reply, false);
            if (getLoginID().equals(reply.getParent().getLoginID())) {
                viewHolder.delete.setVisibility(View.VISIBLE);
            }
        }
        return view;
    }

    final static class PostDetailViewHolder extends PostViewHolder {
        TextView content;
        Button btnReply;
        TextView delete;

        @Override
        public void setDateToItem(Post post, boolean isPostDetail) {
            Log.e("ymx","****************in set date to item");
            super.setDateToItem(post, isPostDetail);
            content.setText(post.getContent());
            Log.e("ymx","****************out set date to item");
        }

        public void setDateToItem(Reply reply, boolean isPostDetail) {
            Parent parent = reply.getParent();
            content.setText(reply.getContent());
            name.setText(parent.getName());
            place.setText(parent.getPlace());
            date.setText(reply.getDate());
            Log.e("ymx","****************in set date to item custom");
            //setImageFromURL(avatar, parent.getAvatar());
            Log.e("ymx","****************out set date to item custom");
        }

        @Override
        public void initViews(View view, boolean isTop) {
            super.initViews(view, isTop);
            delete = (TextView) view.findViewById(R.id.post_delete);
            content = (TextView) view.findViewById(R.id.content);
            btnReply = (Button) view.findViewById(R.id.btn_reply);
        }

    }
}

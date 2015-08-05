
package com.statt.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.statt.util.Parent;
import com.statt.util.Post;
import com.statt.widget.RoundImageView;
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
        ViewHolder viewHolder = null;
        int type = getItemViewType(position);
        final Post post = mListPost.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            int id = (type == TYPE_TOP) ? R.layout.item_post_top : R.layout.item_post_general;
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(id, null);
            //view = getItemView(inflater, position);
            initViews(viewHolder, view, position);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        setDateToItem(post, viewHolder, position);
        return view;
    }

    private void setDateToItem(Post post, ViewHolder viewHolder, int position) {
        viewHolder.title.setText(post.getTitle());
        if (!post.isTop()) {
            Parent parent = post.getParent();
            if (parent == null) {
                Log.e(TAG, "parent is null");
            }
            if (viewHolder == null) {
                Log.e(TAG, "viewHolder is null");
            }
            if (viewHolder.name == null) {
                Log.e(TAG, "viewHolder.name is null");
            }
            viewHolder.name.setText(parent.getName());
            viewHolder.place.setText(parent.getPlace());
            viewHolder.date.setText(post.getDate());
            viewHolder.clicks.setText(post.getClicksCount() + "");
            viewHolder.reply.setText(post.getReplyCount() + "");
            viewHolder.avatar.setImageURI(parent.getAvatar());
            ArrayList<Uri> list = post.getImagePath();
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    ImageView image = viewHolder.mListImage.get(i);
                    image.setImageURI(list.get(i));
                    image.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    private void initViews(ViewHolder viewHolder, View view, int position) {
        viewHolder.title = (TextView) view.findViewById(R.id.title);
        if (!mListPost.get(position).isTop()) {
            viewHolder.name = (TextView) view.findViewById(R.id.post_name);
            viewHolder.place = (TextView) view.findViewById(R.id.post_place);
            viewHolder.date = (TextView) view.findViewById(R.id.post_date);
            viewHolder.clicks = (TextView) view.findViewById(R.id.post_click);
            viewHolder.reply = (TextView) view.findViewById(R.id.post_reply);
            viewHolder.avatar = (RoundImageView) view.findViewById(R.id.post_avatar);
            viewHolder.mListImage.add((ImageView) view.findViewById(R.id.post_image_1));
            viewHolder.mListImage.add((ImageView) view.findViewById(R.id.post_image_2));
            viewHolder.mListImage.add((ImageView) view.findViewById(R.id.post_image_3));
        }
    }

    private View getItemView(LayoutInflater inflater, int position) {
        View view;
        if (mListPost.get(position).isTop()) {
            view = inflater.inflate(R.layout.item_post_top, null);
        } else {
            view = inflater.inflate(R.layout.item_post_general, null);
        }
        return view;
    }

    final static class ViewHolder {
        TextView name;
        TextView place;
        TextView date;
        TextView title;
        TextView clicks;
        TextView reply;

        RoundImageView avatar;
        ArrayList<ImageView> mListImage = new ArrayList<ImageView>();
    }
}

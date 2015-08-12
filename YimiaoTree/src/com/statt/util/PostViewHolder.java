
package com.statt.util;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.statt.serializable.Parent;
import com.statt.serializable.Post;
import com.statt.widget.RoundImageView;
import com.statt.yimiaotree.R;

/**
 * This is PostViewHolder for PostAdapter use.
 * also PostDetailViewHolder will extends it in PostDetailAdapter.
 * @author ymx
 *
 */
public class PostViewHolder {
    private static final String TAG = "ViewHolder";
    public TextView name;
    public TextView place;
    public TextView date;
    public TextView title;
    public TextView clicks;
    public TextView reply;

    public RoundImageView avatar;
    public ArrayList<ImageView> mListImage = new ArrayList<ImageView>();

    /**
     * When in BBS activity, it show the info:
     *  Top post only show the title, and general post show all info except content
     * @param post
     * @param position
     */
    public void setDateToItem(Post post) {
        setDateIsTopPost(post, post.isTop());
    }

    /**
     * When show post detail, Top is post and show all information
     * but not show all info in PostAdapter
     * Used by PostDetailAdapter
     * @param post
     * @param position
     * @param isPostDetail
     */
    public void setDateToItem(Post post, boolean isPostDetail) {
        setDateIsTopPost(post, !isPostDetail);
    }

    private void setDateIsTopPost(Post post, boolean isTop) {
        title.setText(post.getTitle());
        if (isTop) {
            return;
        }
        Parent parent = post.getParent();
        if (parent != null) {
            name.setText(parent.getName());
            place.setText(parent.getPlace());
            Log.e(TAG, "Avatar URL = " + Uri.parse(parent.getAvatar()));
            Bitmap bm = null;
            try {
                bm = new BitmapTask().execute(parent.getAvatar()).get();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            avatar.setImageBitmap(bm);
        } else {
            LogUtil.Log(TAG, "parent is null");
        }

        date.setText(post.getDate());
        clicks.setText(post.getClicksCount());
        reply.setText(post.getReplyCount());

        ArrayList<String> list = post.getImagePath();
        if (list != null) {
            for (int i = 0; i < mListImage.size() && i < list.size(); i++) {
                ImageView image = mListImage.get(i);
                Bitmap bm = null;
                try {
                    bm = new BitmapTask().execute(list.get(i)).get();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                image.setImageBitmap(bm);
                image.setVisibility(View.VISIBLE);
            }
        }

    }

    public void initViews(View view, boolean isTop) {
        title = (TextView) view.findViewById(R.id.title);
        if (!isTop) {
            name = (TextView) view.findViewById(R.id.post_name);
            place = (TextView) view.findViewById(R.id.post_place);
            date = (TextView) view.findViewById(R.id.post_date);
            clicks = (TextView) view.findViewById(R.id.post_click);
            reply = (TextView) view.findViewById(R.id.post_reply);
            avatar = (RoundImageView) view.findViewById(R.id.post_avatar);
            mListImage.add((ImageView) view.findViewById(R.id.post_image_1));
            mListImage.add((ImageView) view.findViewById(R.id.post_image_2));
            mListImage.add((ImageView) view.findViewById(R.id.post_image_3));
        }
    }
}

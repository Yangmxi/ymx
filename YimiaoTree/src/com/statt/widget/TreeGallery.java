
package com.statt.widget;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.statt.yimiaotree.R;

public class TreeGallery extends RelativeLayout {

    private int mGalleryTheme;
    private ArrayList<Uri> mListPhotos;
    private String mCreateTime;
    private String mGalleryName;
    private int mCoverPicIndex;

    private ImageView mCoverPic;
    private TextView mName;
    private TextView mTime;

    public TreeGallery(Context context) {
        this(context, null);
    }

    public TreeGallery(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TreeGallery(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflate = LayoutInflater.from(context);
        View treeGallery = inflate.inflate(R.layout.tree_gallery_layout, this);
        mName = (TextView) treeGallery.findViewById(R.id.name);
        mTime = (TextView) treeGallery.findViewById(R.id.create_time);
        mCoverPic = (ImageView) treeGallery.findViewById(R.id.cover_pictures);
    }

    public ImageView getCoverPic() {
        return mCoverPic;
    }

    /**
     * set cover picture from index
     * @param index the mListPhotos index
     */
    public void setCoverPictureFromIndex(int index) {
        if (mListPhotos != null) {
            int size = mListPhotos.size();
            if (index < size) {
                Uri uri = mListPhotos.get(index);
                mCoverPic.setImageURI(uri);
            }
        }
    }

    /**
     * Set the cover picture of tree gallery.
     * @param draw
     */
    public void setCoverPicture(Drawable draw) {
        mCoverPic.setImageDrawable(draw);
    }

    /**
     * Set the cover picture of tree gallery.
     * @param id the resource identifier of the drawable
     */
    public void setCoverPicture(int id) {
        mCoverPic.setImageResource(id);
    }

    /**
     * Set the gallery name
     * @param name the name of gallery
     */
    public void setGalleryName(String name) {
        mName.setText(name);
    }

    /**
     * Set Create time of tree Gallery
     * @param time the time of tree gallery created
     */
    public void setCreateTime(String time) {
        mTime.setText(time);
    }
}


package com.statt.widget;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.statt.activity.MainActivity;
import com.statt.yimiaotree.R;

public class Branch extends FrameLayout {

    public static final int DIRECTION_LEFT = 0;
    public static final int DIRECTION_MID = 1;
    public static final int DIRECTION_RIGHT = 2;
    private RelativeLayout mBranch;
    private TreeGallery mTreeGallery;
    private int mBranchTheme;
    private OnClickListener listener;

    public Branch(Context context) {
        this(context, null);
    }

    public Branch(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Branch(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(final Context context) {
        LayoutInflater inflate = LayoutInflater.from(context);
        mBranch = (RelativeLayout) inflate.inflate(R.layout.branch_layout, null);
        mTreeGallery = (TreeGallery) mBranch.findViewById(R.id.branch_photo_frame);
        if (listener != null) {
            mTreeGallery.setOnClickListener(listener);
        }
        addView(mBranch);
    }

    public TreeGallery getTreeGallery() {
        return mTreeGallery;
    }

    public void setGalleryMargin(int marginLeft, int marginTop) {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mTreeGallery.getLayoutParams();
        lp.leftMargin = marginLeft;
        lp.topMargin = marginTop;
        mTreeGallery.setLayoutParams(lp);
    }

    public void setTreeGalleryOnClickListener(OnClickListener l) {
        listener = l;
    }
}

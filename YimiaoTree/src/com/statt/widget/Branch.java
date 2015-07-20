
package com.statt.widget;

import com.statt.actionbardemo.R;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class Branch extends FrameLayout {

    public static final int DIRECTION_LEFT = 0;
    public static final int DIRECTION_MID = 1;
    public static final int DIRECTION_RIGHT = 2;
    private Point mPosition;
    private RelativeLayout mBranch;
    private TreeGallery mTreeGallery;
    private int mBranchTheme;

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

    private void init(Context context) {
        LayoutInflater inflate = LayoutInflater.from(context);
        mBranch = (RelativeLayout) inflate.inflate(R.layout.branch_layout, null);
        mTreeGallery = (TreeGallery) mBranch.findViewById(R.id.branch_photo_frame);
        addView(mBranch);
    }

    public View getTreeGallery() {
        return mTreeGallery;
    }
}

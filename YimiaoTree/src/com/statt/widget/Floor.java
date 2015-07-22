
package com.statt.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.statt.util.DefineUtil;
import com.statt.yimiaotree.R;

public class Floor extends FrameLayout {

    private static final String TAG = "Floor";

    private RelativeLayout mFloor;
    private Branch mBranchLeft, mBranchRight;
    private ImageView mTrunk;
    private int mTypeBranch = DefineUtil.TYPE_BRANCH_LARGER;

    public Floor(Context context) {
        this(context, null);
    }

    public Floor(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Floor(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflate = LayoutInflater.from(context);
        if (mTypeBranch == DefineUtil.TYPE_BRANCH_SMALL) {
            mFloor = (RelativeLayout) inflate.inflate(R.layout.branch_left_right_layout_small, null);
        } else if (mTypeBranch == DefineUtil.TYPE_BRANCH_LARGER) {
            mFloor = (RelativeLayout) inflate.inflate(R.layout.branch_left_right_layout_larger, null);
        }
        mTrunk = (ImageView) mFloor.findViewById(R.id.trunk);
        mBranchLeft = (Branch) mFloor.findViewById(R.id.branch_left);
        mBranchRight = (Branch) mFloor.findViewById(R.id.branch_right);
        addView(mFloor);
    }

    public void setBranchType(int type) {
        mTypeBranch = type;
        init(getContext());
    }

    public Branch getBranchLeft() {
        return mBranchLeft;
    }

    public Branch getBranchRight() {
        return mBranchRight;
    }

    public ImageView getTrunk() {
        return mTrunk;
    }

    public boolean getBranchRightVisible() {
        if (mBranchRight == null) {
            Log.e(TAG, "Right Branch is null!");
            return false;
        }
        if (View.VISIBLE == mBranchRight.getVisibility()) {
            return true;
        }
        return false;
    }

    public void setBranchRightVisible() {
        if (mBranchRight == null) {
            Log.e(TAG, "Right Branch is null!");
            return;
        }
        mBranchRight.setVisibility(View.VISIBLE);

    }
}


package com.statt.widget;

import android.content.Context;
import android.content.res.Resources;
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
    private int mMarginStartLargerLeftBranch, mMarginTopLargerLeftBranch;
    private int mMarginStartLargerRightBranch, mMarginTopLargerRightBranch;
    private int mMarginStartSmallLeftBranch, mMarginTopSmallLeftBranch;
    private int mMarginStartSmallRightBranch, mMarginTopSmallRightBranch;

    public Floor(Context context) {
        this(context, null);
    }

    public Floor(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Floor(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initMarginDimen();
    }

    /**
     * Must setBranchType after new Floor
     * @param type the Branch Type - small or larger
     */
    public void setBranchType(int type) {
        mTypeBranch = type;
        init(getContext());
    }

    /**
     * init the Floor layout and widget in it
     * @param context
     */
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

        if (mTypeBranch == DefineUtil.TYPE_BRANCH_SMALL) {
            setMarginAndBkg(mBranchLeft, mMarginStartSmallLeftBranch,
                    mMarginTopSmallLeftBranch, R.drawable.branch_left_small);

            setMarginAndBkg(mBranchRight, mMarginStartSmallRightBranch,
                    mMarginTopSmallRightBranch, R.drawable.branch_right_small);

        } else if (mTypeBranch == DefineUtil.TYPE_BRANCH_LARGER) {
            setMarginAndBkg(mBranchLeft, mMarginStartLargerLeftBranch,
                    mMarginTopLargerLeftBranch, R.drawable.branch_left_larger);

            setMarginAndBkg(mBranchRight, mMarginStartLargerRightBranch,
                    mMarginTopLargerRightBranch, R.drawable.branch_right_larger);

        }
        addView(mFloor);
    }

    /**
     * Set the gallery margin let it in right position and set the branch background
     * @param branch the branch with to setup
     * @param left the left margin
     * @param top the right margin
     * @param resId the background resource id
     */
    private void setMarginAndBkg(Branch branch, int left, int top, int resId) {
        branch.setGalleryMargin(left, top);
        branch.setBackgroundResource(resId);
    }

    /**
     * Get the resource
     */
    private void initMarginDimen() {
        Resources res = getResources();
        mMarginStartLargerLeftBranch = res.getDimensionPixelOffset(R.dimen.margin_start_larger_left_branch);
        mMarginStartLargerRightBranch = res.getDimensionPixelOffset(R.dimen.margin_start_larger_right_branch);
        mMarginTopLargerLeftBranch = res.getDimensionPixelOffset(R.dimen.margin_top_larger_left_branch);
        mMarginTopLargerRightBranch = res.getDimensionPixelOffset(R.dimen.margin_top_larger_right_branch);

        mMarginStartSmallLeftBranch = res.getDimensionPixelOffset(R.dimen.margin_start_small_left_branch);
        mMarginStartSmallRightBranch = res.getDimensionPixelOffset(R.dimen.margin_start_small_right_branch);
        mMarginTopSmallLeftBranch = res.getDimensionPixelOffset(R.dimen.margin_top_small_left_branch);
        mMarginTopSmallRightBranch = res.getDimensionPixelOffset(R.dimen.margin_top_small_right_branch);
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

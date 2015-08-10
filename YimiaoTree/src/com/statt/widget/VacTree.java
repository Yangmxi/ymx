
package com.statt.widget;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.statt.util.DefineUtil;

public class VacTree extends RelativeLayout {

    private float mTreeRing;
    private int mState;
    private ArrayList<Floor> mListFloor;
    private OnGrowListener mGrowListener;
    private boolean mIsEven = true;

    /**
     * A callback that notifies clients when the vaccine tree growing or the branch added.
     * You can add animation or move in callback method.
     * @author ymx
     *
     */
    public interface OnGrowListener {
        /**
         * Notification that the vaccine tree has grown.
         * If the Branches were odd number turn into even number
         * the vaccine tree's height not change.
         */
        void onGrowTree();

        /**
         * Notification that the vaccine tree add new branch.
         */
        void onAddBranch(Floor lastFloor);
    }

    public VacTree(Context context) {
        this(context, null);
    }

    public VacTree(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VacTree(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mListFloor = new ArrayList<Floor>();
    }

    private LayoutParams prepareLayoutParams() {

        RelativeLayout.LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        Floor lastFloor = getLastFloor();
        if (lastFloor != null) {
            lp.addRule(RelativeLayout.BELOW, lastFloor.getId());
        }
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        return lp;
    }

    public void setOnGrowListener(OnGrowListener onGrowListener) {
        mGrowListener = onGrowListener;
    }

    public void addBranch() {
        if (mIsEven) {
            addNewFloor();
            mIsEven = false;
        } else {
            setBranchRightVisible();
            mIsEven = true;
        }
        if (mGrowListener != null) {
            mGrowListener.onAddBranch(getLastFloor());
        }
    }

    private void setBranchRightVisible() {
        Floor floor = getLastFloor();
        if (floor != null) {
            floor.setBranchRightVisible();
        }
    }

    private Floor getLastFloor() {
        if (mListFloor == null || mListFloor.size() <= 0) {
            return null;
        }
        return mListFloor.get(mListFloor.size() - 1);
    }

    /**
     * Add a new floor to the vaccine tree.
     * if the number of floor is Even set the branch type is LARGER
     * otherwise, set the branch type is SMALL
     */
    private void addNewFloor() {
        Floor floor = new Floor(getContext());
        floor.setId(DefineUtil.FLOOR_ID_BASE + mListFloor.size());
        RelativeLayout.LayoutParams lp = prepareLayoutParams();
        if (mListFloor.size() % 2 == 0) {
            floor.setBranchType(DefineUtil.TYPE_BRANCH_LARGER);
        } else {
            floor.setBranchType(DefineUtil.TYPE_BRANCH_SMALL);
        }
        mListFloor.add(floor);
        this.addView(floor, lp);
    }

    /**
     * Make the tree add new floor like grow up.
     * Because the relativeLayout not dynamic add new floor ABOVE current floor
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int childTop = 0;
        int childLeft;
        for (int i = childCount - 1; i >= 0; i--) {
            View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                final int childWidth = child.getMeasuredWidth();
                final int childHeight = child.getMeasuredHeight();
                childLeft = (r - l - childWidth) / 2;
                child.layout(childLeft, childTop, childLeft + childWidth, +childTop + childHeight);
                childTop += childHeight;
            }
        }

    }
}

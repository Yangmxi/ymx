
package com.statt.widget;

import java.util.ArrayList;

import com.statt.util.DefineUtil;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

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
    interface OnGrowListener {
        /**
         * Notification that the vaccine tree has grown.
         * If the Branches were odd number turn into even number
         * the vaccine tree's height not change.
         */
        void onGrowTree();

        /**
         * Notification that the vaccine tree add new branch.
         */
        void onAddBranch();
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
        RelativeLayout.LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
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
            mGrowListener.onAddBranch();
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

    private void addNewFloor() {
        Floor floor = new Floor(getContext());
        floor.setId(DefineUtil.FLOOR_ID_BASE + mListFloor.size());
        RelativeLayout.LayoutParams lp = prepareLayoutParams();
        mListFloor.add(floor);
        this.addView(floor, lp);
    }

}

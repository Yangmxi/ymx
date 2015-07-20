
package com.statt.widget;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class VacTree extends RelativeLayout {

    private float mTreeRing;
    private int mState;
    private int mHeight, mWidth;
    private ArrayList<Branch> mListBranch;
    private OnGrowListener mGrowListener;

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
        // TODO Auto-generated method stub

    }

    public void setOnGrowListener(OnGrowListener onGrowListener) {
        mGrowListener = onGrowListener;
    }

    public void addBranch() {
        mGrowListener.onAddBranch();
    }
}

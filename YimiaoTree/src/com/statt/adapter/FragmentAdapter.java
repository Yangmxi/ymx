
package com.statt.adapter;

import java.util.List;

import com.statt.yimiaotree.R;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.util.Log;
import android.widget.RadioGroup;

public class FragmentAdapter implements RadioGroup.OnCheckedChangeListener {

    private static final String TAG = "FragmentAdapter";
    private List<Fragment> mFragments;
    private RadioGroup mRgs;
    private Activity mFragActivity;
    private int mFragContentId;

    private int mCurrentFragIndex;

    private OnMenuChangedListener mOnChangedListener;

    /**
     * Notification that when Menu change to another fragment
     * @author ymx
     *
     */
    public interface OnMenuChangedListener {
        public void OnMenuChanged(RadioGroup radioGroup, int checkedId, int index);
    }

    public FragmentAdapter(Activity fragmentActivity, List<Fragment> fragments,
            int fragmentContentId, RadioGroup rgs) {
        this.mFragments = fragments;
        this.mRgs = rgs;
        this.mFragActivity = fragmentActivity;
        this.mFragContentId = fragmentContentId;

        // Home is the default fragment to show
        FragmentTransaction ft = fragmentActivity.getFragmentManager().beginTransaction();
        ft.add(fragmentContentId, fragments.get(0));
        mCurrentFragIndex = 0;
        ft.commit();

        rgs.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        if (checkedId == R.id.btn_add_branch) {
            // TOTAGen click add branch show a dialog
            Log.e(TAG, "****** this is add branch btn ********");
            return;
        }
        for (int i = 0; i < mRgs.getChildCount(); i++) {
            if (mRgs.getChildAt(i).getId() == checkedId) {
                Fragment fragment = mFragments.get(i);
                FragmentTransaction ft = obtainFragmentTransaction(i);

                // pause the current fragment
                getCurrentFragment().onPause();

                if (fragment.isAdded()) {
                    // if added before onResume()
                    fragment.onResume();
                } else {
                    ft.add(mFragContentId, fragment);
                }
                // show the target fragment
                showFragment(i);
                ft.commit();

                // callback
                if (null != mOnChangedListener) {
                    mOnChangedListener.OnMenuChanged(radioGroup, checkedId, i);
                }

            }
        }

    }

    /**
     * Show the New fragment which user choose
     * @param idx the index of RadioButton in RadioGroup
     *          0 - Home fragment
     *          1 - Appoint fragment
     *          2 - Not a fragment, it is add branch
     *          3 - Discovery fragment
     *          4 - Personal Center fragment
     */
    private void showFragment(int idx) {
        for (int i = 0; i < mFragments.size(); i++) {
            Fragment fragment = mFragments.get(i);
            FragmentTransaction ft = obtainFragmentTransaction(idx);

            if (idx == i) {
                ft.show(fragment);
            } else {
                ft.hide(fragment);
            }
            ft.commit();
        }
        mCurrentFragIndex = idx;
    }

    /**
     * Obtain a FragmentTransaction with animation
     * @param index the index of fragment
     * @return fragmentTranscation animation
     */
    private FragmentTransaction obtainFragmentTransaction(int index) {
        FragmentTransaction ft = mFragActivity.getFragmentManager().beginTransaction();
        if (index > mCurrentFragIndex) {
            ft.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out);
        } else {
            ft.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out);
        }
        return ft;
    }

    public int getCurrentFragmentIndex() {
        return mCurrentFragIndex;
    }

    public Fragment getCurrentFragment() {
        return mFragments.get(mCurrentFragIndex);
    }

    public OnMenuChangedListener getOnMenuChangedListener() {
        return mOnChangedListener;
    }

    public void setOnMenuChangedListener(OnMenuChangedListener onMenuChangedListener) {
        this.mOnChangedListener = onMenuChangedListener;
    }

}


package com.statt.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.statt.activity.personal.BobyManagerActivity;
import com.statt.util.ActionBarUtil;
import com.statt.util.DefineUtil;
import com.statt.yimiaotree.R;

public class FragmentPersonal extends Fragment implements OnClickListener {
    private View mParentInfo;
    private View mBabyManager, mAppoint, mMessage, mAlert;
    private Activity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personal, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        mActivity = activity;
        super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initActionBar();
        initView();
        setOnClick();
    }

    @Override
    public void onResume() {
        super.onResume();
        initActionBar();
    }

    private void setOnClick() {
        mBabyManager.setOnClickListener(this);
        mParentInfo.setOnClickListener(this);
        mAppoint.setOnClickListener(this);
        mMessage.setOnClickListener(this);
        mAlert.setOnClickListener(this);
    }

    private void initActionBar() {
        ActionBarUtil ab = new ActionBarUtil();
        ab.initActionBar(getActivity(), R.string.personal_center, View.GONE);
    }

    private void initView() {
        mParentInfo = getView().findViewById(R.id.parent_info_layout);
        mBabyManager = getView().findViewById(R.id.personal_info_baby);
        mAppoint = getView().findViewById(R.id.personal_info_appointment);
        mMessage = getView().findViewById(R.id.personal_info_message);
        mAlert = getView().findViewById(R.id.personal_info_alert);
    }

    private void prepareIntent(Intent intent, int id, int title) {
        intent.putExtra(DefineUtil.KEY_LAYOUT_ID, id);
        intent.putExtra(DefineUtil.KEY_ACTION_BAR_TITLE, title);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.parent_info_layout:
                prepareIntent(intent, R.drawable.info_parent, R.string.info_parent);
                break;
            case R.id.personal_info_baby:
                prepareIntent(intent, R.drawable.info_baby, R.string.info_baby);
                break;
            case R.id.personal_info_appointment:
                return;
            case R.id.personal_info_message:
                prepareIntent(intent, R.drawable.info_message, R.string.info_message);
                break;
            case R.id.personal_info_alert:
                prepareIntent(intent, R.drawable.info_alert, R.string.info_alert);
                break;
            default:
                break;
        }
        if (mActivity != null) {
            intent.setClass(mActivity, BobyManagerActivity.class);
            mActivity.startActivity(intent);
            //mActivity.overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
        }

    }

}

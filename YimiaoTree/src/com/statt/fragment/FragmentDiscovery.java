
package com.statt.fragment;

import com.statt.activity.discovery.BBSActivity;
import com.statt.activity.discovery.VacKnowledgeActivity;
import com.statt.util.ActionBarUtil;
import com.statt.yimiaotree.R;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentDiscovery extends Fragment implements OnClickListener {

    private Button mBabyBbs, mVacKnowledge;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_discovery, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initActionBar();
        setOnClick();
    }

    @Override
    public void onResume() {
        super.onResume();
        initActionBar();
    }

    private void initActionBar() {
        ActionBarUtil ab = new ActionBarUtil();
        ab.initActionBar(getActivity(), R.string.discovery, View.GONE);
    }

    private void setOnClick() {
        mBabyBbs.setOnClickListener(this);
        mVacKnowledge.setOnClickListener(this);
    }

    private void initView() {
        mBabyBbs = (Button) getView().findViewById(R.id.baby_bbs);
        mVacKnowledge = (Button) getView().findViewById(R.id.vac_knowledge);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.baby_bbs:
                intent.setClass(getActivity(), BBSActivity.class);
                break;
            case R.id.vac_knowledge:
                intent.setClass(getActivity(), VacKnowledgeActivity.class);
                break;
            default:
                break;
        }
        startActivity(intent);

    }
}

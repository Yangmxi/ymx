
package com.statt.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.statt.activity.appoint.AppointInfo;
import com.statt.yimiaotree.R;

public class FragmentAppointment extends Fragment {
    private Button mAppointDetail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_appointment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        mAppointDetail.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), AppointInfo.class);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        mAppointDetail = (Button) getView().findViewById(R.id.appoint_detail);
    }

}

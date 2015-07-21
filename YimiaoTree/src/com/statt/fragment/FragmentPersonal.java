
package com.statt.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.statt.activity.BobyManagerActivity;
import com.statt.yimiaotree.R;

public class FragmentPersonal extends Fragment {
    private Button btn;
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
        btn = (Button) getView().findViewById(R.id.btn_test);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), BobyManagerActivity.class);
                startActivity(intent);
                if (mActivity != null) {
                    //mActivity.overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
                }
            }
        });
        super.onActivityCreated(savedInstanceState);
    }
}

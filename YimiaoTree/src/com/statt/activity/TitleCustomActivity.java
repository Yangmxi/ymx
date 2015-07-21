
package com.statt.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.statt.yimiaotree.R;

public abstract class TitleCustomActivity extends Activity {

    private static final String TAG = "TitleCustomActivtiy";
    ActionBar mActionBar;
    TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
    }

    private void initActionBar() {
        mActionBar = getActionBar();
        if (mActionBar == null) {
            Log.e(TAG, "Can not get ActionBar");
            return;
        }
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        mActionBar.setDisplayShowCustomEnabled(true);
        mActionBar.setCustomView(R.layout.custom_action_bar_title);
        mTitle = (TextView) mActionBar.getCustomView().findViewById(R.id.ab_title);
        mTitle.setText("Setting");
        mActionBar.getCustomView().findViewById(R.id.ab_back)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }

}

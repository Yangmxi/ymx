
package com.statt.activity.personal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.statt.util.ActionBarUtil;
import com.statt.yimiaotree.R;

public class BobyManagerActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baby_manager_layout);
        initActionBar();
    }

    private void initActionBar() {
        ActionBarUtil abUtil = new ActionBarUtil();
        abUtil.initActionBar(this, R.string.info_baby, View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
    }

}

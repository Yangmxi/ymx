
package com.statt.activity;

import com.statt.util.ActionBarUtil;
import com.statt.yimiaotree.R;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class BobyManagerActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boby_manager_layout);
        initActionBar();
    }

    private void initActionBar() {
        ActionBarUtil abUtil = new ActionBarUtil();
        abUtil.initActionBar(this, "Setting", View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }

}

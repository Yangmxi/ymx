
package com.statt.activity.personal;

import com.statt.util.ActionBarUtil;
import com.statt.util.DefineUtil;
import com.statt.yimiaotree.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
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
        Intent intent = getIntent();
        int bkgId = intent.getIntExtra(DefineUtil.KEY_LAYOUT_ID, 0);
        int titleId = intent.getIntExtra(DefineUtil.KEY_ACTION_BAR_TITLE, 0);
        setContentView(R.layout.baby_manager_layout);
        initActionBar(titleId);
        View v = findViewById(R.id.baby_manager_layout);
        v.setBackgroundResource(bkgId);
    }

    private void initActionBar(int titleId) {
        String title = getResources().getString(titleId);
        ActionBarUtil abUtil = new ActionBarUtil();
        abUtil.initActionBar(this, title, View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }

}

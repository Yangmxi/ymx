
package com.statt.activity.discovery;

import com.statt.util.ActionBarUtil;
import com.statt.yimiaotree.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class BbsDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bbs_detail_layout);
        initActionBar();
    }

    private void initActionBar() {
        ActionBarUtil au = new ActionBarUtil();
        au.initActionBar(this, R.string.detail, View.VISIBLE);
    }
}

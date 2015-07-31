
package com.statt.activity.discovery;

import com.statt.util.ActionBarUtil;
import com.statt.util.DefineUtil;
import com.statt.yimiaotree.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class VacDetailActivity extends Activity {

    private String mVacName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getVacNameFromIntent();
        setContentView(R.layout.vac_detail_layout);
        initActionBar();

        // 获取TabHost对象
        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        // 如果没有继承TabActivity时，通过该种方法加载启动tabHost
        tabHost.setup();
        tabHost.addTab(tabHost
                .newTabSpec("tab1")
                .setIndicator("疫苗属性")
                .setContent(R.id.view1));

        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator("漫画知识")
                .setContent(R.id.view2));

        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            View v = tabHost.getTabWidget().getChildAt(i);
            TextView tv = (TextView) v.findViewById(android.R.id.title);
            tv.setTextColor(Color.GRAY);
        }
    }

    private void getVacNameFromIntent() {
        Intent intent = getIntent();
        mVacName = intent.getStringExtra(DefineUtil.KEY_VAC_NAME);
    }

    private void initActionBar() {
        ActionBarUtil au = new ActionBarUtil();
        au.initActionBar(this, R.string.vac_detail, View.VISIBLE);
    }
}

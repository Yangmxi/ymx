
package com.statt.activity.discovery;

import com.statt.util.ActionBarUtil;
import com.statt.yimiaotree.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class VacKnowledgeActivity extends Activity {

    private ImageButton mVacDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vac_knowledge_layout);
        initView();
        initActionBar();
        mVacDetail.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(VacKnowledgeActivity.this,
                        VacDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        mVacDetail = (ImageButton) findViewById(R.id.vac_detail);
    }

    private void initActionBar() {
        ActionBarUtil au = new ActionBarUtil();
        au.initActionBar(this, R.string.vac_knowledge, View.VISIBLE);
    }
}

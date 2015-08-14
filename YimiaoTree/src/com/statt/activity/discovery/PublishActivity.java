
package com.statt.activity.discovery;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.statt.json.RequestTask;
import com.statt.util.ActionBarUtil;
import com.statt.util.Address;
import com.statt.util.LogUtil;
import com.statt.yimiaotree.R;

public class PublishActivity extends Activity {

    private static final String TAG = "PublishActivity";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_LOGIN_ID = "loginId";
    private Button mPublish;
    private EditText mTitle, mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publish_layout);
        initView();
        initActionBar();
        mPublish.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mTitle.getText().toString();
                String content = mContent.getText().toString();
                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(content)) {
                    return;
                }
                JSONObject param = new JSONObject();
                try {
                    param.put(KEY_TITLE, title);
                    param.put(KEY_CONTENT, content);
                    param.put(KEY_LOGIN_ID, "1234");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RequestTask rt = new RequestTask(param, Address.NEW_POST_X_IMAGE);
                Map<String, Object> res = null;
                try {
                    res = rt.execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                if (res == null) {
                    return;
                }

                String code = res.get("code").toString();
                LogUtil.Log(TAG, "Publish Post return Code = " + code);
                if (code.equals("2000")) {
                    finish();
                }
            }
        });
    }

    private void initView() {
        mPublish = (Button) findViewById(R.id.post_publish);
        mTitle = (EditText) findViewById(R.id.post_title);
        mContent = (EditText) findViewById(R.id.post_content);
    }

    private void initActionBar() {
        ActionBarUtil au = new ActionBarUtil();
        au.initActionBar(this, R.string.publish_post, View.VISIBLE);
    }
}


package com.statt.util;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.statt.yimiaotree.R;

public class ActionBarUtil {

    private static final String TAG = "CustomFragment";

    public void initActionBar(final Activity act, String title, int visible) {
        ActionBar actionBar = act.getActionBar();
        if (actionBar == null) {
            Log.e(TAG, "Can not get ActionBar");
            return;
        }
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);

        actionBar.setCustomView(R.layout.custom_action_bar_title);
        View v = actionBar.getCustomView();
        TextView tvTitle = (TextView) v.findViewById(R.id.ab_title);
        ImageView back = (ImageView) v.findViewById(R.id.ab_back);

        back.setVisibility(visible);
        tvTitle.setText(title);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.finish();
            }
        });
    }

    public void initActionBar(final Activity act, int id, int visible) {
        Resources res = act.getResources();
        String title = res.getString(id);
        initActionBar(act, title, visible);
    }
}

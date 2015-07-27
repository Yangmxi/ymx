
package com.statt.util;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.statt.yimiaotree.R;

public class ActionBarUtil {

    private static final String TAG = "CustomFragment";

    /**
     * Change the action bar of fragment and activity.
     * 
     * @param act the activity which use this function
     * @param title the title which in center of action bar
     * @param backVisible if the back button visible
     *          View.VISIBLE, View.INVISIBLE, View.GONE
     */
    public void initActionBar(final Activity act, String title, int backVisible) {
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

        back.setVisibility(backVisible);
        tvTitle.setText(title);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.finish();
            }
        });
    }

    /**
     * Change the action bar of fragment and activity.
     * 
     * @param act the activity which use this function
     * @param id the title resource which in center of action bar
     * @param backVisible if the back button visible
     *          View.VISIBLE, View.INVISIBLE, View.GONE
     */
    public void initActionBar(final Activity act, int id, int backVisible) {
        Resources res = act.getResources();
        String title = res.getString(id);
        initActionBar(act, title, backVisible);
    }

    public ImageButton getCustomButton(Activity act) {
        View v = act.getActionBar().getCustomView();
        return (ImageButton) v.findViewById(R.id.ab_custom_button);
    }
}

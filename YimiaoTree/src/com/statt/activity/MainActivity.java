
package com.statt.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.statt.adapter.FragmentAdapter;
import com.statt.adapter.FragmentAdapter.OnMenuChangedListener;
import com.statt.fragment.FragmentAppointment;
import com.statt.fragment.FragmentDiscovery;
import com.statt.fragment.FragmentHome;
import com.statt.fragment.FragmentPersonal;
import com.statt.yimiaotree.R;

public class MainActivity extends AbstractActivity {

    private static final String TAG = "MainActivity";
    private static final String SHAREDPREFERENCES_NAME = "my_pref";
    private static final String KEY_GUIDE_ACTIVITY = "guide_activity";
    private RadioGroup mMenuGroup;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        initView();
        initFragment();

        FragmentAdapter tabAdapter = new FragmentAdapter(this, fragments, R.id.fragment_content, mMenuGroup);
        tabAdapter.setOnMenuChangedListener(new OnMenuChangedListener() {
            @Override
            public void OnMenuChanged(RadioGroup radioGroup, int checkedId, int index) {
                System.out.println("Extra---- " + index + " checked!!! ");
            }
        });
    }

    private void initView() {
        mMenuGroup = (RadioGroup) findViewById(R.id.menu_radio_group);
    }

    private void initFragment() {
        fragments = new ArrayList<Fragment>();

        fragments.add(new FragmentHome());
        fragments.add(new FragmentAppointment());
        fragments.add(new FragmentHome());
        fragments.add(new FragmentDiscovery());
        fragments.add(new FragmentPersonal());

    }

    private boolean isFirstEnter(Context context, String className) {
        if (context == null || className == null || "".equalsIgnoreCase(className))
            return false;
        String mResultStr = context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE)
                .getString(KEY_GUIDE_ACTIVITY, "");//取得所有类名 如 com.my.MainActivity
        if (mResultStr.equalsIgnoreCase("false"))
            return false;
        else
            return true;
    }

}

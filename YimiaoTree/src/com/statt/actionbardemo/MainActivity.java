
package com.statt.actionbardemo;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.statt.adapter.FragmentAdapter;
import com.statt.adapter.FragmentAdapter.OnMenuChangedListener;
import com.statt.fragment.FragmentAppointment;
import com.statt.fragment.FragmentDiscovery;
import com.statt.fragment.FragmentHome;
import com.statt.fragment.FragmentPersonal;

public class MainActivity extends Activity {

    private RadioGroup mMenuGroup;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        initActionBar();
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

    private void initActionBar() {
        ActionBar ab = getActionBar();
        ab.setCustomView(R.layout.home_action_bar_custom);
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

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

}

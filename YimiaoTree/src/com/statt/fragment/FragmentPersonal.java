
package com.statt.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.statt.actionbardemo.R;

public class FragmentPersonal extends Fragment {
    private TextView tv;
    private Button btn;
    private boolean clicked = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_personal, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        tv = (TextView) getView().findViewById(R.id.tv_test);
        btn = (Button) getView().findViewById(R.id.btn_test);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clicked) {
                    tv.setText("Not Click!");
                    tv.setTextColor(Color.RED);
                    clicked = false;
                } else {
                    tv.setText("Click The Button");
                    tv.setTextColor(Color.BLUE);
                    clicked = true;
                }
            }
        });
        super.onActivityCreated(savedInstanceState);
    }
}

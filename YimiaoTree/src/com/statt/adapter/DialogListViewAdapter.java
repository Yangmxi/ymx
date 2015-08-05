
package com.statt.adapter;

import com.statt.yimiaotree.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * DialogListViewAdapter can adapt listview which item is Text + RadioButton
 * It like the android single choice.
 * @author ymx
 *
 */
public class DialogListViewAdapter extends BaseAdapter {

    private Context mContext;
    private String[] mNameArray;
    private int mSelectIndex;
    private String mCurrentBabyName;

    public DialogListViewAdapter(Context context, String[] array, String currentBaby) {
        mContext = context;
        mNameArray = array;
        mCurrentBabyName = currentBaby;
        initState();
    }

    private void initState() {
        for (int i = 0; i < mNameArray.length; i++) {
            if (mCurrentBabyName.equals(mNameArray)) {
                mSelectIndex = i;
            }
        }
    }

    @Override
    public int getCount() {
        return mNameArray.length;
    }

    @Override
    public Object getItem(int position) {
        return mNameArray[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.item_single_choice, null);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.item_baby_name);
            holder.icon = (RadioButton) convertView.findViewById(R.id.item_radio_btn);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.text.setText(mNameArray[position]);
        if (mSelectIndex == position) {
            holder.icon.setChecked(true);
        } else {
            holder.icon.setChecked(false);
        }
        holder.text.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mSelectIndex = position;
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    static class ViewHolder {
        TextView text;
        RadioButton icon;
    }

}

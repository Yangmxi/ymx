
package com.statt.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.statt.adapter.DialogListViewAdapter;
import com.statt.adapter.FragmentAdapter;
import com.statt.adapter.FragmentAdapter.OnMenuChangedListener;
import com.statt.camera.PhotoCropper;
import com.statt.fragment.FragmentAppointment;
import com.statt.fragment.FragmentDiscovery;
import com.statt.fragment.FragmentHome;
import com.statt.fragment.FragmentPersonal;
import com.statt.widget.RoundImageView;
import com.statt.yimiaotree.R;

public class MainActivity extends Activity implements OnClickListener {

    private static final String TAG = "MainActivity";
    private static final String IMAGE_FILE_LOCATION = "file:///sdcard/temp.jpg";
    private static final int CHOOSE_PIC_AVATAR = 0;
    private static final int TAKE_PIC_AVATAR = 1;
    private RadioGroup mMenuGroup;
    private List<Fragment> fragments;
    private RoundImageView mBabyAvatar;
    private Button mVacBook, mBabyManager;

    private ImageView mCurrentClick;

    //to store the big bitmap
    private Uri imageUri;
    private PhotoCropper mPhotoCropper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        initActionBar();
        initView();
        setOnClick();
        initFragment();

        mPhotoCropper = new PhotoCropper(this);
        imageUri = Uri.parse(IMAGE_FILE_LOCATION);

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
        View customView = getActionBar().getCustomView();
        mMenuGroup = (RadioGroup) findViewById(R.id.menu_radio_group);
        mBabyAvatar = (RoundImageView) customView.findViewById(R.id.baby_avatar);
        mVacBook = (Button) customView.findViewById(R.id.vac_book);
        mBabyManager = (Button) customView.findViewById(R.id.baby_manager);
    }

    private void setOnClick() {
        mBabyAvatar.setOnClickListener(this);
        mVacBook.setOnClickListener(this);
        mBabyManager.setOnClickListener(this);
    }

    private void initFragment() {
        fragments = new ArrayList<Fragment>();

        fragments.add(new FragmentHome());
        fragments.add(new FragmentAppointment());
        fragments.add(new FragmentHome());
        fragments.add(new FragmentDiscovery());
        fragments.add(new FragmentPersonal());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.baby_manager:
                final String[] baby = getResources().getStringArray(R.array.children);
                showBabyManagerDialog(baby, mBabyManager.getText().toString());
                break;
            case R.id.vac_book:
                showVacBookInfo();
                break;
            case R.id.baby_avatar:
                showChoosePhoto(MainActivity.this, mBabyAvatar);
                break;
            default:
                Log.w(TAG, "this is unidentifiable ID!");
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            //result is not correct
            Log.e(TAG, "requestCode = " + requestCode);
            Log.e(TAG, "resultCode = " + resultCode);
            Log.e(TAG, "data = " + data);
            return;
        } else {
            switch (requestCode) {
                case PhotoCropper.TAKE_SMALL_PICTURE:
                    Log.i(TAG, "TAKE_SMALL_PICTURE: data = " + data);
                    mPhotoCropper.cropImageUri(imageUri, 260, 200, PhotoCropper.CROP_SMALL_PICTURE);
                    break;
                case PhotoCropper.CROP_SMALL_PICTURE:
                    if (imageUri != null) {
                        Bitmap bitmap = mPhotoCropper.decodeUriAsBitmap(imageUri);
                        mCurrentClick.setImageBitmap(bitmap);
                    } else {
                        Log.e(TAG, "CROP_SMALL_PICTURE: data = " + data);
                    }
                    break;
                case PhotoCropper.CHOOSE_SMALL_PICTURE:
                    if (data != null) {
                        // Bitmap bitmap = data.getParcelableExtra("data");
                        // this is different method get image
                        Uri imageUri = data.getData();
                        Bitmap bitmap = mPhotoCropper.decodeUriAsBitmap(imageUri);
                        mCurrentClick.setImageBitmap(bitmap);
                    } else {
                        Log.e(TAG, "CHOOSE_SMALL_PICTURE: data = " + data);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void showBabyManagerDialog(String[] array, String currentName) {

        View content = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null);
        ListView list = (ListView) content.findViewById(R.id.dialog_list);
        Button addBaby = (Button) content.findViewById(R.id.dialog_btn);
        addBaby.setVisibility(View.VISIBLE);
        final DialogListViewAdapter adapter = new DialogListViewAdapter(getApplicationContext(), array, currentName);
        list.setAdapter(adapter);

        Dialog alertDialog = new AlertDialog.Builder(this)
                .setView(content)
                .create();
        alertDialog.show();

    }

    private void showVacBookInfo() {
        // TODO intent to another activity

    }

    public void showChoosePhoto(Activity context, ImageView CurrentClick) {
        mCurrentClick = CurrentClick;
        final String[] choose = context.getResources().getStringArray(R.array.choose_photo);

        View content = LayoutInflater.from(context).inflate(R.layout.dialog_layout, null);
        ListView list = (ListView) content.findViewById(R.id.dialog_list);
        list.setAdapter(new ArrayAdapter<String>(context, R.layout.item_single_text, choose));
        final Dialog alertDialog = new AlertDialog.Builder(context)
                .setView(content)
                .create();
        alertDialog.show();
        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alertDialog.dismiss();
                Intent intent;
                switch (position) {
                    case CHOOSE_PIC_AVATAR:
                        mPhotoCropper.takeSmallPhoto();
                        break;
                    case TAKE_PIC_AVATAR:
                        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//action is capture
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        startActivityForResult(intent, PhotoCropper.TAKE_SMALL_PICTURE);
                        break;
                    default:
                        break;
                }
            }
        });
    }

}

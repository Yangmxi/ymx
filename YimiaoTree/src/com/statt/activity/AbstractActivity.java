
package com.statt.activity;

import com.statt.camera.PhotoCropper;
import com.statt.yimiaotree.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class AbstractActivity extends Activity {

    private static final String TAG = "AbstractActivity";
    private static final int CHOOSE_PIC_AVATAR = 0;
    private static final int TAKE_PIC_AVATAR = 1;
    private static final String IMAGE_FILE_LOCATION = "file:///sdcard/temp.jpg";
    protected ImageView mCurrentClick;
    protected PhotoCropper mPhotoCropper;
    //to store the big bitmap
    protected Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        mPhotoCropper = new PhotoCropper(this);
        imageUri = Uri.parse(IMAGE_FILE_LOCATION);
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

    public void showChoosePhoto(Context context, ImageView CurrentClick) {
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

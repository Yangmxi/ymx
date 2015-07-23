
package com.statt.camera;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * PhotoCropper class is use to help take snapshot from system camera
 * and Crop the photo, Constructor need give the activity (ContextThemeWrapper).
 * @author ymx
 *
 */
public class PhotoCropper {

    public static final int TAKE_BIG_PICTURE = 1;
    public static final int TAKE_SMALL_PICTURE = 2;
    public static final int CROP_BIG_PICTURE = 3;
    public static final int CROP_SMALL_PICTURE = 4;
    private Activity mActivity;

    public PhotoCropper(Activity activity) {
        mActivity = activity;
    }

    /**
     * Crop the photo in a new activity after System Camera take photo
     * The progress is follow:
     *      MainActivity -> SystemCamera -> CropPhoto
     * @param uri
     * @param outputX
     * @param outputY
     * @param requestCode
     */
    public void cropImageUri(Uri uri, int outputX, int outputY, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 2);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        mActivity.startActivityForResult(intent, requestCode);
    }

    /**
     * Convert the uri to bitmap
     * @param uri the uri of file
     * @return bitmap
     */
    public Bitmap decodeUriAsBitmap(Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(mActivity.getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

}

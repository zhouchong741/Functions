package com.jiae.herbs.baselib.utils;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.jiae.herbs.baselib.utils.cache.FileUtil;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 标题：
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/6/21 18:17.
 */
public class PickPhoto {

    private static final String TAG = LogUtil.getName(PickPhoto.class);

    /**
     * 默认图片压缩大小
     */
    public static final int DEFAULT_SCALE_WIDTH = 1080;
    public static final int DEFAULT_SCALE_HEIGHT = 1920;

    //选择图片
    public static final int PICK_PHOTO = 3021;

    //拍照
    public static final int TAKE_PHOTO = 3022;

    //裁剪图片
    public static final int CROP_PHOTO = 3023;

    //拍照后裁剪
    public static final int TAKE_CROP_PHOTO = 3024;

    //输出宽度
    public static final int OUTPUT_WIDTH = 200;

    //输出高度
    public static final int OUTPUT_HEIGHT = 200;

    /**
     * 调用拍照
     */
    public static void takePhoto(Context activity, Uri target) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, target);
        ((FragmentActivity) activity).startActivityForResult(intent, TAKE_PHOTO);
    }

    /**
     * 调用相册选择图片并裁剪
     * 在魅族手机上有问题
     */
    public static void pickPhotoAndCrop(Context activity, Uri source) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, source);
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", OUTPUT_WIDTH);
        intent.putExtra("outputY", OUTPUT_HEIGHT);
        ((FragmentActivity) activity).startActivityForResult(intent, CROP_PHOTO);
    }

    /**
     * 调用相册选择图片并裁剪
     * 在魅族手机上有问题
     */
    public static void pickPhotoAndCrop(Context activity, String output, int width, int height) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        intent.putExtra("output", Uri.fromFile(new File(output)));
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", width);
        intent.putExtra("outputY", height);
        ((FragmentActivity) activity).startActivityForResult(intent, CROP_PHOTO);
    }

    /**
     * 得到图片资源后剪切
     * 在onActivityResult中获取
     *
     * @param activity 上下文
     * @param source   源文件，pickPhoto在Intent.getData();tackPhoto:takePhoto()的targetUri
     * @param target   目标文件，资源输出文件
     */
    public static void getCropIntent(Context activity, Uri source, Uri target) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(source, "image/*");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, target);
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", OUTPUT_WIDTH);
        intent.putExtra("outputY", OUTPUT_HEIGHT);
        ((FragmentActivity) activity).startActivityForResult(intent, TAKE_CROP_PHOTO);
    }

    /**
     * 调用相册选择图片并返回
     */
    public static void pickPhoto(Context activity) {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        ((FragmentActivity) activity).startActivityForResult(intent, PICK_PHOTO);
    }

    /**
     * 返回图片本地地址
     */
    public static String queryPhotoPath(Context activity, Uri source) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = activity.getContentResolver().query(source, proj, null, null, null);
        if (cursor != null) {
            int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String localUrl = cursor.getString(index);
            cursor.close();
            return localUrl;
        }
        return null;
    }

    /**
     * 解决小米手机上获取图片路径为null的情况 queryPhotoPath();
     */
    public static Uri getMIUri(Context activity, Intent source) {
        Uri uri = source.getData();
        String type = source.getType();
        if (uri.getScheme().equals("file") && (type.contains("image/"))) {
            String path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                String buff = "(" +
                        MediaStore.Images.ImageColumns.DATA +
                        "=" +
                        "'" +
                        path +
                        "'" +
                        ")";
                Cursor cur = activity.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Images.ImageColumns._ID},
                        buff, null, null);
                int index = 0;
                if (cur != null) {
                    for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                        index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                        // set _id value
                        index = cur.getInt(index);
                    }
                    if (index != 0) {
                        Uri uri_temp = Uri
                                .parse("content://media/external/images/media/"
                                        + index);
                        if (uri_temp != null) {
                            uri = uri_temp;
                        }
                    }
                    cur.close();
                }
            }
        }
        return uri;
    }

    /**
     * 读取图片的旋转角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        if (!TextUtils.isEmpty(path)) {
            try {
                ExifInterface exif = new ExifInterface(path);
                int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        degree = 90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        degree = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        degree = 270;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return degree;
    }

    public static Bitmap rotateBitmap(int angle, Bitmap bitmap) {
        try {
            Matrix matrix = new Matrix();
            matrix.postRotate(angle);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            System.gc();
        }
        return bitmap;
    }

    public static String scaleImage(String fileName, int quality) {
        return scaleImage(fileName, DEFAULT_SCALE_WIDTH, DEFAULT_SCALE_HEIGHT, quality);
    }

    /**
     * 图片压缩
     */
    public static String scaleImage(String fileName, int maxWidth, int maxHeight, int quality) {
        if (TextUtils.isEmpty(fileName))
            return null;
        Bitmap bitmap = null;
        try {
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(fileName, opts);
            int srcWidth = opts.outWidth;
            int srcHeight = opts.outHeight;
            if (srcWidth < maxWidth && srcHeight < maxHeight)
                return fileName;
            int desWidth = 0;
            int desHeight = 0;
            double ratio = 0.0;
            if (srcWidth > srcHeight) {
                ratio = srcWidth / maxWidth;
                desWidth = maxWidth;
                desHeight = (int) (srcHeight / ratio);
            } else {
                ratio = srcHeight / maxHeight;
                desHeight = maxHeight;
                desWidth = (int) (srcWidth / ratio);
            }
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            newOpts.inSampleSize = (int) (ratio) + 1;
            newOpts.inJustDecodeBounds = false;
            newOpts.outWidth = desWidth;
            newOpts.outHeight = desHeight;
            bitmap = retryDecodeBitmap(fileName, newOpts, 2);
            //读取旋转角度
            int angle = readPictureDegree(fileName);
            if (angle != 0) {
                bitmap = rotateBitmap(angle, bitmap);
            }
            File tempFile = FileUtil.getInstance().newTempImageFile();
            FileOutputStream out = new FileOutputStream(tempFile);
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, quality, out)) {
                out.flush();
                out.close();
                long size = tempFile.length();
                LogUtil.d(TAG, "图片压缩后大小：" + FileUtil.formatFileSize(size));
                return tempFile.getAbsolutePath();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (OutOfMemoryError e) {
            System.gc();
            return null;
        } finally {
            if (bitmap != null && !bitmap.isRecycled())
                bitmap.recycle();
        }
        return null;
    }

    public static Bitmap retryDecodeBitmap(String fileName, BitmapFactory.Options options, int retryCount) {
        try {
            return BitmapFactory.decodeFile(fileName, options);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            System.gc();
            if (retryCount > 0)
                return retryDecodeBitmap(fileName, options, retryCount - 1);
            return null;
        }
    }
}

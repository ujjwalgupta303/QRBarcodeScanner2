package com.example.lenovo.qrbarcodescanner;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;


public class AppsActivity extends ListActivity {

    private PackageManager packageManager = null;
    private List<ApplicationInfo> applist = null;
    private AppAdapter listadapter = null;

    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps);

        packageManager = getPackageManager();

        new LoadApplications().execute();
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        ApplicationInfo app = applist.get(position);

        try {
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                int width, height;
                String info=app.packageName;
                String text = "https://play.google.com/store/apps/details?id="+info;
                Log.d("ADebugTag", "Value: " + text+info);
                    BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, width = 500, height = 500);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            ImageView imgGnr = findViewById(R.id.imageView10);
            imgGnr.setImageBitmap(bitmap);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(AppsActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(AppsActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public Uri getImageUri(Context context, Bitmap bitmap) {
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new ByteArrayOutputStream());
        return Uri.parse(MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Title", null));
    }

    private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list) {

        ArrayList<ApplicationInfo> appList = new ArrayList<ApplicationInfo>();

        for (ApplicationInfo info : list) {
            try {
                if (packageManager.getLaunchIntentForPackage(info.packageName) != null) {
                    appList.add(info);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return appList;
    }
    private class LoadApplications extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progress = null;

        @Override
        protected Void doInBackground(Void... params) {

            applist = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));

            listadapter = new AppAdapter(AppsActivity.this, R.layout.list_item, applist);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            setListAdapter(listadapter);
            progress.dismiss();
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(AppsActivity.this, null, "Loading apps info...");
            super.onPreExecute();
        }
    }
}


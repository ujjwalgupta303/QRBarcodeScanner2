package com.example.lenovo.qrbarcodescanner;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class GenerateActivity extends AppCompatActivity{
    private Button btnGenerate, btnShare;
    private EditText etGenerate;
    private TextView textViewGnr;
    private ImageView imgGnr;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);

        btnShare = (Button) findViewById(R.id.buttonShare);
        btnGenerate = (Button) findViewById(R.id.btngnr);
        etGenerate = (EditText) findViewById(R.id.etGnr);
        textViewGnr = (TextView) findViewById(R.id.tvHead);
        imgGnr = (ImageView) findViewById(R.id.ivGnr);


        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = etGenerate.getText().toString();
                if (text != null) {
                    try {
                        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                        int width, height;
                        BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, width = 500, height = 500);
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                        imgGnr.setImageBitmap(bitmap);

                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Bitmap bitmap=((BitmapDrawable)imgGnr.getDrawable()).getBitmap();

       /* btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri imagePath= FileProvider.getUriForFile(GenerateActivity.this,"com.example.lenovo.qrbarcodescanner",new File(String.valueOf(imgGnr)));
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                sharingIntent.setType("image/JPEG");
                sharingIntent.putExtra(Intent.EXTRA_STREAM, imagePath);
                startActivity(Intent.createChooser(sharingIntent, "Share Image Using"));
            }

        });
*/

       btnShare.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               shareImage();
           }
       });


    }

    private void shareImage() {
        try{
            String s=textViewGnr.getText().toString();
            File file=new File(getExternalCacheDir(),"sample.jpeg");
            FileOutputStream fOut=new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true,false);
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_TEXT,s);
            intent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(file));
            intent.setType("image/jpeg");
            startActivity(Intent.createChooser(intent,"Share via"));
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }
}

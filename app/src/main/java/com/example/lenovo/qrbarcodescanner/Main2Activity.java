package com.example.lenovo.qrbarcodescanner;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class Main2Activity extends AppCompatActivity {

    private TextView textView1,textView2,textView3;
    private ImageView imageView1,imageView2,imageView3;
    LinearLayout l1,l2,l3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        l1=findViewById(R.id.ll1);
        l2=findViewById(R.id.ll2);
        l3=findViewById(R.id.ll3);
        imageView1=findViewById(R.id.imageView2);
        imageView2=findViewById(R.id.imageView3);
        imageView3=findViewById(R.id.imageView4);
        textView1=findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);
        textView3=findViewById(R.id.textView3);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this, GenerateActivity.class));
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this,ContactActivity.class));
            }
        });

        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this,AppsActivity.class));
            }
        });


    }


}

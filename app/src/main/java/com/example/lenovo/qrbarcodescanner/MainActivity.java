package com.example.lenovo.qrbarcodescanner;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    private ImageView scanner,generate;
    private TextView textViewScanner,textViewGenerator,textViewBarcode;
    private ImageView imgQR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanner= findViewById(R.id.imgScanner);
        generate=findViewById(R.id.generate);
        textViewBarcode=(TextView)findViewById(R.id.tvQrCode);
        textViewScanner=(TextView)findViewById(R.id.tvScanner);
        textViewGenerator=(TextView)findViewById(R.id.tvGenerator);
        imgQR=(ImageView)findViewById(R.id.imgQr);

        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator=new IntentIntegrator(MainActivity.this);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                intentIntegrator.setCameraId(0);
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.setPrompt("Scanning");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setBarcodeImageEnabled(true);
                intentIntegrator.initiateScan();
            }
        });

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        final IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null && result.getContents()!=null)
        {
          new AlertDialog.Builder(MainActivity.this)
                  .setTitle("Scan Result")
                  .setMessage(result.getContents())
                  .setPositiveButton("Copy", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                          ClipboardManager manager=(ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                          ClipData data= ClipData.newPlainText("result",result.getContents());
                          manager.setPrimaryClip(data);
                      }
                  })
                  /*.setPositiveButton("Visit", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                  Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(result)));
                  startActivity(intent);
              }
          })*/
                  .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                  dialog.dismiss();
              }
          }).create().show();


        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

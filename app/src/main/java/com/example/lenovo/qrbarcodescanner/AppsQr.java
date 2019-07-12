package com.example.lenovo.qrbarcodescanner;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

class AppsQr extends AppsActivity {
    ImageView imageView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_apps);

        imageView=findViewById(R.id.imageView5);

        listView=findViewById(R.id.list);
        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                    int width, height;
                    BitMatrix bitMatrix = (BitMatrix) multiFormatWriter.encode(String.valueOf(app), BarcodeFormat.QR_CODE, width = 500, height = 500);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            }
        }); */
    }


}

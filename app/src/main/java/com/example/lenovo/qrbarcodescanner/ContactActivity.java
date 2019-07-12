package com.example.lenovo.qrbarcodescanner;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ContactActivity extends AppCompatActivity {

    ListView listView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        listView = findViewById(R.id.listViewContacts);
        button = findViewById(R.id.button2);
    }


    public void get(View v) {
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        startManagingCursor(cursor);

        String[] from = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone._ID};
        int to[] = {android.R.id.text1, android.R.id.text2};

        final SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, from, to);
        listView.setAdapter(simpleCursorAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {



                if (position == 0) {
                    Toast.makeText(getApplicationContext(), "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    /*public static int getTotalHeightofListView(ListView listView) {

        ListAdapter mAdapter = listView.getAdapter();

        int totalHeight = 0;

        for (int i = 0; i < mAdapter.getCount(); i++) {
            View mView = mAdapter.getView(i, null, listView);

            mView.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),

                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            totalHeight += mView.getMeasuredHeight();
            Log.w("HEIGHT" + i, String.valueOf(totalHeight));

        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (mAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
        return totalHeight;

    } */

}

package com.cs442.sbasappa_a3;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sahana on 10/19/15.
 */
public class ChangeItemActivity extends Activity{

    private TextView textV1;
    private Button btn4;
    private ContentResolver CR2;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changeitem);

        Intent intent = getIntent();
        id = intent.getStringExtra(MainActivity.FIRST_ITEM_ID);
        Log.w("aaa", "id is " + id);

        textV1 = (TextView) findViewById(R.id.change_item_price);
        btn4 = (Button) findViewById(R.id.button456);
        CR2 = getContentResolver();


        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("aaa", "in the btn4 setOnClickListener before changeprice");
                if (textV1.getText().toString() != null && textV1.getText().toString().isEmpty()) {
                    Toast.makeText(ChangeItemActivity.this, "Please enter the Correct Values", Toast.LENGTH_SHORT).show();
                } else {
                    String price = textV1.getText().toString();
                    Log.w("aaa", "The new price is " + price);
                    if (price == null) {
                    Toast.makeText(ChangeItemActivity.this, "Please enter the Correct Values", Toast.LENGTH_SHORT).show();
                    } else {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(Mycontentprovider.KEY_PRICE, price);
                        CR2.update(Mycontentprovider.CONTENT_URI, contentValues, Mycontentprovider.KEY_ID + "=?", new String[]{id});
        }

                }
                showMenu(v);
            }
        });
    }

//    public void changeprice() {
//        String price = textV1.getText().toString();
//        Log.w("aaa", "The new price is " + price);
//        if (price == null) {
//            Toast.makeText(this, "Please enter the Correct Values", Toast.LENGTH_SHORT).show();
//        } else {
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(Mycontentprovider.KEY_PRICE, price);
//            CR2.update(Mycontentprovider.CONTENT_URI, contentValues, Mycontentprovider.KEY_ID + "=?", new String[]{id});
//        }
//    }

    public void showMenu(View view){
        Intent intent = new Intent(this,ItemListActivity.class);
        startActivity(intent);
    }

}



package com.cs442.sbasappa_a3;

import android.app.Activity;
import android.content.ContentResolver;
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
public class DeleteItemActivity extends Activity{

    private TextView textV;
    private Button btn;
    private String id;
    private Button btn1;
    private ContentResolver CR1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete);

        Intent intent = getIntent();
        id = intent.getStringExtra(MainActivity.FIRST_ITEM_ID);
        Log.w("aaa","id is " + id);

        textV = (TextView) findViewById(R.id.textv);
        btn = (Button) findViewById(R.id.button123);
        btn1 = (Button) findViewById(R.id.button143);
        CR1 = getContentResolver();
        //Cursor cursor = CR1.query(Mycontentprovider.CONTENT_URI, null, Mycontentprovider.KEY_ID + "=?", new String[]{id}, null);
       // Log.w("aaa","foodName is " + cursor.getString(Mycontentprovider.KEY_NAME_COLUMN));
        //String foodName = cursor.getString(Mycontentprovider.KEY_NAME_COLUMN);
        Log.w("aaa","All set");

        textV.setText("Do you wanna delete the food items ?");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),id+"",Toast.LENGTH_LONG).show();
                CR1.delete(Mycontentprovider.CONTENT_URI, Mycontentprovider.KEY_ID + "=?", new String[]{id});
            }

        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToItemListActivity();
            }

        });
    }

    public void backToItemListActivity(){
        Intent intent = new Intent(this,ItemListActivity.class);
        startActivity(intent);
    }

    public void showMenu(View view){
        Intent intent = new Intent(this,ItemListActivity.class);
        startActivity(intent);
    }

}
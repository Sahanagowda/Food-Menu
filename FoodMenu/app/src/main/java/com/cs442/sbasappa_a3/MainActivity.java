package com.cs442.sbasappa_a3;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private ContentResolver CR;
    public static final String FIRST_ITEM_ID = "com.cs442.sbasappa_a3.FIRST_ITEM_ID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CR = getContentResolver();
        Cursor cursor = CR.query(Mycontentprovider.CONTENT_URI, null, null, null, null);
        if(cursor.getCount()==0){
        String[] names = getResources().getStringArray(R.array.item_names);
        int [] ids = getResources().getIntArray(R.array.item_ids);
        String[] prices = getResources().getStringArray(R.array.item_prices);
        String[] ind = getResources().getStringArray(R.array.item_ingredients);
        String[] res = getResources().getStringArray(R.array.item_cookingStyle);

        int i=0;
        for (String name : names){
            Item item = new Item(ids[i],names[i],prices[i]);
            ContentValues CV = new ContentValues();
            CV.put(Mycontentprovider.KEY_NAME,names[i]);
            CV.put(Mycontentprovider.KEY_PRICE,prices[i]);
            CV.put(Mycontentprovider.KEY_Ingredient,ind[i]);
            CV.put(Mycontentprovider.KEY_Receipe,res[i]);
            CR.insert(Mycontentprovider.CONTENT_URI,CV);
            i++;
        }}
    }

    public void showMenu(View view){
        Intent intent = new Intent(this,ItemListActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        Log.w("aaa","before switch");
        switch(item.getItemId())
        {
            case R.id.add_item:
                Log.w("aaa","in case add_item");
                Intent intent = new Intent(getApplicationContext(),AddItemActivity.class);
                Log.w("aaa", "before startActivity");
                startActivity(intent);
                Log.w("aaa","after startActivity");
                break;
            case R.id.delete_item:
                Log.w("aaa","Delete item");
                Cursor cur = CR.query(Mycontentprovider.CONTENT_URI,null,null,null,null);
                cur.moveToFirst();
                String id = cur.getString(Mycontentprovider.KEY_ID_COLUMN);
               Intent intent1 = new Intent(getApplicationContext(),DeleteItemActivity.class);
                Log.w("aaa", "Start Intent");
                intent1.putExtra(FIRST_ITEM_ID, id);
                startActivity(intent1);
                Log.w("aaa","Started Actvity");
                break;
            case R.id.change_item:
                Cursor curs = CR.query(Mycontentprovider.CONTENT_URI,null,null,null,null);
                curs.moveToFirst();
                String id1 = curs.getString(Mycontentprovider.KEY_ID_COLUMN);
                Intent intent2 = new Intent(getApplicationContext(),ChangeItemActivity.class);
                Log.w("aaa", "Start Intent");
                intent2.putExtra(FIRST_ITEM_ID, id1);
                startActivity(intent2);

                Log.w("aaa","Started Actvity");
                break;
    }
        return true;
    }


}


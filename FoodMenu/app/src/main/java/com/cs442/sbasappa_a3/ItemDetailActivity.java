package com.cs442.sbasappa_a3;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ItemListActivity}.
 * <p/>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link ItemDetailFragment}.
 */
public class ItemDetailActivity extends AppCompatActivity {
    String CurrentID;
    public static final String FIRST_ITEM_ID = "com.cs442.sbasappa_a3.FIRST_ITEM_ID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fooditem_detail);

        // Show the Up button in the action bar.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        CurrentID = getIntent().getStringExtra(ItemDetailFragment.ARG_ITEM_ID);
       // Toast.makeText(getApplicationContext(),getIntent().getStringExtra(ItemDetailFragment.ARG_ITEM_ID),Toast.LENGTH_LONG).show();
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(ItemDetailFragment.ARG_ITEM_ID));
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fooditem_detail_container, fragment)
                    .commit();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        ContentResolver CR = getContentResolver();
        switch(item.getItemId())
        {
            case R.id.delete_item:
                Log.w("aaa", "Delete item");
                Cursor cur = CR.query(Mycontentprovider.CONTENT_URI,null,null,null,null);
                cur.moveToFirst();
                //String id = cur.getString(Mycontentprovider.KEY_ID_COLUMN);
                Intent intent1 = new Intent(getApplicationContext(),DeleteItemActivity.class);
                Log.w("aaa", "Start Intent");
                intent1.putExtra(FIRST_ITEM_ID, CurrentID);
                startActivity(intent1);
                Log.w("aaa","Started Actvity");
                break;
            case R.id.change_item:
                Cursor curs = CR.query(Mycontentprovider.CONTENT_URI,null,null,null,null);
                curs.moveToFirst();
                //String id1 = curs.getString(Mycontentprovider.KEY_ID_COLUMN);
                Intent intent2 = new Intent(getApplicationContext(),ChangeItemActivity.class);
                Log.w("aaa", "Start Intent");
                intent2.putExtra(FIRST_ITEM_ID, CurrentID);
                startActivity(intent2);
                Log.w("aaa","Started Actvity");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

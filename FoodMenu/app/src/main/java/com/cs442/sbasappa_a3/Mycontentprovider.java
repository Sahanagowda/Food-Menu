package com.cs442.sbasappa_a3;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by sahana on 10/19/15.
 */
public class Mycontentprovider extends ContentProvider
{
public static final Uri CONTENT_URI = Uri.parse("content://com.cs442.sbasappa_a3.android.provider.FoodMenu1/items");

private SQLiteDatabase itemsDB;

private static final String TAG = "MycontentProvider";
private static final String DB_NAME = "Item.db";
private static final int DB_VERSION = 1;
private static final String ITEMS_TABLE = "ITEMS";

public static final String KEY_ID = "_id";
public static final String KEY_NAME = "name";
public static final String KEY_PRICE = "price";
public static final String KEY_Ingredient = "Ingredient";
public static final String KEY_Receipe = "Receipe";

public static final int KEY_ID_COLUMN = 0;
public static final int KEY_NAME_COLUMN = 1;
public static final int KEY_PRICE_COLUMN = 2;
public static final int KEY_Ingredient_COLUMN = 3;
public static final int KEY_Receipe_COLUMN = 4;

private static final int ALL_ITEMS = 1;
private static final int ONE_ITEM = 2;

private static final UriMatcher uriMatcher;

static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.cs442.sbasappa_a3.android.provider.FoodMenu1","items", ALL_ITEMS);
        uriMatcher.addURI("com.cs442.sbasappa_a3.android.provider.FoodMenu1","items/#",ONE_ITEM);

        }

public String getType(Uri uri){
        switch (uriMatcher.match(uri)) {
        case ONE_ITEM :
        return "vnd.android.cursor.item/foods";

        case ALL_ITEMS :
        return "vnd.android.cursor.dir/foods";

default: throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        }

@Override
public Cursor query(Uri uri, String[] projection, String selection,
        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        String groupBy = null;
        String having = null;
        switch(uriMatcher.match(uri)){
        case ONE_ITEM:
        String rowID = uri.getPathSegments().get(1);
        queryBuilder.appendWhere(KEY_ID + "=" + rowID);

default: break;
        }
        queryBuilder.setTables(ITEMS_TABLE);


        Cursor cursor = queryBuilder.query(itemsDB,projection,selection,selectionArgs,groupBy,having,sortOrder);
        return cursor;
        }

@Override
public int delete(Uri uri, String selection, String[] selectionArgs){
        switch (uriMatcher.match(uri)){
        case ONE_ITEM:
        String rowId = uri.getPathSegments().get(1);
        selection = KEY_ID + "=" + rowId
        + (!TextUtils.isEmpty(selection) ?
        " AND (" + selection + ')' : "");

default:break;
        }

        if(selection == null){
        selection = "1";
        }

        int deleteCount = itemsDB.delete(ITEMS_TABLE, selection, selectionArgs);

        getContext().getContentResolver().notifyChange(uri,null);
        return deleteCount;

        }

@Override
public Uri insert(Uri uri, ContentValues values) {
        String nullColumnHack = null;
        long rowId = itemsDB.insert(ITEMS_TABLE, nullColumnHack, values);

        if(rowId > -1){
        Uri insertedUri = ContentUris.withAppendedId(CONTENT_URI, rowId);
        getContext().getContentResolver().notifyChange(insertedUri,null);
        return insertedUri;
        }
        else return null;
        }

@Override
public int update(Uri uri, ContentValues values,String selection, String[] selectionArgs){
        switch (uriMatcher.match(uri)){
        case ONE_ITEM:
        String rowId = uri.getPathSegments().get(1);
        selection = KEY_ID + "=" + rowId
        + (!TextUtils.isEmpty(selection) ?
        " AND (" + selection + ')' : "");

default:break;
        }
        int updateCount = itemsDB.update(ITEMS_TABLE,values,selection,selectionArgs);

        getContext().getContentResolver().notifyChange(uri,null);
        return updateCount;
        }

private static class ItemsHelper extends SQLiteOpenHelper {

    private static final String DB_CREATE_TABLE =
            "create table " + ITEMS_TABLE + " ("
                    + KEY_ID + " integer primary key autoincrement, "
                    + KEY_NAME + " STRING, "
                    + KEY_PRICE + " STRING, "
                    + KEY_Ingredient + " TEXT, "
                    + KEY_Receipe + " TEXT);";


    public ItemsHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.w("arrayAdapter", "Upgrading from version " + oldVersion + " to " + newVersion + ", which whill destroy all old data");
        db.execSQL("DROP TABLE IF IT EXISTS " + DB_CREATE_TABLE);
        //getContext().getResources().getStringArray(R.array.item_ids);
        onCreate(db);
    }
}

    @Override
    public boolean onCreate(){
        ItemsHelper itemsHelper = new ItemsHelper(getContext(),ITEMS_TABLE, null, DB_VERSION);
        itemsDB = itemsHelper.getWritableDatabase();

        return (itemsDB == null)?false:true;
    }

}


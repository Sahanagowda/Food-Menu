package com.cs442.sbasappa_a3;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A list fragment representing a list of FoodItems. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link ItemDetailFragment}.
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class ItemListFragment extends ListFragment {
    private ContentResolver CR;

    ArrayList<Item> arrayOfItems = new ArrayList<Item>();

    private  View currentSelectedView;
    /**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";
    //private static View internalView;
    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private Callbacks mCallbacks = sDummyCallbacks;

    /**
     * The current activated item position. Only used on tablets.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         */
        public void onItemSelected(String id);
    }

    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(String id) {
        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ItemAdapter foodAdapter = new ItemAdapter(getActivity(), arrayOfItems);

        CR = getActivity().getContentResolver();
        Cursor cursor = CR.query(Mycontentprovider.CONTENT_URI, null, null, null, null);
        while(cursor.moveToNext()){
            String id = cursor.getString(Mycontentprovider.KEY_ID_COLUMN);
            String name = cursor.getString(Mycontentprovider.KEY_NAME_COLUMN);
            String price = cursor.getString(Mycontentprovider.KEY_PRICE_COLUMN);
            String ingredients = cursor.getString(Mycontentprovider.KEY_Ingredient_COLUMN);
            String receipe = cursor.getString(Mycontentprovider.KEY_Receipe_COLUMN);
            Item item = new Item(Integer.parseInt(id),name,price,ingredients,receipe);
            foodAdapter.add(item);
        }

//        String[] names = getResources().getStringArray(R.array.item_names);
//        int [] ids = getResources().getIntArray(R.array.item_ids);
//        String[] prices = getResources().getStringArray(R.array.item_prices);
//
//        int i=0;
//        for (String name : names){
//            Item item = new Item(ids[i],names[i],prices[i]);
//            foodAdapter.add(item);
//            i++;
//        }
        setListAdapter(foodAdapter);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        changeBackgroundColorSelection(view);
        Log.d("Food menu", "onListItemClick method is called");
        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        mCallbacks.onItemSelected(Integer.toString(arrayOfItems.get(position).getId()));
    }

    private void changeBackgroundColorSelection(View selectedView){
        if (currentSelectedView != null && currentSelectedView != selectedView) {
            currentSelectedView.setBackground(getResources().getDrawable(R.drawable.hifi));
        }

        currentSelectedView = selectedView;
        currentSelectedView.setBackgroundResource(R.color.select_color);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }
        mActivatedPosition = position;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AdapterView.OnItemLongClickListener listener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long id) {
                Log.d("Food menu","onItemLongClick method is called");
                if (getListView().getCheckedItemCount() > 0) {
                    return false;
                }
                mCallbacks.onItemSelected(Integer.toString(arrayOfItems.get(position).getId()));
                getListView().setSelected(true);
                changeBackgroundColorSelection(arg1);
                return true;
            }
        };
        getListView().setOnItemLongClickListener(listener);
    }
}

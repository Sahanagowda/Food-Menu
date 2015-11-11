package com.cs442.sbasappa_a3;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;



/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Item item;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID) || getArguments().getString(ARG_ITEM_ID)!=null) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            //mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
            if(getArguments().getString(ARG_ITEM_ID) != null)
            {
                int id = Integer.parseInt(getArguments().getString(ARG_ITEM_ID));
                int [] ids = getResources().getIntArray(R.array.item_ids);
                String[] names = getResources().getStringArray(R.array.item_names);
                String[] prices = getResources().getStringArray(R.array.item_prices);
                String[] shortDesc = getResources().getStringArray(R.array.item_shortDesc);
                String[] ingredients = getResources().getStringArray(R.array.item_ingredients);
                String[] cookingStyle = getResources().getStringArray(R.array.item_cookingStyle);
                id = id-1;
                item = new Item();
                item.setId(ids[id]);
                item.setName(names[id]);
                item.setPrice(prices[id]);
                item.setShortDesc(shortDesc[id]);
                item.setIngredients(ingredients[id]);
                item.setCookingStyle(cookingStyle[id]);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fooditem_detail, container, false);

        // Show the item detail as texts in a TextViews.
        if (item != null) {
//            ((TextView) rootView.findViewById(R.id.fooditem_id)).setText(Integer.toString(item.getId()));
//            ((TextView) rootView.findViewById(R.id.fooditem_name)).setText(item.getName());
//            ((TextView) rootView.findViewById(R.id.fooditem_price)).setText(item.getPrice());
          ((TextView) rootView.findViewById(R.id.fooditem_shortDesc)).setText(item.getShortDesc().trim());
            ((TextView) rootView.findViewById(R.id.fooditem_ingredients)).setText((item.getIngredients().trim()));
            ((TextView) rootView.findViewById(R.id.fooditem_cookingMethod)).setText(item.getCookingStyle());

            Resources res = getResources();
            int foodImageId = res.getIdentifier(
                    "s"+ item.getId(), "drawable", getActivity().getPackageName());

             Log.d("Food Menu","Image id = "+foodImageId);
            ((ImageView) rootView.findViewById(R.id.fooditem_img)).setImageResource(foodImageId);

        }
        return rootView;
    }
}

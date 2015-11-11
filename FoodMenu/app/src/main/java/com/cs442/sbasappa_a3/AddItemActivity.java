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
public class AddItemActivity extends Activity {

    private TextView inputfood;
    private TextView inputfoodprice;
    private TextView inputfoodingredient;
    private TextView inputreceipe;
    private Button  Addfood;
    private ContentResolver CR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.additem);

        inputfood=(TextView)findViewById(R.id.Foodname);
        inputfoodprice=(TextView)findViewById(R.id.FoodPrice);
        inputfoodingredient=(TextView)findViewById(R.id.FoodIngredient);
        inputreceipe=(TextView)findViewById(R.id.FoodReceipe);
        Addfood=(Button)findViewById(R.id.button2);
        CR = getContentResolver();
        Log.w("aaa","AddItemActivity.onCreate method done");

        Addfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("aaa","before if");
                if(inputfood.getText().length()==0 && inputreceipe.getText().length()==0 && inputfoodingredient.getText().length()==0 && inputfoodprice.getText().length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please input the values",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Log.w("aaa","in else body");
                    ContentValues CV = new ContentValues();
                    CV.put(Mycontentprovider.KEY_NAME,inputfood.getText().toString());
                    CV.put(Mycontentprovider.KEY_PRICE,inputfoodprice.getText().toString());
                    CV.put(Mycontentprovider.KEY_Ingredient,inputfoodingredient.getText().toString());
                    CV.put(Mycontentprovider.KEY_Receipe,inputreceipe.getText().toString());
                    CR.insert(Mycontentprovider.CONTENT_URI,CV);
                    showMenu(v);
                    Log.w("aaa","food added success");
                }
            }

        });
    }

    public void showMenu(View view){
        Intent intent = new Intent(this,ItemListActivity.class);
        startActivity(intent);
    }
/*
   public void ADDFOOD()
   {
       Log.w("aaa","before if");
      if(inputfood.getText().length()==0 && inputreceipe.getText().length()==0 && inputfoodingredient.getText().length()==0 && inputfoodprice.getText().length()==0)
      {
          Toast.makeText(this,"Please input the values",Toast.LENGTH_SHORT).show();
      }
       else
      {
          Log.w("aaa","in else body");
          ContentValues CV = new ContentValues();
          CV.put(Mycontentprovider.KEY_NAME,inputfood.getText().toString());
          CV.put(Mycontentprovider.KEY_PRICE,inputfoodprice.getText().toString());
          CV.put(Mycontentprovider.KEY_Ingredient,inputfoodingredient.getText().toString());
          CV.put(Mycontentprovider.KEY_Receipe,inputreceipe.getText().toString());
          CR.insert(Mycontentprovider.CONTENT_URI,CV);

      }
      }
     */
}

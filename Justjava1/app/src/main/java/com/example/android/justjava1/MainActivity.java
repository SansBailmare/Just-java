package com.example.android.justjava1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.justjava1.R;

import java.text.CharacterIterator;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int quantity=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void increment(View view) {
        quantity++;
        displayquantity(quantity);

    }
    public void decrement(View view) {

        if(quantity>0)
            quantity--;
        displayquantity(quantity);

    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int wc=0,c=0;
       CheckBox checkBox=(CheckBox)findViewById(R.id.checkbox_clicked);
       boolean toadd= checkBox.isChecked();
        CheckBox checkB=(CheckBox)findViewById(R.id.checkbox_click);
        boolean toadd2=checkB.isChecked();
        EditText text=(EditText)findViewById(R.id.name_Field);
        String value=text.getText().toString();
        if(toadd) {
            wc = 1;
        }
        if(toadd2){
            c=2;
        }
        int price=quantity*(5+wc+c);
        String priceMessage= createOrderSummary(price,toadd,toadd2,value);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this

        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order For "+value);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayquantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */

    public String createOrderSummary( int price, boolean add,boolean add2,String val){
        return "Name: "+val+"\nQuantity: "+quantity+"\nAdd whipped cream? "+add+"\nChocolate? "+add2+"\nTotal: $"+price+"\nThankyou u!!";
    }
}
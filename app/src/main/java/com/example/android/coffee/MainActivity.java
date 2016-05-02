package com.example.android.coffee;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int quantity = 0;

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText text = (EditText) findViewById(R.id.name_field);
        String value = text.getText().toString();

        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_check_box);
        boolean hasWhippedCream = whippedCream.isChecked();

        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_check_box);
        boolean hasChocolate = chocolate.isChecked();

        int price;
        String string;
        string = value + "\n" + "Whipped cream : ";

        price = 5;
        if(hasWhippedCream) {
            price += 1;
            string = string + "Yes";
        }
        else {
            string = string + "No";
        }
        string = string + "\nChocolate : ";
        if(hasChocolate) {
            price += 2;
            string= string + "Yes";
        }
        else {
            string = string + "No";
        }
        string = string + "\nQuantity : " + quantity;
        string = string + "\nTotal amount : $ ";

        price *= quantity;

        string = string + price + "\nThank you!";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT , "Coffee order for " + value);
        intent.putExtra(Intent.EXTRA_TEXT , string);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        displayMessage(string);
    }

    public void increment(View view) {
        if(quantity >= 10) {
            Toast.makeText(this,"10 coffees per customer :)",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        display(quantity);
    }

    public void decrement(View view) {
        if(quantity <= 1) {
            Toast.makeText(this,"Order at least 1 coffee :)",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        display(quantity);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayMessage(String string) {
        TextView stringTextView = (TextView) findViewById(R.id.order_summary_text_view);
        stringTextView.setText(string);
    }
}


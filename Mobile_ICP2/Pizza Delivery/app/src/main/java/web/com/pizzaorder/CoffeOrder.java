package web.com.pizzaorder;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CoffeOrder extends AppCompatActivity {
    private static final String MAIN_ACTIVITY_TAG = "CoffeOrder";
    final int COFFEE_PRICE = 5;
    final int WHIPPED_CREAM_PRICE = 1;
    final int CHOCOLATE_PRICE = 2;
    int quantity = 1;
    String orderSummary;
    EditText userNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffe);

        userNameText = (EditText) findViewById(R.id.user_input);
    }
    // Check for UserName error
    private boolean isUserEmpty(){
        // Checking If username is present or not
        String userName = userNameText.getText().toString();
        if(userName == null || userName.isEmpty()){
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.userNull);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return true;
        }
        return false;
    }
    /**This method is called when the order summary button is clicked. */

    public void orderSummary(View view) {
        if (!isUserEmpty()) {
            orderSummary = fetchDetails();
            Intent intent = new Intent(CoffeOrder.this, OrderSummary.class);
            intent.putExtra("orderSummary", orderSummary);
            startActivity(intent);
        }
    }

    /**This method is called when the order button is clicked. */

    public void orderCoffeeMain(View view) {
        if (!isUserEmpty()) {
            orderSummary = fetchDetails();
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
     // The intent does not have a URI, so declare the "text/plain" MIME type
            emailIntent.setType("plain/text");
      // Recipients
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"rmtcxk@mail.umkc.edu"});
     // Adding Subject
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Order Summary");
     // Adding the Order Summary Text
            emailIntent.putExtra(Intent.EXTRA_TEXT, orderSummary);
     // Redirecting to Email Intent
            startActivity(Intent.createChooser(emailIntent, ""));
        }
    }

    public String fetchDetails() {

       /* Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:47.6,-122.3"));
        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent);
        }*/

//  get user input
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();
//  check if whipped cream is selected
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_checked);
        boolean hasWhippedCream = whippedCream.isChecked();
// check if chocolate is selected
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checked);
        boolean hasChocolate = chocolate.isChecked();
//  calculate and store the total price
        float totalPrice = calculatePrice(hasWhippedCream, hasChocolate);
//  create and store the order summary
        String orderSummaryMessage = createOrderSummary(userInputName, hasWhippedCream, hasChocolate, totalPrice);
// Write the relevant code for making the buttons work(i.e implement the implicit and explicit intents
        return orderSummaryMessage;
    }
    private String boolToString(boolean bool){
        return bool?(getString(R.string.yes)):(getString(R.string.no));

    }

    private String createOrderSummary(String userInputName, boolean hasWhippedCream, boolean hasChocolate, float price) {
        String orderSummaryMessage = getString(R.string.order_summary_name,userInputName) +"\n"+"\n"+
                getString(R.string.order_summary_whipped_cream,boolToString(hasWhippedCream))+"\n"+"\n"+
                getString(R.string.order_summary_chocolate,boolToString(hasChocolate)) +"\n"+"\n"+
                getString(R.string.order_summary_quantity,quantity)+"\n"+"\n"+
                getString(R.string.order_summary_total_price,price) +"\n"+"\n"+
                getString(R.string.thank_you);
        return orderSummaryMessage;

    }


    /**
     * Method to calculate the total price
     *
     * @return total Price
     */
    private float calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int basePrice = COFFEE_PRICE;
        if (hasWhippedCream) {
            basePrice += WHIPPED_CREAM_PRICE;
        }
        if (hasChocolate) {
            basePrice += CHOCOLATE_PRICE;
        }

        return quantity * basePrice;

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);

    }


    /**
     * This method increments the quantity of coffee cups by one
     *
     * @param view on passes the view that we are working with to the method
     */

    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            display(quantity);
        } else {

            Log.i("CoffeOrder", "Please select less than one hundred cups of coffee");
            Context context = getApplicationContext();
            String lowerLimitToast = getString(R.string.too_much_coffee);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;

        }
    }

    /**
     * This method decrements the quantity of coffee cups by one
     *
     * @param view passes on the view that we are working with to the method
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Log.i("CoffeOrder", "Please select atleast one cup of coffee");
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.too_little_coffee);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;
        }
    }
}

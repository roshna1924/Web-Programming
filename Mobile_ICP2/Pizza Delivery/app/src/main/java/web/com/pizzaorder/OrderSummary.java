package web.com.pizzaorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.TextView;


// Create Order Summary
public class OrderSummary extends AppCompatActivity {

    TextView summaryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordersummary);
        summaryText = findViewById(R.id.summaryText);
        summaryText.setText(Html.fromHtml("<br/><h2><u><b>Your Order Summary</b></u></h2><br/>"));
        if(getIntent() != null){
            summaryText.append(getIntent().getStringExtra("orderSummary"));
        }else{
            summaryText.setText("You have no orders !!");
        }
        summaryText.append(Html.fromHtml("<br/>"));
        summaryText.setVisibility(View.VISIBLE);
    }

    // On Click of Order Coffee
    public void goBack(View view) {
        super.onBackPressed();
    }
}

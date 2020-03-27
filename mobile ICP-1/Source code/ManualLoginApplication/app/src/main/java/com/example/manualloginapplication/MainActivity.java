package com.example.manualloginapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b1;
    EditText ed1,ed2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button)findViewById(R.id.button);
        ed1 = (EditText)findViewById(R.id.editText6);
        ed2 = (EditText)findViewById(R.id.editText7);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed1.getText().toString().equals("yamini@gmail.com") &&
                        ed2.getText().toString().equals("manaswini123")) {
                    Toast.makeText(getApplicationContext(),
                            "Redirecting...",Toast.LENGTH_SHORT).show();
                    reDirectToHomePage();
                }else{
                    Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void reDirectToHomePage(){
        Intent redirect = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(redirect);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.example.mythreepagespassingparameters;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    // ***** Note nothing to be amended here for the new way *****
    // Changes to avoid warnings were :
    // 1- dealing with string to be displayed
    // 2- using lambda in setOnClickListener
    // ***********************************************************
    TextView textView ;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle extras = getIntent().getExtras();
        textView = findViewById(R.id.textViewPage2);
        button = findViewById(R.id.back_Btn);

        String coming_from_text = extras.getString("coming_from_text");
        String i_am_in_txt = extras.getString("i_am_in_txt");
        int coming_from_int = extras.getInt("coming_from_int");
        double i_am_in_double = extras.getDouble("i_am_in_double");
         String myString = getResources().getString(R.string.coming_from_page)
                 +coming_from_text+"\n"
                 +getResources().getString(R.string.i_am_in_page)
                 +i_am_in_txt+"\n"
                 +getResources().getString(R.string.coming_from_page)
                 +coming_from_int+"\n"
                 +getResources().getString(R.string.i_am_in_page)
                 +i_am_in_double;
        textView.setText(myString);

        button.setOnClickListener(v -> {
            Intent backIntent  = getIntent();
            backIntent.putExtra("i_am_in_txt","I was in Page 2");
            setResult(RESULT_OK, backIntent);
            finish();
        }); //end of setOnClickListener()
    }//end of onCreate()
}//end class
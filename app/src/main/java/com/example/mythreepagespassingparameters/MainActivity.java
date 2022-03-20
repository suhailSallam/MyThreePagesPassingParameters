package com.example.mythreepagespassingparameters;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button2, button3;
//    private final int REQUEST_CODE = 2; // In the new way no need for REQUEST_CODE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button2 = findViewById(R.id.buttonSecond);
        button3 = findViewById(R.id.buttonThird);
        textView = findViewById(R.id.textViewComingFrom);
// ===== As <startActivityForResult> was deprecated we use this code instead inside onCreate()
        ActivityResultLauncher<Intent> getResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode()== Activity.RESULT_OK){
                    Intent data = result.getData();

                    // what to do with data object came in result?
                    String returnData = data.getStringExtra("i_am_in_txt");
                    Toast.makeText(getApplicationContext(), returnData, Toast.LENGTH_LONG).show();
                    textView.setText(returnData);
                }
            }
        });
// ===== End of the code in the new way, but still one line for lunching your intent for each button down here

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                intent.putExtra("coming_from_text", "First Page");
                intent.putExtra("i_am_in_txt", "Second Page");
                intent.putExtra("coming_from_int", 1);
                intent.putExtra("i_am_in_double", 2.0);

//                startActivityForResult(intent, REQUEST_CODE); // this way was deprecated
                  getResult.launch(intent);                     // use this instead
            }//end of onClick() button2
        }); //end of setOnClickListener() button2

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                intent.putExtra("coming_from_text", "First Page");
                intent.putExtra("i_am_in_txt", "Third Page");
                intent.putExtra("coming_from_int", 1);
                intent.putExtra("i_am_in_double", 3.0);

//                startActivityForResult(intent, REQUEST_CODE); // this way was deprecated
                  getResult.launch(intent);                     // use this instead
            }//end of onClick() button3
        }); //end of setOnClickListener() button3
    }//end of onCreate()
// ==================== no need for this code any more in the new way
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//                String returnData = data.getStringExtra("i_am_in_txt");
//                //Toast.makeText(getApplicationContext(), returnData, Toast.LENGTH_LONG).show();
//                textView.setText(returnData);
//            }//end of inner if
//        }//end of outer if
//    }//end of onActivityResult()
}//end class
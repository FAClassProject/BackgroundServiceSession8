package com.aptech.backgroundservicesession8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
   public  int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        final TextView nextScreen = findViewById(R.id.asyncTask);

        nextScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, AsyncTaskDemo.class);
                myIntent.putExtra("key", "value"); //Optional parameters
                MainActivity.this.startActivity(myIntent);
            }
        });

        final TextView text = findViewById(R.id.text);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text.getText().toString().equals("Started")) {
                    text.setText("Stopped");
                    stopService(new Intent(MainActivity.this,service.class));
                } else {
                    text.setText("Started");
                    startService(new Intent(MainActivity.this,service.class));
                }
            }
        });
    }
}
package com.example.digitalrush;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    EditText userId;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button);
        userId = findViewById(R.id.editText2);
        ImageView img = findViewById(R.id.logo);
        img.setImageResource(R.raw.heart);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                переход на другой экран
                Intent intent = new Intent(MainActivity.this, Second.class);
                intent.putExtra("UserID", userId.getText().toString());
                startActivity(intent);

            }
        });
    }

}
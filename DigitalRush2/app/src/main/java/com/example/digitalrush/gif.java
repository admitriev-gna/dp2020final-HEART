package com.example.digitalrush;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class gif extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);
        Bundle arguments = getIntent().getExtras();
        String id_str = arguments.get("Id_image").toString();
        int id = Integer.parseInt(id_str);
        TextView text = findViewById(R.id.text);
        text.setText(Text_gifs.getText(id));
        ImageView img = findViewById(R.id.gif);
        Glide.with(this)
                .load(id)
                .into(img);
    }
}
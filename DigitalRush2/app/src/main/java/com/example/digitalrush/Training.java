package com.example.digitalrush;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Training extends AppCompatActivity {

    ArrayList<Integer> class1 = new ArrayList<>(Arrays.asList(R.raw.content, R.raw.content1, R.raw.content2));
    ArrayList<Integer> class2 = new ArrayList<>(Arrays.asList(R.raw.content3, R.raw.content4));
    ArrayList<Integer> class3 = new ArrayList<>(Arrays.asList(R.raw.content5, R.raw.content6));
    ArrayList<Integer> class4 = new ArrayList<>(Arrays.asList(R.raw.content7, R.raw.content8));
    ArrayList<ArrayList<Integer>> complex = new ArrayList<>(Arrays.asList(class1, class2, class3, class4));

    RecyclerView rv;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        rv = findViewById(R.id.rv_training);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);


        Bundle arguments = getIntent().getExtras();
        String id_str = arguments.get("Id").toString();
        id = Integer.parseInt(id_str);

        TrainingAdapter adapter = new TrainingAdapter(complex.get(id));
        rv.setAdapter(adapter);
    }

    void TrainGIF(int position) {
        Intent intent = new Intent(Training.this, gif.class);
        intent.putExtra("Id_image", complex.get(id).get(position));
        startActivity(intent);
    }
}
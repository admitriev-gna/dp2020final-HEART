package com.example.digitalrush;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Second extends AppCompatActivity {

    ArrayList<String> users;

    RecyclerView rv;
    ProgressBar progressBar, progressBar2, progressBar3;
    TextView son, steps, bpm, rec;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        rv = findViewById(R.id.rv);
        Bundle arguments = getIntent().getExtras();
        String id_str = arguments.get("UserID").toString();
        int id = Integer.parseInt(id_str);
        users = get_complex_by_id(id);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);


        RVAdapter adapter = new RVAdapter(users);
        rv.setAdapter(adapter);
        progressBar = findViewById(R.id.progressBar);
        progressBar2 = findViewById(R.id.progressBar2);
        progressBar3 = findViewById(R.id.progressBar3);
        son = findViewById(R.id.son);
        steps = findViewById(R.id.steps);
        bpm = findViewById(R.id.bpm);
        setProgressBars(id);
        setValues();
        rec = findViewById(R.id.recomendate);
        rec.setText(Text_gifs.getTextRec(id));

    }

    void goToTraining(int position) {
        Intent intent = new Intent(Second.this, Training.class);
        intent.putExtra("Id", Integer.parseInt(users.get(position)) - 1);
        startActivity(intent);
    }

    void showSnackbar(int position) {
        Snackbar.make(rv, "Карточка с номером "+position, Snackbar.LENGTH_LONG).show();
    }

    @SuppressLint("SetTextI18n")
    void setValues() {
        int value1 = (int)(4.8 * progressBar.getProgress() / 60);
        int value2 = (int)(4.8 * progressBar.getProgress() % 60);
        int value3 = (int) ((int) 100 * progressBar2.getProgress() + Math.random() * 200 - 100);
        int value4 = (int)(1.8 * progressBar3.getProgress());

        son.setText(value1 + "h:" + value2 + "m" );
        steps.setText("" + value3);
        bpm.setText(value4 + "bpm");

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void setProgressBars(int id) {
        switch (id) {
            case (1) :
                progressBar.setProgress(10, true);
                progressBar2.setProgress(30, true);
                progressBar3.setProgress(50, true);
                break;
            case (2) :
                progressBar.setProgress(80, true);
                progressBar2.setProgress(70, true);
                progressBar3.setProgress(70, true);
                break;
            case (3) :
                progressBar.setProgress(100, true);
                progressBar2.setProgress(40, true);
                progressBar3.setProgress(50, true);
                break;
            default:
                progressBar.setProgress(0, true);
                progressBar2.setProgress(0, true);
                progressBar3.setProgress(0, true);
                break;
        }
    }

    ArrayList<String> get_complex_by_id(int id) {
        ArrayList<String> complexes = new ArrayList<>();
        switch(id) {
            case (1) :
                complexes.add("1");
                complexes.add("4");
                break;
            case (2) :
                complexes.add("3");
                break;
            case (3) :
                complexes.add("2");
                break;
            default:
                Snackbar.make(rv, "На данный момент у вас нет рекомендаций", Snackbar.LENGTH_LONG).show();
                break;
        }
        return complexes;
    }
}
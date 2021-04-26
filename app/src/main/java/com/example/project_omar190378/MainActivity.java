package com.example.project_omar190378;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    MainActivity4 weather=new MainActivity4();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView act2 = (ImageView) findViewById(R.id.sqlview);
        ImageView act3 = (ImageView) findViewById(R.id.firebaseview);
        ImageView act4 = (ImageView) findViewById(R.id.weatherview);
        ImageView img = (ImageView) findViewById(R.id.imageView44);
        img.setImageResource(weather.getImgResource());

        act2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });

        act3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity3.class));
            }
        });

        act4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity4.class));
            }
        });
    }
}
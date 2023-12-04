package com.example.pr26_ovchinnikov_notifications;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import android.widget.Button;

public class Interfere extends AppCompatActivity implements View.OnClickListener {

    Button btn1, btn2, btn3, btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfere);
        btn1 = findViewById(R.id.website);
        btn2 = findViewById(R.id.website2);
        btn3 = findViewById(R.id.website3);
        btn4 = findViewById(R.id.btnBack);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.website) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://1gai.ru/baza-znaniy/525470-pochemu-ljudi" +
                    "-ljubopytny-i-kak-rabotaet-mehanizm-ljubopytstva.html")));
        }
        else if (view.getId() == R.id.website2) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Curiosity")));
        }
        else if (view.getId() == R.id.website3) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://trends.rbc.ru/trends/social/6481b5ad9a7947b16addea1b")));
        }
        else if (view.getId() == R.id.btnBack) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
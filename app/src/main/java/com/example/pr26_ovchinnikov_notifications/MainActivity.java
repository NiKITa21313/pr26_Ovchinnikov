package com.example.pr26_ovchinnikov_notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView programmer;
    NotificationManager manager;
    NotificationChannel channel;
    Button btnCoffe, btnUp;
    Toast toast;
    int counter = 0;
    String ID_CHANEL ="ID_NOTIFICATION";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //interfere1 = findViewById(R.drawable.interfere1);
        //interfere2 = findViewById(R.drawable.interfere2);
        //interfere3 = findViewById(R.drawable.interfere3);
        programmer = findViewById(R.id.imgProgrammer);//нажатие на картинку
        programmer.setOnClickListener(this);
        btnCoffe = findViewById(R.id.btnCoffe);//кнопка кофе
        btnCoffe.setOnClickListener(this);
        btnUp = findViewById(R.id.btnPleaseUp);
        btnUp.setOnClickListener(this);
        channel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel(
                    ID_CHANEL,
                    "NameChanel",
                    NotificationManager.IMPORTANCE_DEFAULT);
        }
        manager = getSystemService(NotificationManager.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(channel);
        }
        Notification notification = new NotificationCompat.Builder(this, ID_CHANEL)
                .setContentTitle("Запуск")
                .setContentText("Добро пожаловать в наше приложение")
                .setSmallIcon(R.drawable.process)
                .setColor(Color.DKGRAY)
                .build();//собранное уведомление
        manager.notify(1, notification);
        //закрытие уведомления через 5 секунд после запуска
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                manager.cancel(1);
            }
        },5000);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.imgProgrammer){
            counter++;
            Notification notification = new NotificationCompat.Builder(this, ID_CHANEL)
                    .setContentTitle("В процессе")
                    .setContentText("Программист работает, не мешайте ему")
                    .setSmallIcon(R.drawable.process)
                    .setColor(Color.DKGRAY)
                    .build();//собранное уведомление
            manager.notify(2, notification);
            if(counter == 2){
                Toast toast = new Toast(this);
                ImageView view = new ImageView(this);
                view.setImageResource(R.drawable.interfere1);
                toast.setView(view);
                //toast.setText(R.string.interfere1);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, -1500);
                toast.show();
            }
            if(counter == 3){
                Toast toast = new Toast(this);
                ImageView view = new ImageView(this);
                view.setImageResource(R.drawable.interfere2);
                toast.setView(view);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            }
            if(counter > 3){
                Toast toast = new Toast(this);
                ImageView view = new ImageView(this);
                view.setImageResource(R.drawable.interfere3);
                toast.setView(view);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
                counter = 0;
            }
        }
        else if(v.getId() == R.id.btnCoffe){
            Notification notification = new NotificationCompat.Builder(this, ID_CHANEL)
                    .setContentTitle("Перерыв")
                    .setContentText("Самое время глотнуть для бодрости")
                    .setSmallIcon(R.drawable.coffe)
                    .setColor(Color.GREEN)
                    .build();//собранное уведомление
            manager.notify(3, notification);
        }
        else if (v.getId()==R.id.btnPleaseUp) {
            Intent intent = new Intent(this, Interfere.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
            Notification notification = new NotificationCompat.Builder(this, ID_CHANEL)
                    .setContentTitle("Нереально")
                    .setContentText("Вы просите невозможного(нажмите...)")
                    .setSmallIcon(R.drawable.impossible)
                    .setColor(Color.DKGRAY)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build();//собранное уведомление
            manager.notify(4, notification);
        }
    }
}
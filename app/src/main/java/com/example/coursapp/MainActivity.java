package com.example.coursapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Document document;
    private Thread secondThread;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        runnable = new Runnable() {
            @Override
            public void run() {
                getWeb();
            }
        };
        secondThread = new Thread(runnable);
        secondThread.start();
    }

    private void getWeb() {
        try {
            document = Jsoup.connect("https://minfin.com.ua/currency/").get();
            Log.d("MyLog06","Title : " + document.title());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
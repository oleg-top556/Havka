package com.example.havka;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 *
 *  Cторінка інформації для певної страви.
 *  Містить фото та детальну інформацію про страву.
 *  @version 0.0
 *
 */
public class Information extends AppCompatActivity {

    private TextView textView;
    private StringBuilder information = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        // Ініцілізуємо змінну та присвоюємо їй обєкт з activity_main.xml
        BottomNavigationView bottomNavigationView = findViewById(R.id.bootom_navigation);

        // Спочатку вибране "Meals",бо це головна сторінка
        bottomNavigationView.setSelectedItemId(R.id.inforamation);

        // Переключатель сторінок
        /**
         *  Нижнє поле навігації.
         *  Перехід між сторінками №5, №6, №7
         *  По стандарту вибрана сторінкка №5
         */
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ingridients:
                        startActivity(new Intent(getApplicationContext(),Ingridients.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.inforamation:

                        return true;
                    case R.id.instructions:
                        startActivity(new Intent(getApplicationContext(),Instructions.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }

        });
        readFile();
    }
    /*
    Зчитуємо інформацію з файлу і виводимо на екран
     */
    private void readFile(){
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("Borsch.txt"))
            );

            String line;
            while ((line = reader.readLine()) != null) {
                information.append(line);
                information.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            TextView output = (TextView) findViewById(R.id.aboutBorsch);
            output.setText((CharSequence) information);
        }
    }
}

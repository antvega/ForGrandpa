package com.example.antoniovega.forgrandpa;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends Activity {

    private Button submit;
    private EditText enterMovie;
    private int counter;

    private Resources resources;
    //a string to output the contents of the files to LogCat
    private String output;

    private ArrayList<String> movieNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieNames = new ArrayList<String>();
        counter = 0;


        enterMovie = findViewById(R.id.main_movieField_editText);
        submit = findViewById(R.id.main_submit_button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String movieInput = enterMovie.getText().toString();

                InputStream inputStream = getResources().openRawResource(R.raw.movies);
                BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
                String eachline = null;

                boolean movieExists = false;
                try {
                    eachline = bufferedReader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                while (eachline != null) {
                    // `the words in the file are separated by space`, so to get each words
                    if(movieInput.equals(eachline)){
                        movieExists = true;
                        break;
                    }
                    //movieNames.add(eachline);
                    try {
                        eachline = bufferedReader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                toastMovieExists(movieExists);

            }
        });
    }

    public void toastMovieExists(boolean b){
        if(b){
            Toast.makeText(MainActivity.this,"Movie exists", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this,"Movie does not exist", Toast.LENGTH_LONG).show();
        }
    }
}

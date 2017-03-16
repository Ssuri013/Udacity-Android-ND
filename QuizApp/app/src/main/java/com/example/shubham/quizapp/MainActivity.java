package com.example.shubham.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    void checkAnswers(View view){
        boolean result = true;
        EditText tx = (EditText) findViewById(R.id.name);
        RadioButton rb = (RadioButton) findViewById(R.id.relative);
        RadioButton rb2 = (RadioButton) findViewById(R.id.view);
        CheckBox cb1 = (CheckBox) findViewById(R.id.checkbox_sp);
        CheckBox cb2 = (CheckBox) findViewById(R.id.checkbox_dp);
        CheckBox cb3 = (CheckBox) findViewById(R.id.checkbox_wrap);
        CheckBox cb4 = (CheckBox) findViewById(R.id.checkbox_match);
        int score = 0;

        if( tx.getText().toString().trim().equals("Nougat")){
            score++;
        }
        if(rb.isChecked()){
            score++;
        }
        if(!cb1.isChecked()  && cb2.isChecked() && cb3.isChecked() && cb4.isChecked()){
            score++;
        }
        if(rb2.isChecked()){
            score++;
        }


        Toast.makeText( this, "Checking...", Toast.LENGTH_SHORT).show();
        if(score == 4){

           Toast.makeText(this, "All answers are right", Toast.LENGTH_LONG).show();
        }
        else{

            Toast.makeText(this, "correct answers are" + score + "/4", Toast.LENGTH_LONG).show();
        }
    }
}
package com.yavuz.alertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText ageText;
    TextView resultText;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ageText = findViewById(R.id.ageText);
        resultText = findViewById(R.id.resultText);
        sharedPreferences = this.getSharedPreferences("com.yavuz.alertdialog", Context.MODE_PRIVATE);
        int storedAge = sharedPreferences.getInt("storedAge", 0);

        if(storedAge != 0){
            resultText.setText("Your age : " + storedAge);
        } else{
            resultText.setText("Your age : ");
        }

    }

    public void save(View view){

        if(ageText.getText().toString().matches("")){

            Toast.makeText(MainActivity.this, "Please enter your age!", Toast.LENGTH_LONG).show();

        }

        else{

            int userAge = Integer.parseInt(ageText.getText().toString());

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Save");
            alert.setMessage("Are you sure ?");

            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    sharedPreferences.edit().putInt("storedAge", userAge).apply();
                    Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_LONG).show();
                    resultText.setText("Your age : " + userAge);
                }
            });

            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Toast.makeText(MainActivity.this,"Not saved!", Toast.LENGTH_LONG).show();
                }
            });

            alert.show();
        }

    }

    public void delete(View view){

        int storedData = sharedPreferences.getInt("storedAge", 0);

        if(ageText.toString().matches("") || storedData != 0){

            if (storedData != 0){
                sharedPreferences.edit().remove("storedAge").apply();
                resultText.setText("Your age : ");
                ageText.getText().clear();
            }
        }

        else{

            Toast.makeText(MainActivity.this, "Please enter your age!", Toast.LENGTH_LONG).show();
        }
    }

}
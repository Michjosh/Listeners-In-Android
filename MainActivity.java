package com.mikul.listeners;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    private static EditText toToastText;
    @SuppressLint("StaticFieldLeak")
    private static Button clearButton;
    @SuppressLint("StaticFieldLeak")
    private static Button toastButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toToastText = findViewById(R.id.editTextTextEmailAddress);
        clearButton = findViewById(R.id.buttonClear);
        toastButton = findViewById(R.id.buttonToast);

        clearButton.setOnClickListener(new ButtonOnClickListener());
        toastButton.setOnClickListener(new ButtonOnClickListener());

        clearButton.setOnLongClickListener(new ButtonHintOnLongClickListener());
        toastButton.setOnLongClickListener(new ButtonHintOnLongClickListener());

        toToastText.setOnClickListener(new TextOnClickListener());
    }


    private static class TextOnClickListener implements View.OnClickListener {
        private final ButtonOnClickListener buttonOnClickListener = new ButtonOnClickListener();

        @Override
        public void onClick(View view) {
            clearButton.setOnClickListener(buttonOnClickListener);
            toastButton.setOnClickListener(buttonOnClickListener);
        }
    }


    private static class ButtonOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.buttonClear){
                toToastText.setText("");
            }
            else if (view.getId() == R.id.buttonToast){
                Toast.makeText(view.getContext(), toToastText.getText(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private static class ButtonHintOnLongClickListener implements View.OnLongClickListener{

        @Override
        public boolean onLongClick(View view) {
            String hint = null;
            Context context = view.getContext();
            if (view.getId() == R.id.buttonClear){
               hint = "This button would clear the text";
            }
            else if (view.getId() == R.id.buttonToast){
                hint = "This button would toast the text";

            }
            Toast.makeText(context, hint, Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}


package com.sebrs3018.passaggiodatitraactivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AnotherActivity extends Activity {


    TextView tvMessage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another_activity); //Riciclando XML di activity

        tvMessage = findViewById(R.id.tvMessageReceived);

        //3) Recuperare il messaggio nell'altro activity
        Intent i = getIntent();

        //recupero messaggio string
        String _message = i.getStringExtra(getString(R.string.LABEL_MESSAGE));
        tvMessage.setText(_message);

    }



}

package com.sebrs3018.passaggiodatitraactivities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    final int ACTIVITY_REQUEST_CODE = 1;

    Button bttStartActivity = null, bttStartActivity4Results = null;
    EditText etMessagge = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bttStartActivity = findViewById(R.id.bttStartAcitvity);
        bttStartActivity4Results = findViewById(R.id.bttStartAcitvity4Results);

        etMessagge = findViewById(R.id.etMessage);

        bttStartActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _text = etMessagge.getText().toString();

                //intent: oggetti/entitàche servono per attivare/triggerare altri eventi dall'interno del SO
                //hanno capacità di portarsi dietro delle info (verso altre entità)

                /*
                * Intenti espliciti: messaggi che hanno "bersaglio" ben preciso
                * Intenti impliciti: messaggi con bersagli non precisi
                * Entrambi lanciati in BROADCAST
                * */

                //In questo caso si crea un intento ESPLICITO
                Intent intent = new Intent(getString(R.string.launch_activity)); //azione dell'intento. E' proprio questa che lo rende esplicito

                /*
                * 1) Rendere AnotherActivity  "consapevole" di doversi attivare alla ricezione del mio intento
                *       (vd Manifest)
                * 2) Associare all'intento il messaggio da mandare all'altro activity
                * 3) Recuperare il messaggio nell'altro activity (in AnotherActivity.java)
                * */

                //2) Associare all'intento il messaggio da mandare all'altro activity
                intent.putExtra(getString(R.string.LABEL_MESSAGE), _text); //aggiunge un info extra all'intent
                startActivity(intent);
            }
        });

        //Con questa versione, si vuole modificare i dati e rimandarlo indietro (creando un activity temporaneo di modifica)
        bttStartActivity4Results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _text = etMessagge.getText().toString();
                Intent intent4Results = new Intent(getString(R.string.launch_activity_4_results));
                intent4Results.putExtra(getString(R.string.LABEL_MESSAGE), _text);
                //il secondo parametro mi serve per capire QUANDO next activity ha finito
                //successiva riga di codice chiama onActiviyResult
                startActivityForResult(intent4Results, ACTIVITY_REQUEST_CODE);
            }
        });

    }


    //Chiamato in automatico quando un activity chiama metodo setResult --> arrivaa starActiviyForResult
    //requestCode corrisponde al code per chiamarla ==> dato da setResult (riga 69)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //controllo che il request_code sia quello che voglio io
        if( requestCode == ACTIVITY_REQUEST_CODE ){
            //controllo che l'activity di supporto mi abbia inviato il risultato corretto (definito da me)
            if( resultCode == Activity.RESULT_OK ){
               String _s = data.getStringExtra(getString(R.string.LABEL_MESSAGE_RETURN));
               etMessagge.setText(_s);
            }
        }

    }
}
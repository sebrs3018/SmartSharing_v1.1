package com.sebrs3018.passaggiodatitraactivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AnotherActivity4Results extends Activity {

    EditText etResult = null;
    Button bttOK = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another_activity_4_results);

        etResult = findViewById(R.id.etResult);
        bttOK = findViewById(R.id.bttOK);

        Intent i = getIntent();
        String _text = i.getStringExtra(getString(R.string.LABEL_MESSAGE));
        etResult.setText(_text);


        bttOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Lo rifaccio perchè ho appena modificato il campo in etResult... Così mi assicuro di avere risultato fresco
                String _s = etResult.getText().toString();
                Intent ii = new Intent(); //poichè è stato lanciato da intento4results, mi basta lasciarlo vuoto
                ii.putExtra(getString(R.string.LABEL_MESSAGE_RETURN), _s);

                //interessante notare che ci sono dei valori di default per comunicare esito della computazione
                setResult(Activity.RESULT_OK, ii);
                //ora lo devo killare
                finish();

            }
        });

    }
}

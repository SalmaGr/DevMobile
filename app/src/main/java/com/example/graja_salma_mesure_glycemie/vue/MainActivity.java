package com.example.graja_salma_mesure_glycemie.vue;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.graja_salma_mesure_glycemie.R;
import com.example.graja_salma_mesure_glycemie.controller.Controller;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_CODE = 1;
    private EditText etValeur; //l’EditText qui comporte la valeur mesurée.
    private TextView tvAge;
    private SeekBar sbage;
    private RadioButton rbtOui;
    private RadioButton rbtnon;
    private Button btnConsulter;
    //private TextView tvReponse;
    private Controller controller = Controller.getInstance();
    //private Controller controller = new Controller();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        sbage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Information", "onProgressChanged " + progress);
                // affichage dans le Log de Android studio pour voir les changements détectés sur le SeekBar ..
                tvAge.setText("Votre âge : " + progress);
                // Mise à jour du TextView par la valeur : progress à chaque changement.
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekbar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculer(v);
            }
        });
    }

    private void init() {
        etValeur = findViewById(R.id.etValeur);
        tvAge = findViewById(R.id.tvAge);
        sbage = findViewById(R.id.sbage);
        rbtOui = findViewById(R.id.rbtOui);
        rbtnon = findViewById(R.id.rbtnon);
        btnConsulter = findViewById(R.id.btnConsulter);
        //tvReponse = findViewById(R.id.tvReponse);
    }

    public void calculer(View view) {
        Log.i("Information", "button cliqué");
        int age;
        float valeurMesuree;
        boolean verifAge = false, verifValeur = false;
        if (sbage.getProgress() != 0)
            verifAge = true;
        else
            Toast.makeText(MainActivity.this, "Veuillez saisir votre age !",

                    Toast.LENGTH_SHORT).show();

        if (!etValeur.getText().toString().isEmpty())
            verifValeur = true;
        else
            Toast.makeText(MainActivity.this, "Veuillez saisir votre valeur mesurée !",

                    Toast.LENGTH_LONG).show();

        if(verifAge && verifValeur)
        {
            age = sbage.getProgress();
            valeurMesuree = Float.valueOf(etValeur.getText().toString());

            //Flèche "User action" Vue --> Controller
            controller.createPatient(age, valeurMesuree, rbtOui.isChecked());

            //Flèche "Update" Controller --> vue
            //tvReponse.setText(controller.getReponse());
            Intent intent = new Intent (MainActivity.this, ConsultActivity.class);
            intent.putExtra("reponse",controller.getReponse());
            startActivityForResult(intent, REQUEST_CODE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE)
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(MainActivity.this, "ERROR : RESULT_CANCELED", Toast.LENGTH_SHORT).show();
            }
    }
}
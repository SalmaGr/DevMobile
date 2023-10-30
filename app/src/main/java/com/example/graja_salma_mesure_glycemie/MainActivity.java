package com.example.graja_salma_mesure_glycemie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etValeur; //l’EditText qui comporte la valeur mesurée.
    private TextView tvAge;
    private SeekBar sbage;
    private RadioButton rbtOui;
    private RadioButton rbtnon;
    private Button btnConsulter ;
    private TextView tvReponse;
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
                tvAge.setText("Votre âge : "+ progress);
                // Mise à jour du TextView par la valeur : progress à chaque changement.
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekbar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculer(v);
            }
        });
    }

    private void init()
    {
        etValeur = findViewById(R.id.etValeur);
        tvAge = findViewById(R.id.tvAge);
        sbage = findViewById(R.id.sbage);
        rbtOui = findViewById(R.id.rbtOui);
        rbtnon = findViewById(R.id.rbtnon);
        btnConsulter = findViewById(R.id.btnConsulter);
        tvReponse = findViewById(R.id.tvReponse);
    }

    public void calculer(View view) {
        Log.i("Information", "button cliqué");
        int age = sbage.getProgress();
        double glucoseLevel;
        boolean verifAge = false, verifValeur = false;
        if (age !=0)
            verifAge = true;
        else
            Toast.makeText(MainActivity.this, "Veuillez saisir votre âge !", Toast.LENGTH_SHORT).show();
        if(!etValeur.getText().toString().isEmpty())
            verifValeur = true;
        else
            Toast.makeText(MainActivity.this, "Veuillez saisir votre niveau de glycémie !", Toast.LENGTH_LONG).show();
        if(verifAge && verifValeur) {
            glucoseLevel = Double.parseDouble(etValeur.getText().toString());
            boolean jeune = rbtOui.isChecked();
            if (jeune) {
                if (age > 13) {
                    if (glucoseLevel >= 5.0 && glucoseLevel <= 7.2) {
                        tvReponse.setText("Niveau de glycémie est normal");
                    } else if (glucoseLevel < 5.0) {
                        tvReponse.setText("Niveau de glycémie est trop bas!!");
                    } else {
                        tvReponse.setText("Niveau de glycémie est trop élevé!!");
                    }
                } else {
                    if (glucoseLevel >= 5.0 && glucoseLevel <= 10) {
                        tvReponse.setText("Niveau de glycémie est normal");
                    } else if (glucoseLevel < 5.0) {
                        tvReponse.setText("Niveau de glycémie est trop bas!!");
                    } else {
                        tvReponse.setText("Niveau de glycémie est trop élevé!!");
                    }
                }
            } else {
                if (glucoseLevel >= 5.0 && glucoseLevel <= 10.5) {
                    tvReponse.setText("Niveau de glycémie est normal");
                } else if (glucoseLevel < 5.0) {
                    tvReponse.setText("Niveau de glycémie est trop bas!!");
                } else {
                    tvReponse.setText("Niveau de glycémie est trop élevé!!");
                }
            }
        }
    }


}
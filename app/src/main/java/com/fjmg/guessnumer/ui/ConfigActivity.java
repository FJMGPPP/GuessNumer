package com.fjmg.guessnumer.ui;


import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.fjmg.guessnumer.R;
import com.fjmg.guessnumer.data.Partida;
import com.fjmg.guessnumer.databinding.ActivityConfigBinding;

import java.util.Random;

public class ConfigActivity extends AppCompatActivity {

    /**
     * Funcion para modificar el evento click de la escena
     * @param binding binding de la escena que contiene el boton iniciar
     */
    void ModificarEventClick(ActivityConfigBinding binding)
    {
        Button btnIniciar = binding.btnIniciar;
         btnIniciar.setOnClickListener((view ->
        {
            //Alertas creadas para control de errores del formulario
            AlertDialog alertNombreJugadorAlert = CrearAlerta(getString(R.string.errorNombreJugador),
                                                              getString(R.string.errorNombreJugadorText)
                                                             );
            AlertDialog alertIntentosAlert = CrearAlerta(getString(R.string.errorNumeroIntentosInvalido),
                                                              getString(R.string.errorNumeroIntentosInvalidoText)
                                                             );

            if(binding.nameJugadorEditText.getText().toString().isEmpty() == true)
            {
                alertNombreJugadorAlert.show();
            }
            else if(binding.numeroIntentoseEditText.getText().toString().isEmpty() == true )
            {
                alertIntentosAlert.show();
            }
            else
            {
                int nIntentos = -1;
                boolean iniciarPartida = false;
                try {
                    nIntentos = Integer.parseInt(binding.numeroIntentoseEditText.getText().toString());
                    if (nIntentos > 0)
                    {
                        iniciarPartida = true;
                    }
                    else {
                        alertIntentosAlert.show();
                        iniciarPartida = false;
                    }

                }
                catch (Exception e)
                {
                    alertIntentosAlert.show();

                }
                if(iniciarPartida)
                {
                    String NombreJugador = binding.nameJugadorEditText.getText().toString();
                    Random rnd = new Random();
                    int NumeroDeVictoria = rnd.nextInt(100);
                    Partida game = new Partida(NombreJugador,nIntentos,NumeroDeVictoria);
                    IniciarPartida(game);
                }
            }

        }));
    }

    void IniciarPartida(Partida game)
    {
        Bundle carta = new Bundle();
        carta.putSerializable("game",game);
        Intent paquete = new Intent(this, PlayActivity.class);
        paquete.putExtras(carta);
        startActivity(paquete);
    }
    AlertDialog CrearAlerta(String Titulo , String Texto)
    {

        AlertDialog.Builder alertaIntentosConstructor = new AlertDialog.Builder(this);
        alertaIntentosConstructor.setTitle(Titulo);
        alertaIntentosConstructor.setMessage(Texto);
        return alertaIntentosConstructor.create();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ActivityConfigBinding binding = ActivityConfigBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ModificarEventClick(binding);
    }
}
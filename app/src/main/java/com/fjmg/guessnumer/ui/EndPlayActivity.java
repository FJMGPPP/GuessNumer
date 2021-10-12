package com.fjmg.guessnumer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.fjmg.guessnumer.R;
import com.fjmg.guessnumer.data.Partida;
import com.fjmg.guessnumer.databinding.ActivityEndplayBinding;
import com.fjmg.guessnumer.databinding.ActivityPlayBinding;

public class EndPlayActivity  extends AppCompatActivity
{
    ActivityEndplayBinding binding;
    Partida game;
    boolean gano;
    int nIntentos;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEndplayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        optenerResultado();
        binding.finalNintentos.append( " "+(game.getNumeroIntentos() - nIntentos));
        if (gano)
        {
            binding.ganador.setText(getString(R.string.ganaste));
        }
        else
            {
                binding.ganador.setText(getString(R.string.perdiste));
            }
        binding.ganador.append(" "+game.getUsuario());
        binding.numeroMagicoFinal.append(" " + game.getNumeroGenerado());
    }
    void optenerResultado()
    {
        Intent paquete = getIntent();
        Bundle carta = paquete.getExtras();
        game = (Partida) carta.getSerializable("game");
        gano  = carta.getBoolean("gano");
        nIntentos = carta.getInt("nIntentosFinales");
    }
}

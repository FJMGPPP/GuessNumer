package com.fjmg.guessnumer.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fjmg.guessnumer.R;
import com.fjmg.guessnumer.data.Partida;
import com.fjmg.guessnumer.databinding.ActivityPlayBinding;

public class PlayActivity extends AppCompatActivity
{
    ActivityPlayBinding binding;
    int nintentos;
    int numeroMagico;
    Partida game;
    Partida RecibirJuego(Intent paquete)
    {
       Bundle carta = paquete.getExtras();
       return (Partida) carta.getSerializable("game");
    }
    AlertDialog CrearAlerta(String Titulo , String Texto)
    {

        AlertDialog.Builder alertaIntentosConstructor = new AlertDialog.Builder(this);
        alertaIntentosConstructor.setTitle(Titulo);
        alertaIntentosConstructor.setMessage(Texto);
        return alertaIntentosConstructor.create();
    }
    /**
     * Comprueba si el numero es el buscado
     * @param binding el binding de la view
     * @return -1 error no cuenta , 3 falso no lo adivino y es menor , 4 falso y es mayor , 1 lo adivino
     */
    int ComprobarNumero(ActivityPlayBinding binding)
    {
        String posiblenumero = binding.numeroAdivinaEditText.getText().toString();
        int numero = -1;
        try{
            numero = Integer.parseInt(posiblenumero);
            if (numero <= -1 )
            {
                CrearAlerta(getString(R.string.errorNumeroMagicoError),getString(R.string.errorNumeroMagicoErrorText)).show();
                return -1;
            }
        }catch (Exception e)
        {
            CrearAlerta(getString(R.string.errorNumeroMagicoError),getString(R.string.errorNumeroMagicoErrorText)).show();
            return -1;
        }
        if (numero == numeroMagico)
        {
            return  1;
        }else
            {
                if (numero > numeroMagico)
                    return 3;
                if (numero < numeroMagico)
                    return 4;
        }
        return -1;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  ActivityPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        game = RecibirJuego(getIntent());
        nintentos =  game.getNumeroIntentos();
        numeroMagico = game.getNumeroGenerado();
        binding.comprobar.setOnClickListener((view -> {
            if (nintentos > 0 )
            {
                nintentos--;
                switch (ComprobarNumero(binding))
                {
                    case 1:
                        MostrarResultado(true);
                        break;
                    case -1:
                        binding.simbol.setText(getString(R.string.simbol));
                        break;
                    case 3:
                        binding.simbol.setText(getString(R.string.simbolNegative));
                        break;
                    case 4:
                        binding.simbol.setText(getString(R.string.simbolPositive));
                        break;
                }
            }else
                {
                    MostrarResultado(false);
                }

        }));
    }
    void MostrarResultado(boolean gano)
    {
        Bundle carta = new Bundle();
        carta.putSerializable("game",game);
        carta.putBoolean("gano",gano);
        carta.putInt("nIntentosFinales",nintentos);
        Intent paquete = new Intent(this,EndPlayActivity.class);
        paquete.putExtras(carta);
        startActivity(paquete);
    }
}

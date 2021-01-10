package br.edu.ifsp.scl.ads.s5.pdm.dices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import br.edu.ifsp.scl.ads.s5.pdm.dices.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private TextView resultadoTv;

    Integer resultado;
    String resultadoImagem1;


    private final String RESULTADO_SORTEADO_TV = "RESULTADO_SORTEADO_TV";
    private final String IMAGEM_RESULTADO_DADO_1 = "IMAGEM_RESULTADO_DADO_1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(getString(R.string.app_name), "onCreate executado - iniciado ciclo de vida completo");

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        resultadoTv = findViewById(R.id.resultadoTv);

        if(savedInstanceState != null){
            resultadoTv.setText(savedInstanceState.getString((RESULTADO_SORTEADO_TV)));
            resultado = Integer.parseInt(savedInstanceState.getString(RESULTADO_SORTEADO_TV, ""));
            resultadoImagem1 = savedInstanceState.getString(IMAGEM_RESULTADO_DADO_1, "");

            //VERIFICAR COMO SALVAR O DADO SORTEADO NA TELA
            /*System.out.println("resultado salvo:" + resultadoImagem1);
            activityMainBinding.resultadoIv.setImageResource(
                    getResources().getIdentifier(resultadoImagem1,"drawable", getPackageName())
            );*/
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(RESULTADO_SORTEADO_TV, resultadoTv.getText().toString());

        //VERIFICAR COMO SALVAR O DADO SORTEADO NA TELA
        /*outState.putString(IMAGEM_RESULTADO_DADO_1, resultadoImagem1);
        System.out.println("salvando resultado:" + resultadoImagem1);*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.sairMi:
                finish();
                return true;

            default:
                return false;
        }
    }

    public void onClick(View view) {
        if(view == activityMainBinding.jogarBt){
            resultado = new Random(System.currentTimeMillis()).nextInt(6) + 1;
            activityMainBinding.resultadoTv.setText(resultado.toString());

            String resultadoImagem1 = "dice_"+resultado;
            activityMainBinding.resultadoIv.setImageResource(
                    getResources().getIdentifier(resultadoImagem1,"drawable", getPackageName())
            );
        }
    }


}
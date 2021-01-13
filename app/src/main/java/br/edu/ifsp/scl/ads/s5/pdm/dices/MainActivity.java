package br.edu.ifsp.scl.ads.s5.pdm.dices;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    private Configuracoes configuracoes = new Configuracoes(false,"6");

    private Integer numFaces;
    private Integer resultado;
    private Integer resultado2;
    private String resultadoImagem1;
    private String resultadoImagem2;


    private final String RESULTADO_SORTEADO_TV = "RESULTADO_SORTEADO_TV";
    private final String IMAGEM_RESULTADO_DADO_1 = "IMAGEM_RESULTADO_DADO_1";
    private final int CONFIGURACOES_REQUEST_CODE = 0;
    public static final String EXTRA_CONFIGURACOES = "EXTRA_CONFIGURACOES";
    private final String CONFIGURACOES = "CONFIGURACOES";
    private final String DOIS_DADOS = "DOIS_DADOS";
    private final String IMAGEM_DADO_1 = "IMAGEM_DADO_1";
    private final String NUMERO_FACES = "NUMERO_FACES";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(getString(R.string.app_name), "onCreate executado - iniciado ciclo de vida completo");

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        resultadoTv = findViewById(R.id.resultadoTv);

        if(savedInstanceState != null){
            resultadoTv.setText(savedInstanceState.getString((RESULTADO_SORTEADO_TV)));
            if(!savedInstanceState.getString(RESULTADO_SORTEADO_TV).equals("Não Lançado")){
                resultado = Integer.parseInt(savedInstanceState.getString(RESULTADO_SORTEADO_TV, ""));
            }
            //resultado = Integer.parseInt(savedInstanceState.getString(RESULTADO_SORTEADO_TV, ""));
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
        outState.putBoolean(DOIS_DADOS, configuracoes.getDoisDados());
        outState.putString(NUMERO_FACES, configuracoes.getNumFaces());
        //outState.putString(IMAGEM_DADO_1, dado.getDice1Image());

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
            case R.id.configuracoesMi:
                Intent configuracoesIntent = new Intent(CONFIGURACOES);
                configuracoesIntent.putExtra(EXTRA_CONFIGURACOES, configuracoes);
                startActivityForResult(configuracoesIntent, CONFIGURACOES_REQUEST_CODE);
                numFaces = Integer.parseInt(configuracoes.getNumFaces());
                if(numFaces>6){
                    findViewById(R.id.resultadoIv).setVisibility(View.GONE);
                }
                return true;

            case R.id.sairMi:
                finish();
                return true;

            default:
                return false;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CONFIGURACOES_REQUEST_CODE && resultCode == RESULT_OK && data != null){
            configuracoes = data.getParcelableExtra(EXTRA_CONFIGURACOES);
            if (configuracoes != null ) {
                numFaces = Integer.parseInt(configuracoes.getNumFaces());
                if (numFaces > 6) {
                    findViewById(R.id.resultadoIv).setVisibility(View.GONE);
                    findViewById(R.id.resultado2Iv).setVisibility(View.GONE);
                } else if (numFaces <= 6 && configuracoes.getDoisDados()) {
                    findViewById(R.id.resultadoIv).setVisibility(View.VISIBLE);
                    findViewById(R.id.resultado2Iv).setVisibility(View.VISIBLE);
                } else if (numFaces <= 6 && !configuracoes.getDoisDados()) {
                    findViewById(R.id.resultadoIv).setVisibility(View.VISIBLE);
                    findViewById(R.id.resultado2Iv).setVisibility(View.GONE);
                }
            }
        }
    }

    public void onClick(View view) throws InterruptedException {
        if(view == activityMainBinding.jogarBt){
            numFaces = Integer.parseInt(configuracoes.getNumFaces());
            if(!configuracoes.getDoisDados()) {
                resultado = new Random(System.currentTimeMillis()).nextInt(numFaces) + 1;
                activityMainBinding.resultadoTv.setText(resultado.toString());

                String resultadoImagem1 = "dice_" + resultado;
                activityMainBinding.resultadoIv.setImageResource(
                        getResources().getIdentifier(resultadoImagem1, "drawable", getPackageName())
                );
            } else {
                resultado = new Random(System.currentTimeMillis()).nextInt(numFaces) + 1;
                Thread.sleep(103);
                resultado2 = new Random(System.currentTimeMillis()).nextInt(numFaces) + 1;

                activityMainBinding.resultadoTv.setText(resultado.toString() + "   " + resultado2.toString());

                String resultadoImagem1 = "dice_" + resultado;
                String resultadoImagem2 = "dice_" + resultado2;

                activityMainBinding.resultadoIv.setImageResource(
                        getResources().getIdentifier(resultadoImagem1, "drawable", getPackageName())
                );

                activityMainBinding.resultado2Iv.setImageResource(
                        getResources().getIdentifier(resultadoImagem2, "drawable", getPackageName())
                );

            }
        }
    }


}
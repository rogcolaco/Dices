package br.edu.ifsp.scl.ads.s5.pdm.dices

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_configuracoes.*

class ConfiguracoesActivity: AppCompatActivity() {

    private lateinit var configuracoes: Configuracoes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracoes)

        supportActionBar?.title = "Configurações"

        configuracoes = intent.getParcelableExtra(MainActivity.EXTRA_CONFIGURACOES) ?: Configuracoes(false, 6)
        qtdDadosRg.check(if(configuracoes.doisDados) R.id.doisDadosRb else R.id.umDadoRb)
        var numFacesEdt :String = facesEdt.text.toString()
        if (!numFacesEdt.equals(""))
            configuracoes.numFaces = numFacesEdt as Int
    }

    fun onClick(view: View){
        if (view.id == R.id.salvarBtn){
            configuracoes.doisDados = doisDadosRb.isChecked
            var numFacesEdt :String = facesEdt.text.toString()
            if (numFacesEdt != "")
                configuracoes.numFaces = numFacesEdt as Int

            var resultIntent = Intent()
            resultIntent.putExtra(MainActivity.EXTRA_CONFIGURACOES, configuracoes)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

    }


}
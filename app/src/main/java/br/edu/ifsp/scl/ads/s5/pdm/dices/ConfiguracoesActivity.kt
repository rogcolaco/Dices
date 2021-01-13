package br.edu.ifsp.scl.ads.s5.pdm.dices

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_configuracoes.*

class ConfiguracoesActivity: AppCompatActivity() {

    private lateinit var configuracoes: Configuracoes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracoes)

        supportActionBar?.title = "Configurações"

        configuracoes = intent.getParcelableExtra(MainActivity.EXTRA_CONFIGURACOES) ?: Configuracoes(false, "6")
        qtdDadosRg.check(if(configuracoes.doisDados) R.id.doisDadosRb else R.id.umDadoRb)
        facesEdt.setText(configuracoes.numFaces)
        val numFacesEdt :String = facesEdt.text.toString()
        if (numFacesEdt.isNotEmpty() || numFacesEdt.isNotBlank())
            configuracoes.numFaces = numFacesEdt
    }

    fun onClick(view: View){
        if (view.id == R.id.salvarBtn){
            configuracoes.doisDados = doisDadosRb.isChecked
            val numFacesEdt :String = facesEdt.text.toString()
            if (numFacesEdt.isNotEmpty() || numFacesEdt.isNotBlank())
                configuracoes.numFaces = numFacesEdt
            Toast.makeText(this,"Numero de faces: $numFacesEdt", Toast.LENGTH_SHORT).show()

            val resultIntent = Intent()
            resultIntent.putExtra(MainActivity.EXTRA_CONFIGURACOES, configuracoes)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

    }


}
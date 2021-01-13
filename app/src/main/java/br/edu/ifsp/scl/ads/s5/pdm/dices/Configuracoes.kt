package br.edu.ifsp.scl.ads.s5.pdm.dices

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Configuracoes (var doisDados: Boolean, var numFaces: String): Parcelable
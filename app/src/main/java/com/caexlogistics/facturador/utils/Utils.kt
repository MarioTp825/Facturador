package com.caexlogistics.facturador.utils

import android.content.Context
import android.text.Selection.setSelection
import android.widget.EditText
import android.widget.Spinner
import android.widget.SpinnerAdapter
import com.caexlogistics.facturador.dto.MyObjectBox
import io.objectbox.BoxStore

var boxStore:BoxStore? = null

fun getStore(context: Context):BoxStore {
    return if(boxStore == null) {
        boxStore = MyObjectBox.builder().androidContext(context).build()
        boxStore!!
    } else boxStore!!
}

fun EditText.isNotEmpty():Boolean {
    val validate = this.text.isNotBlank()
    this.error = if (validate) null else "Campo obligatorio"
    return validate
}

fun EditText.isNit():Boolean {
    if (this.text.isBlank()) return false
    val value = text.toString()
    for(txt in value) {
        if(!txt.isDigit() && txt.uppercase() != "K") {
            this.error = "Formato inválido"
            return false
        }
    }
    this.error = null
    return true
}

fun EditText.rangeValue(min: Int, max: Int):Boolean {
    if (this.text.isBlank()) return false
    val value = text.toString().toInt()
    val validate = value in min..max
    this.error = if (validate) null else "Valor entre ($min - $max)"
    return validate
}

fun EditText.floatRangeValue(min: Float, max: Float):Boolean {
    if (this.text.isBlank()) return false
    val value = text.toString().toFloat()
    val validate = value in min..max
    this.error = if (validate) null else "Valor entre ($min - $max)"
    return validate
}

fun EditText.clear() {
    this.text.clear()
}

fun Spinner.clear() {
    this.setSelection(0)
}
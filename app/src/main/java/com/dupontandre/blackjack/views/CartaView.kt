package com.dupontandre.blackjack.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.dupontandre.blackjack.R

class CartaView : View {

    // Propiedades de la carta
    var numero : Int = (1..13).random()
    var bocaArriba : Boolean = false

    // Lienzo y pincel
    var paint : Paint? = null
    var naipe : Bitmap? = null

    // Constructor de CartaView
    constructor(context : Context, attrs : AttributeSet) : super(context, attrs){
        paint = Paint()
        naipe = BitmapFactory.decodeResource(resources, R.drawable.naipe)
    }

    // Dibujar
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawNaipe(canvas)
    }

    // Pintar naipe
    private fun drawNaipe(canvas: Canvas?){
        canvas?.drawBitmap(naipe!!, null, Rect(0, 0, width, height), paint!!)
    }

    // Actualizar naipe
    fun actualizarNaipe(){
        if(bocaArriba){
            when(this.numero){
                1-> naipe = BitmapFactory.decodeResource(resources, R.drawable.carta1)
                2-> naipe = BitmapFactory.decodeResource(resources, R.drawable.carta2)
                3-> naipe = BitmapFactory.decodeResource(resources, R.drawable.carta3)
                4-> naipe = BitmapFactory.decodeResource(resources, R.drawable.carta4)
                5-> naipe = BitmapFactory.decodeResource(resources, R.drawable.carta5)
                6-> naipe = BitmapFactory.decodeResource(resources, R.drawable.carta6)
                7-> naipe = BitmapFactory.decodeResource(resources, R.drawable.carta7)
                8-> naipe = BitmapFactory.decodeResource(resources, R.drawable.carta8)
                9-> naipe = BitmapFactory.decodeResource(resources, R.drawable.carta9)
                10-> naipe = BitmapFactory.decodeResource(resources, R.drawable.carta10)
                11-> naipe = BitmapFactory.decodeResource(resources, R.drawable.carta11)
                12-> naipe = BitmapFactory.decodeResource(resources, R.drawable.carta12)
                13-> naipe = BitmapFactory.decodeResource(resources, R.drawable.carta13)
            }
        } else{
            naipe = BitmapFactory.decodeResource(resources, R.drawable.naipe)
        }

        // Forzar el reinicio del View
        this.invalidate()
    }
}
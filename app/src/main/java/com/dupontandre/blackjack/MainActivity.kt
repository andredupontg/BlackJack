package com.dupontandre.blackjack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.dupontandre.blackjack.views.CartaView

// TODO: Integrantes -> Dupont Góngora, André & Fournier Claros, Willy
// TODO: Codigos -> 20161997 & 20180729


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Variables globales
        var ganador : Boolean = false
        var contadorMesa : Int = 0
        var contadorJugador : Int = 0
        var consola = findViewById<TextView>(R.id.tviResultados)
        var butQuedar = findViewById<Button>(R.id.butQuedar)
        var teQuedas : Boolean = false

        // Listener de las cartas a las cartas
        var mesaCarta1 : CartaView = findViewById(R.id.cviMesaCarta1)
        var mesaCarta2 : CartaView = findViewById(R.id.cviMesaCarta2)
        var mesaCarta3 : CartaView = findViewById(R.id.cviMesaCarta3)
        var mesaCarta4 : CartaView = findViewById(R.id.cviMesaCarta4)

        var jugadorCarta1 : CartaView = findViewById(R.id.cviJugadorCarta1)
        var jugadorCarta2 : CartaView = findViewById(R.id.cviJugadorCarta2)
        var jugadorCarta3 : CartaView = findViewById(R.id.cviJugadorCarta3)
        var jugadorCarta4 : CartaView = findViewById(R.id.cviJugadorCarta4)

        // Asignamos como valor maximo de carta 10
        fun sumarCarta(carta : CartaView) : Int{
            if(carta.bocaArriba == true){
                if(carta.numero >= 10){
                    return 10
                } else{
                    return carta.numero
                }
            } else{
                return 0
            }
        }

        // Comprobar tras cada jugada si hay un ganador
        fun comprobarGanador(){
            contadorMesa = 0
            contadorMesa += sumarCarta(mesaCarta1)
            contadorMesa += sumarCarta(mesaCarta2)
            contadorMesa += sumarCarta(mesaCarta3)
            contadorMesa += sumarCarta(mesaCarta4)

            contadorJugador = 0
            contadorJugador += sumarCarta(jugadorCarta1)
            contadorJugador += sumarCarta(jugadorCarta2)
            contadorJugador += sumarCarta(jugadorCarta3)
            contadorJugador += sumarCarta(jugadorCarta4)

            if(contadorMesa >= 21 && contadorJugador >= 21){
                consola.setText("Empate")
                ganador = true
            } else if(contadorMesa > 21){
                consola.setText("Mesa Perdio")
                ganador = true
            } else if(contadorJugador > 21){
                consola.setText("Jugador Perdio")
                ganador = true
            } else if(contadorMesa == 21){
                consola.setText("Jugador Perdio")
                ganador = true
            } else if(contadorJugador == 21){
                consola.setText("Mesa Perdio")
                ganador = true
            } else if(teQuedas){
                if(contadorMesa > contadorJugador){
                    consola.setText("Jugador Perdio")
                    ganador = true
                } else if(contadorJugador > contadorMesa){
                    consola.setText("Mesa Perdio")
                    ganador = true
                } else{
                    consola.setText("Empate")
                    ganador = true
                }
            }
        }

        // Opcion quedarse en partida
        fun quedar(){
            if(!teQuedas){
                teQuedas = true
                comprobarGanador()
            }
        }
        butQuedar.setOnClickListener { quedar() }

        // Animacion de desvelar el naipe
        fun voltear(naipe : CartaView){
            if(ganador == false){
                if(naipe == jugadorCarta3){
                    voltear(mesaCarta3)
                } else if(naipe == jugadorCarta4){
                    voltear(mesaCarta4)
                }
                naipe.bocaArriba = true
                naipe.actualizarNaipe()
                comprobarGanador()
            }
        }

        jugadorCarta3.setOnClickListener{voltear(jugadorCarta3)}
        jugadorCarta4.setOnClickListener{voltear(jugadorCarta4)}

        // Se necesita mostrar 4 cartas al inicio
        fun mostrarInicio(){
            mesaCarta1.bocaArriba = true
            mesaCarta2.bocaArriba = true
            jugadorCarta1.bocaArriba = true
            jugadorCarta2.bocaArriba = true
            mesaCarta1.actualizarNaipe()
            mesaCarta2.actualizarNaipe()
            jugadorCarta1.actualizarNaipe()
            jugadorCarta2.actualizarNaipe()
        }

        // Funcionalidad de los botones
        var butReiniciar = findViewById<Button>(R.id.butReiniciar)

        fun reiniciarBlackJack(){
            // Seteamos las cartas boca abajo
            mesaCarta1.bocaArriba = false
            mesaCarta2.bocaArriba = false
            mesaCarta3.bocaArriba = false
            mesaCarta4.bocaArriba = false

            jugadorCarta1.bocaArriba = false
            jugadorCarta2.bocaArriba = false
            jugadorCarta3.bocaArriba = false
            jugadorCarta4.bocaArriba = false

            // Actualizamos los numeros de las cartas
            mesaCarta1.numero = (1..13).random()
            mesaCarta2.numero = (1..13).random()
            mesaCarta3.numero = (1..13).random()
            mesaCarta4.numero = (1..13).random()

            jugadorCarta1.numero = (1..13).random()
            jugadorCarta2.numero = (1..13).random()
            jugadorCarta3.numero = (1..13).random()
            jugadorCarta4.numero = (1..13).random()

            // Actualizamos las cartas
            jugadorCarta1.actualizarNaipe()
            jugadorCarta2.actualizarNaipe()
            jugadorCarta3.actualizarNaipe()
            jugadorCarta4.actualizarNaipe()

            mesaCarta1.actualizarNaipe()
            mesaCarta2.actualizarNaipe()
            mesaCarta3.actualizarNaipe()
            mesaCarta4.actualizarNaipe()

            consola.setText("Resultado")
            Handler().postDelayed({
                mostrarInicio()
            }, 2000)
            ganador = false
            teQuedas = false
        }
        butReiniciar.setOnClickListener {reiniciarBlackJack()}
        mostrarInicio()
        comprobarGanador()
    }
}
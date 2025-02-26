package fr.iutlan.tp2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log
import android.view.View
import fr.iutlan.tp2.databinding.ActivityMainBinding
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    private val TAG = "TP2"

    lateinit var ui: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.i(TAG, "Mon premier message de log !")
    }

    fun racine2(N: Double): Double {
        var n = N / 2
        for (i in 1..50) {
            val m = N / n       // donc la surface du rectangle (n, m) est N = n * m
            if (abs(n - m) < 1e-12) break
            n = (n + m) / 2     // n = moyenne entre n et m
        }
        return n
    }

    fun testRacine2() {
        var N = 2.0
        while (N < 50.0) {
            val n = racine2(N)
            Log.i(TAG, "racine($N) = $n")
            N += 7.4
        }
    }

    fun onCalculer(view: View){
        try {
            val txtNombre = ui.nombre.text.toString()

            val nombre = txtNombre.toDouble()

            val racine = racine2(nombre)

            ui.resultat.text = racine.toString()

        }catch (e:Exception){
            Log.e(TAG,e.toString())
        }
    }

}
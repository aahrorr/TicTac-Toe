package com.example.tictactoe

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {

    lateinit var txt1: TextView
    lateinit var txt2: TextView
    lateinit var txt3: TextView
    lateinit var txt4: TextView
    lateinit var txt5: TextView
    lateinit var txt6: TextView
    lateinit var txt7: TextView
    lateinit var txt8: TextView
    lateinit var txt9: TextView
    lateinit var shownavbat: TextView
    var navbat: Boolean = true
    val belgimiz = Array(3) { CharArray(3) { '*' } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        initViews()
        txt1.setOnClickListener {
            clickButton(it as TextView, 1)
        }
        txt2.setOnClickListener {
            clickButton(it as TextView, 2)
        }
        txt3.setOnClickListener {
            clickButton(it as TextView, 3)
        }
        txt4.setOnClickListener {
            clickButton(it as TextView, 4)
        }
        txt5.setOnClickListener {
            clickButton(it as TextView, 5)
        }
        txt6.setOnClickListener {
            clickButton(it as TextView, 6)
        }
        txt7.setOnClickListener {
            clickButton(it as TextView, 7)
        }
        txt8.setOnClickListener {
            clickButton(it as TextView, 8)
        }
        txt9.setOnClickListener {
            clickButton(it as TextView, 9)
        }
    }

    private fun initViews() {
        txt1 = findViewById(R.id.textView1)
        txt2 = findViewById(R.id.textView2)
        txt3 = findViewById(R.id.textView3)
        txt4 = findViewById(R.id.textView4)
        txt5 = findViewById(R.id.textView5)
        txt6 = findViewById(R.id.textView6)
        txt7 = findViewById(R.id.textView7)
        txt8 = findViewById(R.id.textView8)
        txt9 = findViewById(R.id.textView9)
        shownavbat = findViewById(R.id.shownavbat)
    }

    private fun clickButton(textView: TextView, numbers: Int) {

        if (textView.text.isNotBlank())
            return


        val column: Int
        column = (numbers - 1) % 3
        val row: Int = (numbers - 1) / 3


        if (navbat) {
            textView.text = "X"
            belgimiz[row][column] = 'X'
            check()
            navbat =! navbat

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                textView.setTextColor(Color.parseColor("#FF0000"))
            }
        } else {
            textView.text = "O"
            belgimiz[row][column] = 'O'
            check()
            navbat =! navbat

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                textView.setTextColor(Color.parseColor("#FF000000"))
            }
        }
        shownavbat.text = if (navbat) "X ning navbati" else "0 ning navbati"



    }

    private fun check() {
        if ((belgimiz[0][0] == 'X' && belgimiz[0][1] == 'X' && belgimiz[0][2] == 'X') ||
            belgimiz[1][0] == 'X' && belgimiz[1][1] == 'X' && belgimiz[1][2] == 'X' ||
            belgimiz[2][0] == 'X' && belgimiz[2][1] == 'X' && belgimiz[2][2] == 'X' ||
            belgimiz[0][0] == 'X' && belgimiz[1][0] == 'X' && belgimiz[2][0] == 'X' ||
            belgimiz[0][1] == 'X' && belgimiz[1][1] == 'X' && belgimiz[2][1] == 'X' ||
            belgimiz[0][2] == 'X' && belgimiz[1][2] == 'X' && belgimiz[2][2] == 'X' ||
            belgimiz[0][0] == 'X' && belgimiz[1][1] == 'X' && belgimiz[2][2] == 'X' ||
            belgimiz[0][2] == 'X' && belgimiz[1][1] == 'X' && belgimiz[2][0] == 'X'

        ) {
            showDialog("X g'alaba qozondi!")

        } else if ((belgimiz[0][0] == 'O' && belgimiz[0][1] == 'O' && belgimiz[0][2] == 'O') ||
            belgimiz[1][0] == 'O' && belgimiz[1][1] == 'O' && belgimiz[1][2] == 'O' ||
            belgimiz[2][0] == 'O' && belgimiz[2][1] == 'O' && belgimiz[2][2] == 'O' ||
            belgimiz[0][0] == 'O' && belgimiz[1][0] == 'O' && belgimiz[2][0] == 'O' ||
            belgimiz[0][1] == 'O' && belgimiz[1][1] == 'O' && belgimiz[2][1] == 'O' ||
            belgimiz[0][2] == 'O' && belgimiz[1][2] == 'O' && belgimiz[2][2] == 'O' ||
            belgimiz[0][0] == 'O' && belgimiz[1][1] == 'O' && belgimiz[2][2] == 'O' ||
            belgimiz[0][2] == 'O' && belgimiz[1][1] == 'O' && belgimiz[2][0] == 'O'
        ) {
            showDialog("0 g'alaba qozondi!")
        } else {
            var k: Int
            k = 0
            for (i in 0..2) {
                for (j in 0..2) {
                    if (belgimiz[i][j] == '*') {
                        break
                    } else {
                        k++
                    }
                }
            }
            if (k == 9)
                showDialog("Durang")
        }

    }

    private fun showDialog(txt: String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.win_dialog)
        dialog.setCancelable(false)
        val window: Window? = dialog.getWindow()
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val txtWin: TextView
        txtWin = dialog.findViewById(R.id.txtwin)
        txtWin.text = txt
        val homebtn = dialog.findViewById<Button>(R.id.homebtn)
        homebtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        dialog.show()
    }
}


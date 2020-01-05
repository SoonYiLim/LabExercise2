package com.example.labexercise2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.buttonCalculate).setOnClickListener{
            calculator(it)
        }

        findViewById<Button>(R.id.buttonReset).setOnClickListener{
            clear(it)
        }
    }

    private fun calculator(view:View){
        val kg = findViewById<EditText>(R.id.editTextWeight)
        val tall = findViewById<EditText>(R.id.editTextHeight)

        when{
            kg.text.isBlank() -> kg.setText(R.string.input_error)
            tall.text.isBlank() -> tall.setText(R.string.input_error)

            else ->{
                val total = calBMI(kg.text.toString().toDouble(),tall.text.toString().toDouble())
                when{
                    total < 18.5 ->{
                        imageViewProfile.setImageResource(R.drawable.under)
                        textViewBMI.setText(textViewBMI.text.toString().plus(total.toString()))
                    }
                    total >=18.5 && total <= 24.9->{
                        imageViewProfile.setImageResource(R.drawable.normal)
                        textViewBMI.setText(textViewBMI.text.toString().plus(total.toString()))

                    }
                    total >24.9 ->{
                        imageViewProfile.setImageResource(R.drawable.over)
                        textViewBMI.setText(textViewBMI.text.toString().plus(total.toString()))

                    }
                }
            }
        }
    }

    private fun clear(view: View){
        editTextWeight.setText("")
        editTextHeight.setText("")
        imageViewProfile.setImageResource(R.drawable.empty)
        textViewBMI.setText(R.string.bmi)
    }

    private fun calBMI(a:Double, b:Double): Double{
        return a / ((b/100) * (b/100))
    }
}

package com.marksargentteachingexample.randomnumbergenerator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var lowerVal:EditText
    lateinit var upperVal:EditText
    lateinit var getVal: Button
    lateinit var display: TextView
    lateinit var checkBox: CheckBox
    lateinit var numlist: ArrayList<Int>
    lateinit var getNumber: () -> Int
    var justchecked = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lowerVal = findViewById(R.id.lower)
        upperVal = findViewById(R.id.upper)
        getVal = findViewById(R.id.get_random)
        display = findViewById(R.id.display)
        checkBox = findViewById(R.id.checkBox)
        getVal.setOnClickListener { displayNumber(it) }

        checkBox.setOnCheckedChangeListener{buttonView, isChecked->
            if(isChecked){
                justchecked = true
            }

            lowerVal.getText().clear()
            upperVal.getText().clear()

        }

    }

    private fun displayNumber(view: View) {
        if(lowerVal.getText().toString() == "" ||  upperVal.getText().toString() == "")
            return

        val lower = Integer.parseInt(lowerVal.getText().toString())
        val upper = Integer.parseInt(upperVal.getText().toString())


        if (checkBox.isChecked){
            if(justchecked){
                initRandomList(lower, upper)
                justchecked = false
            }
            if(!numlist.isEmpty()) {
                display.text = numlist.last().toString()
                numlist.removeAt(numlist.lastIndex)
            }else{
                display.text = "no more numbers"
            }

            display.visibility = View.VISIBLE

        }else{
            display.text = generateRandom(lower, upper).toString()
            display.visibility = View.VISIBLE
        }

    }

    fun generateRandom(lower:Int, upper: Int):Int{
        return (lower..upper).shuffled().first()
    }

    fun initRandomList(lower:Int, upper: Int){
        numlist = ArrayList<Int>((lower..upper).shuffled())
    }

}

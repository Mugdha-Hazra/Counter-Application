package com.example.counter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.CheckedTextView
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.IdRes
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //use lateinit to initilize the values to be used throughout the class.
    //will define them in oncreate.
    private lateinit var numberText: TextView
    private lateinit var numberInput: EditText
    private lateinit var interval: EditText
    private lateinit var summary: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get a reference to our button.
        val submitButton: Button=findViewById(R.id.main_activity_bt_submit)
        val randomButton: Button=findViewById(R.id.main_activity_bt_random_number)
        val incrementButton: Button=findViewById(R.id.main_activity_bt_increment)
        val decrementButton: Button=findViewById(R.id.main_activity_bt_decrement)

        //Set clickListeners for each Button.
        submitButton.setOnClickListener{ submitNumber()}
        randomButton.setOnClickListener { generateRandomNumber() }
        incrementButton.setOnClickListener { changeNumber("+") }
        decrementButton.setOnClickListener { changeNumber("-") }

        //set the values to our views initilised with lateint
        numberText=findViewById(R.id.main_activity_tv_number)
        numberInput=findViewById(R.id.main_activity_et_number_input)
        interval=findViewById(R.id.main_activity_et_interval)
        summary=findViewById(R.id.main_activity_tv_summary)
    }

    //Get a number from edit text and display to the screen.
    private fun submitNumber(){
    //get the number the editText user wants to submit
        var startingNumber=numberInput.text.toString()
        //check to see if the user left the edit text blank,
        // if so default to a value of 10.
        if(startingNumber==""){
            startingNumber="10"
        }
        //set our startingNumber in the text view
        numberText.text=startingNumber
        //clear the edittext after submitting the number
        numberInput.setText("")
        //hide the keyboard
        hideKeyboard()
    }
    //generate a random number and display it to the screen.
    private fun generateRandomNumber(){
//generate random number form -100 to 100
        val randomNumber=(-100..100).random()
        numberText.text=randomNumber.toString()

    }

    //Increment or decrement a number by a given value
    private fun changeNumber(operation:String){
        //Get the current number and the increment number
        val currentNumber=numberText.text.toString().toInt()
        var incrementValue=interval.text.toString()

        //ckeck to see if the increment value is blank, if so default to 1.
        if(incrementValue==""){
            incrementValue="1"
        }

        //either or decrement based on the value of operation
        if(operation=="+")
        {
            //Determine new number to display and display it
            val newNumber=currentNumber+incrementValue.toInt()
            numberText.text=newNumber.toString()

            //update the summary message
            summary.text="$currentNumber + $incrementValue = $newNumber"
        }
        else
        {
            //Determine new number to display and display it
            val newNumber=currentNumber - incrementValue.toInt()
            numberText.text=newNumber.toString()

            //update the summary message
            summary.text="$currentNumber - $incrementValue = $newNumber"
        }

        //hide the keyboard
        hideKeyboard()
    }
    //hide the keyboard
    private fun hideKeyboard()
    {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(numberText.windowToken,0)
    }

}
package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.udojava.evalex.Expression
import java.math.RoundingMode
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    private lateinit var button_1: Button
    private lateinit var button_2: Button
    private lateinit var button_3: Button
    private lateinit var button_4: Button
    private lateinit var button_5: Button
    private lateinit var button_6: Button
    private lateinit var button_7: Button
    private lateinit var button_8: Button
    private lateinit var button_9: Button
    private lateinit var button_0: Button
    private lateinit var button_mult: Button
    private lateinit var button_divs: Button
    private lateinit var button_plus: Button
    private lateinit var button_minus: Button
    private lateinit var button_proc: Button
    private lateinit var button_result: Button
    private lateinit var button_crop: Button
    private lateinit var button_delete: Button
    private lateinit var button_AC: Button
    private lateinit var button_plus_minus: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.field)
        button_1 = findViewById(R.id.buttom_1)
        button_2 = findViewById<Button>(R.id.buttom_2)
        button_3 = findViewById<Button>(R.id.buttom_3)
        button_4 = findViewById<Button>(R.id.buttom_4)
        button_5 = findViewById<Button>(R.id.buttom_5)
        button_6 = findViewById<Button>(R.id.buttom_6)
        button_7 = findViewById<Button>(R.id.buttom_7)
        button_8 = findViewById<Button>(R.id.buttom_8)
        button_9 = findViewById<Button>(R.id.buttom_9)
        button_0 = findViewById<Button>(R.id.buttom_null)
        button_mult = findViewById<Button>(R.id.buttom_multiplication)
        button_divs = findViewById<Button>(R.id.buttom_division)
        button_plus = findViewById<Button>(R.id.buttom_plus)
        button_minus = findViewById<Button>(R.id.buttom_minus)
        button_proc = findViewById<Button>(R.id.buttom_procent)
        button_result = findViewById<Button>(R.id.buttom_result)
        button_crop = findViewById<Button>(R.id.buttom_crop)
        button_delete = findViewById<Button>(R.id.buttom_delete)
        button_AC = findViewById<Button>(R.id.buttom_AC)
        button_plus_minus = findViewById(R.id.buttom_plus_minus)

    }

    override fun onStart() {
        super.onStart()
        buttons_click()
    }

    private fun buttons_click(){
        button_1.setOnClickListener {
            setText(it as Button)
        }
        button_2.setOnClickListener {
            setText(it as Button)
        }
        button_3.setOnClickListener {
            setText(it as Button)
        }
        button_4.setOnClickListener {
            setText(it as Button)
        }
        button_5.setOnClickListener {
            setText(it as Button)
        }
        button_6.setOnClickListener {
            setText(it as Button)
        }
        button_7.setOnClickListener {
            setText(it as Button)
        }
        button_8.setOnClickListener {
            setText(it as Button)
        }
        button_9.setOnClickListener {
            setText(it as Button)
        }
        button_0.setOnClickListener {
            setText(it as Button)
        }
        button_plus.setOnClickListener {
            if (check(textView.text.toString())){
                setText(it as Button)
            }
        }
        button_minus.setOnClickListener {
            if (check(textView.text.toString())){
                setText(it as Button)
            }
        }
        button_mult.setOnClickListener {
            if (check(textView.text.toString())){
                setText(it as Button)
            }
        }
        button_divs.setOnClickListener {
            if (check(textView.text.toString())){
                setText(it as Button)
            }
        }
        button_crop.setOnClickListener {
            if (check(textView.text.toString())){
                val text = textView.text
                textView.text = "$text${(it as Button).text}"
            }
        }
        button_AC.setOnClickListener {
            textView.text = "0"
        }
        button_proc.setOnClickListener {
            textView.text = round(textView.text.toString().toDouble()/100).toString()
        }
        button_plus_minus.setOnClickListener {
            if (textView.text[0] == '-'){
                textView.text = textView.text.removeRange(0..0)
            } else if (textView.text != "0") {
                textView.text = "-${textView.text}"
            }
        }
        button_delete.setOnClickListener {
            if (!textView.text.equals("")) {
                val string = textView.text
                val result = string.removeRange(string.lastIndex..string.lastIndex)
                Log.d("result", result.toString())
                if (result.equals("")) {
                    textView.text = "0"
                } else {
                    textView.text = result
                }
            }
        }
        button_result.setOnClickListener {
            val signs_plus_minus = textView.text.replace(Regex("[0-9]x/"),"").toCharArray().map { it.toString() }.toTypedArray()
            val numbers: List<String> = textView.text.replace(Regex("[-+]"),"\n").lines()
            val numbersCopy = numbers.map { it }.toTypedArray()

            textView.text = round(count(create(numbersCopy),signs_plus_minus))
        }
    }

    fun setText(button: Button): Unit {
        val text = textView.text
        if (text.equals("0")){
            textView.text = "${button.text.toString()}"
        } else {
            textView.text = "$text${button.text.toString()}"
        }
    }


    // Функция возвращающая число из массива строк (знаки + или -),умножение и деление происходят первее, в другой функции create()
    private fun count(numbers: MutableList<Double>, signs: Array<String>): Double {
        var result = numbers.get(0)
        var j = 0
        if (result < 0){
            j = 1
        }
        for (i in 0..numbers.size-1){
            if (i == numbers.size-1){
                break
            }
            for (k in j..signs.size-1){
                if (signs.get(k) == "+" && signs.get(k+1) != "x"){
                    signs[k] = "*"
                    result = sum(result, numbers.get(i+1))
                    break
                }
                if (signs.get(k) == "-"&& signs.get(k+1) != "x"){
                    signs[k] = "*"
                    result = minus(result,numbers.get(i+1))
                    break
                }
            }
        }
        return result
    }

    // Функция возвращающая массив вида [1,8,9,12] полученная из массива [1,4x2,27/9x3,3x4]
    private fun create(numbers: Array<String>): MutableList<Double>  {
        for (i in 0..numbers.size-1) {
            if (numbers.get(i).contains("x") || numbers.get(i).contains("/")) {
                val list: List<String> = numbers.get(i).replace(Regex("[x/]"), "\n").lines()
                val numbers2 = list.map { it.toDouble() }.toTypedArray()
                val signs = numbers.get(i).replace(Regex("[0-9]"), "").
                toCharArray().map { it.toString() }.toTypedArray()

                var result = numbers2.get(0)
                for (k in 0..numbers2.size - 1) {
                    if (k == numbers2.size - 1) {
                        break
                    }
                    for (j in 0..signs.size - 1) {
                        if (signs.get(j) == "x") {
                            signs[j] = "*"
                            result = multiplication(result, numbers2.get(k + 1))
                            break
                        }
                        if (signs.get(j) == "/") {
                            signs[j] = "*"
                            result = division(result, numbers2.get(k + 1))
                            break
                        }
                    }
                }
                numbers[i] = result.toString()
            }
        }
        var list: MutableList<Double> = mutableListOf()
        if (numbers[0] == ""){
            list.add(-numbers.get(1).toDouble())
            for (i in 1..numbers.size-2){
                list.add(numbers.get(i+1).toDouble())
            }
        } else {
            list = numbers.map { it.toDouble() }.toTypedArray().toMutableList()
        }
        return list
    }

    // Математические фунцкии
    private fun sum(a: Double, b: Double): Double = a+b
    private fun minus(a: Double, b: Double): Double = a-b
    private fun division(a: Double, b: Double): Double = a/b
    private fun multiplication(a: Double, b: Double): Double = a*b

    private fun check(str: String): Boolean {
        if (str == ""){
            return false
        }
        return str[str.length-1] != 'x' && str[str.length-1] != '/' &&
                str[str.length-1] != '+' && str[str.length-1] != '-' &&
                str[str.length-1] != '.'
    }

    private fun round(result: Double): String {
        val df = DecimalFormat("#.######")
        df.roundingMode = RoundingMode.CEILING
        return df.format(result).toString()
    }
}


package org.techtown.rotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private val addButton: Button by lazy {
        findViewById(R.id.addButton)
    }
    private val clearButton: Button by lazy {
        findViewById(R.id.clearButton)
    }
    private val runButton: Button by lazy {
        findViewById(R.id.runButton)
    }
    private val numberPicker: NumberPicker by lazy {
        findViewById(R.id.NumberPicker)
    }
    private val numberPickerViewList: List<TextView> by lazy {
        listOf<TextView> (
            findViewById(R.id.textView1),
            findViewById(R.id.textView2),
            findViewById(R.id.textView3),
            findViewById(R.id.textView4),
            findViewById(R.id.textView5),
            findViewById(R.id.textView6)
        )

    }


    private var didRun = false//숫자 생성 가능 여부 판단

    //hashset요소의 순서 저장안함
    private val numberSet = hashSetOf<Int>()//중복방지위해서 set사용


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        numberPicker.minValue = 1
        numberPicker.maxValue = 45
        initRunButton()
        initAddButton()
        initClearButton()
    }
    private fun initRunButton() {
        TODO("Not yet implemented")
        runButton.setOnClickListener {
            val list = getRandomNumber()
            didRun = true
            list.forEachIndexed {index,number->
                val textView = numberPickerViewList[index]
                textView.text = number.toString()
                textView.visibility = View.VISIBLE
                setNumberBackground(number, textView)
            }
        }
    }
    private fun initClearButton() {
        clearButton.setOnClickListener {
            numberSet.clear()
            numberPickerViewList.forEach {
                //리스트의 텍스트뷰를 하나하나 꺼내준다
                it.visibility = View.GONE

            }
            didRun = false
        }




    }

    private fun initAddButton() {
        TODO("Not yet implemented")
        addButton.setOnClickListener {
            if (didRun) {
                //숫자 자동생성 경우
                Toast.makeText(this, "초기화 후에 시도해 주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (numberSet.size >= 6) {
                //다찬경우
                Toast.makeText(this, "번호는 6개 까지만", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (numberSet.contains(numberPicker.value)) {
                Toast.makeText(this, "이미 선택한 번호입니다", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val textView = numberPickerViewList[numberSet.size]
            textView.visibility = View.VISIBLE
            textView.text = numberPicker.value.toString()
            setNumberBackground(numberPicker.value, textView)
            numberSet.add(numberPicker.value)
        }
    }

   


    private fun getRandomNumber(): Any {
        val numberList = mutableListOf<Int>().apply {

            for (i in 1..45) {
                if (numberSet.contains(i))//이미 선택된 번호는 제외 후 랜덤
                {
                    continue
                }
                this.add(i)
            }
        }
        numberList.shuffle()
        val newList = numberSet.toList() + numberList.subList(0, 6 - numberSet.size)
//sublist -> subList(fromIndex:Int, toIndex:Int):mutableList<E>
        return newList.sorted()//선택번호 오름차순 정렬

    }

    private fun setNumberBackground(i: Int, textView: TextView) {
        when (i) {
            in 1..10 -> textView.background =
                ContextCompat.getDrawable(this, R.drawable.circle_yellow)
            in 11..20 -> textView.background =
                ContextCompat.getDrawable(this, R.drawable.circle_blue)
            in 21..30 -> textView.background =
                ContextCompat.getDrawable(this, R.drawable.circle_red)
            in 31..40 -> textView.background =
                ContextCompat.getDrawable(this, R.drawable.circle_gray)
            else -> textView.background =
                ContextCompat.getDrawable(this, R.drawable.circle_green)
        }

    }


    //init함수는 매개변수 없고 반환되는 값없는 특별함수
    //생성자를 통해 인스턴스 만들어질때 호출되는 함수
    
}









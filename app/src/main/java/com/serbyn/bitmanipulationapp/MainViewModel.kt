package com.serbyn.bitmanipulationapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(0)
    val uiState = _uiState.asStateFlow()



    fun calculateDiff(firstValue: String, secondValue: String) {
        viewModelScope.launch(Dispatchers.Default) {
            val result = getBitDiffCount(firstValue.toInt(), secondValue.toInt())

            _uiState.emit(result)
        }
    }

    fun getBitDiffCount(x: Int, y: Int): Int {

        val xBits = Integer.toBinaryString(x)
        val yBits = Integer.toBinaryString(y)

        Log.e("MainViewModel", "x = $xBits")
        Log.e("MainViewModel", "y = $yBits")

        var count = 0
        var xorResult = x xor y

        println(Integer.toBinaryString(xorResult))

        while (xorResult != 0) {
            count += xorResult and 1
            xorResult = xorResult shr 1
        }
        return count
    }
}
package com.example.week1102counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ClickerViewModel: ViewModel() {
    private val _content = MutableLiveData("눌러주세요")
    val content: LiveData<String> = _content

    fun onPushed(){
        _content.value = "눌렸습니다"
    }
}
package com.example.lab3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

class UserViewModel : ViewModel() {
    // 1. LiveData со списком пользователей
    private val _users = MutableLiveData<List<String>>()
    val users: LiveData<List<String>> = _users

    init {
        // 2. Начальные данные
        _users.value = listOf("Алексей", "Мария", "Иван")
    }

    // 3. Добавление пользователя
    fun addUser(name: String) {
        _users.value = _users.value?.plus(name) ?: listOf(name)
    }

    // 4. Удаление пользователя
    fun deleteUser(position: Int) {
        _users.value = _users.value?.toMutableList()?.apply {
            removeAt(position)
        }
    }
}
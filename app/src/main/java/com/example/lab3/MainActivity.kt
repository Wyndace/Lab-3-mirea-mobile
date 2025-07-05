package com.example.lab3

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // 1. Инициализация ViewModel
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // 2. Настройка RecyclerView
        adapter = UserAdapter(emptyList()) { position ->
            viewModel.deleteUser(position) // Обработчик удаления
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // 3. Подписка на LiveData
        viewModel.users.observe(this) { userList ->
            adapter.updateList(userList) // Обновляем список
        }

        // 4. Обработчик добавления
        findViewById<Button>(R.id.btnAddUser).setOnClickListener {
            val name = "Пользователь ${Random.nextInt(100)}"
            viewModel.addUser(name)
        }
    }
}








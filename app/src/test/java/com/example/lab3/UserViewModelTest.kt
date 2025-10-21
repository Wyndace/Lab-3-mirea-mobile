package com.example.lab3

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class UserViewModelTest {

    // Делает LiveData синхронной в тестах
    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    // Подменяет Dispatchers.Main на тестовый диспетчер (инфраструктура для корутин)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `initial users list is prefilled`() {
        // given
        val vm = UserViewModel()

        // when
        val value = vm.users.getOrAwaitValue()

        // then
        assertEquals(listOf("Алексей", "Мария", "Иван"), value)
    }

    @Test
    fun `addUser appends new item and notifies LiveData`() {
        // given
        val vm = UserViewModel()

        // when
        vm.addUser("Саня")
        val value = vm.users.getOrAwaitValue()

        // then
        assertEquals(listOf("Алексей", "Мария", "Иван", "Саня"), value)
    }

    @Test
    fun `deleteUser removes item by index and notifies LiveData`() {
        // given
        val vm = UserViewModel()

        // when
        vm.deleteUser(1) // удаляем "Мария"
        val value = vm.users.getOrAwaitValue()

        // then
        assertEquals(listOf("Алексей", "Иван"), value)
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun `deleteUser with invalid index throws`() {
        // given
        val vm = UserViewModel()

        // when
        vm.deleteUser(100) // некорректный индекс — ожидаем исключение
    }
}

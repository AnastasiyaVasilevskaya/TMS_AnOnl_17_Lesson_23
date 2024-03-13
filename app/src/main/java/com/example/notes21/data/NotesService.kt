package com.example.notes21.data

import java.time.LocalDate

typealias ItemsListener = (items: List<ListItem>) -> Unit

class NotesService {

    private var listeners = mutableSetOf<ItemsListener>()

    private var items = mutableListOf<ListItem>(
        Group("Покупки1"),
        Note("Евроопт", "Купить 5 кг бананов", LocalDate.parse("2024-03-05")),
        Note("Рынок", "Купить 15 тюльпанов", LocalDate.parse("2024-03-05")),
        Group("Покупки2"),
        Note("Евроопт", "Купить 5 кг бананов", LocalDate.parse("2024-03-05")),
        Note("Рынок", "Купить 15 тюльпанов", LocalDate.parse("2024-03-05")),
        Group("Покупки3"),
        Note("Евроопт", "Купить 5 кг бананов", LocalDate.parse("2024-03-05")),
        Note("Рынок", "Купить 15 тюльпанов", LocalDate.parse("2024-03-05")),
        Group("Покупки4"),
        Note("Евроопт", "Купить 5 кг бананов", LocalDate.parse("2024-03-05")),
        Note("Рынок", "Купить 15 тюльпанов", LocalDate.parse("2024-03-05"))
    )

    fun getItems(): List<ListItem> {
        return items
    }

    fun deleteItem(position: Int) {
        if (position < 0 || position >= items.size) {
            return
        }
        items = ArrayList(items)
        items.removeAt(position)
        notifyChanges()
    }

    fun addItem(item: ListItem) {
        items.add(item)
        notifyChanges()
    }

    fun addListener(listener: ItemsListener) {
        listeners.add(listener)
        listener.invoke(items)

    }

    fun removeListener(listener: ItemsListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(items) }
    }


}

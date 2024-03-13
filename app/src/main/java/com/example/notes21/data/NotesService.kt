package com.example.notes21.data

import java.time.LocalDate

typealias ItemsListener = (items: List<ListItem>) -> Unit

class NotesService {

    private var listeners = mutableSetOf<ItemsListener>()

    private var items = mutableListOf<ListItem>(
        Group("Покупки"),
        Note("Евроопт", "Купить 5 кг бананов", LocalDate.parse("2024-03-05")),
        Note("Рынок", "Купить 15 тюльпанов", LocalDate.parse("2024-03-05"))
    )

    fun getItems(): List<ListItem> {
        return items
    }

    fun getItem(position: Int): ListItem? {
        if (position in 0 until items.size) {
            return items[position]
        }
        return null
    }

    fun deleteItem(item: ListItem) {  //надо покруче метод
        items.remove(item)
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

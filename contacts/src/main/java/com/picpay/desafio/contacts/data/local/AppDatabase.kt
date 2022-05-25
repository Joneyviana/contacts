package com.picpay.desafio.contacts.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ContactRoomModel::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun contactDao(): ContactDao
}
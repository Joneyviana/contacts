package com.picpay.desafio.contacts.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContactDao {

    @Query("SELECT * FROM contact")
    suspend fun getContacts() : List<ContactRoomModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(contacts: List<ContactRoomModel>)
}
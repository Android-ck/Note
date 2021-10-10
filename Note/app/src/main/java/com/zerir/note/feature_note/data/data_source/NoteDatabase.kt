package com.zerir.note.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zerir.note.feature_note.domain.model.Note

@Database(
    entities = [Note::class],
    version = 2
)
abstract class NoteDatabase : RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "noted_db"
    }

}
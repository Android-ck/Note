package com.zerir.note.feature_note.domain.usa_case

import com.zerir.note.feature_note.domain.model.InvalidNoteException
import com.zerir.note.feature_note.domain.model.Note
import com.zerir.note.feature_note.domain.repository.NoteRepository
import kotlin.jvm.Throws

class InsertNoteUseCase(
    private val repo: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if(note.title.isNotBlank()) throw InvalidNoteException("Empty Title Exception")
        if(note.content.isNotBlank()) throw InvalidNoteException("Empty Content Exception")
        repo.insertNote(note)
    }

}
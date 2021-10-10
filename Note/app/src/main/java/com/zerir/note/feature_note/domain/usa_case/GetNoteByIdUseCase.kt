package com.zerir.note.feature_note.domain.usa_case

import com.zerir.note.feature_note.domain.model.Note
import com.zerir.note.feature_note.domain.repository.NoteRepository

class GetNoteByIdUseCase(
    private val repo: NoteRepository
) {

    suspend operator fun invoke(id: Int): Note? = repo.getNoteById(id)

}
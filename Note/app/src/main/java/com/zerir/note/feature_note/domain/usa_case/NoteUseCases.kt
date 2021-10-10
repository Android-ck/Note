package com.zerir.note.feature_note.domain.usa_case

import javax.inject.Inject

data class NoteUseCases @Inject constructor(
    val getNotesUseCase: GetNotesUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val insertNoteUseCase: InsertNoteUseCase,
    val getNoteByIdUseCase: GetNoteByIdUseCase,
)
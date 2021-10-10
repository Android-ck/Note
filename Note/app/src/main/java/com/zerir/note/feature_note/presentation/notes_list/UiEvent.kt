package com.zerir.note.feature_note.presentation.notes_list

import com.zerir.note.feature_note.domain.model.Note
import com.zerir.note.feature_note.domain.utils.NoteOrder

sealed class UiEvent {
    data class Order(val noteOrder: NoteOrder) : UiEvent()
    data class DeleteNote(val note: Note) : UiEvent()
    object ToggleSortingSection : UiEvent()
    object RestoreNote : UiEvent()
}

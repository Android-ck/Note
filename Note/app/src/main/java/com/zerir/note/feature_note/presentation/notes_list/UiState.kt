package com.zerir.note.feature_note.presentation.notes_list

import com.zerir.note.feature_note.domain.model.Note
import com.zerir.note.feature_note.domain.utils.NoteOrder
import com.zerir.note.feature_note.domain.utils.OrderType

data class UiState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isSortingSectionVisible: Boolean = false,
)

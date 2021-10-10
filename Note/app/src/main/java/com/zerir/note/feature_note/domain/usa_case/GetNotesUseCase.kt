package com.zerir.note.feature_note.domain.usa_case

import com.zerir.note.feature_note.domain.model.Note
import com.zerir.note.feature_note.domain.repository.NoteRepository
import com.zerir.note.feature_note.domain.utils.NoteOrder
import com.zerir.note.feature_note.domain.utils.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(
    private val repo: NoteRepository
) {

    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ): Flow<List<Note>> = repo.getNotes().map { notes ->
        when (noteOrder.orderType) {
            is OrderType.Descending -> {
                when (noteOrder) {
                    is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                    is NoteOrder.Date -> notes.sortedBy { it.timestamp }
                    is NoteOrder.Color -> notes.sortedBy { it.color }
                }
            }
            is OrderType.Ascending -> {
                when (noteOrder) {
                    is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                    is NoteOrder.Date -> notes.sortedByDescending { it.timestamp }
                    is NoteOrder.Color -> notes.sortedByDescending { it.color }
                }
            }
        }
    }

}
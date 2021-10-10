package com.zerir.note.feature_note.presentation.notes_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zerir.note.feature_note.domain.model.Note
import com.zerir.note.feature_note.domain.usa_case.NoteUseCases
import com.zerir.note.feature_note.domain.utils.NoteOrder
import com.zerir.note.feature_note.domain.utils.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesListViewModel @Inject constructor(
    private val useCases: NoteUseCases
) : ViewModel() {

    private var getNotesJob: Job? = null

    private val _state = mutableStateOf(UiState())
    val state: State<UiState> = _state

    private var recentlyDeletedNote: Note? = null

    init {
        getNotes()
    }

    fun toggleEvent(event: UiEvent) {
        when (event) {
            is UiEvent.Order -> {
                if(event.noteOrder::class == state.value.noteOrder::class &&
                    event.noteOrder.orderType == state.value.noteOrder.orderType) return
                getNotes(event.noteOrder)
            }
            is UiEvent.DeleteNote -> {
                viewModelScope.launch {
                    useCases.deleteNoteUseCase(event.note)
                    recentlyDeletedNote = event.note
                }
            }
            is UiEvent.RestoreNote -> {
                viewModelScope.launch {
                    useCases.insertNoteUseCase(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }
            is UiEvent.ToggleSortingSection -> {
                _state.value = state.value.copy(
                    isSortingSectionVisible = !state.value.isSortingSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)) {
        getNotesJob?.cancel()
        getNotesJob = useCases.getNotesUseCase().onEach { notes ->
            _state.value = state.value.copy(
                notes = notes,
                noteOrder = noteOrder,

            )
        }.launchIn(viewModelScope)
    }

}
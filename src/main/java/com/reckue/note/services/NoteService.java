package com.reckue.note.services;

import com.reckue.note.models.entities.Note;

import java.util.List;
import java.util.UUID;

public interface NoteService {

    List<Note> getAllNotes();
    Note getNoteById(String id);
    Note createNote(Note note);
    Note editNote(String id, Note note);
    void deleteNote(String id);
}

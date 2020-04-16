package com.reckue.post.services;

import com.reckue.post.models.entities.Note;

import java.util.List;

public interface NoteService {

    List<Note> getAllNotes();

    Note getNoteById(String id);

    Note createNote(Note note);

    Note editNote(String id, Note note);

    void deleteNote(String id);
}

package com.reckue.note.services;

import com.reckue.note.models.entities.Note;
import com.reckue.note.models.transfers.NoteDescription;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NoteStorageService {

    @Qualifier("noteServiceMongo")
    private final NoteService noteService;

    public NoteStorageService(NoteService noteService) {
        this.noteService = noteService;
    }

    public NoteDescription getNoteDescriptionById(String id) {
        final Note note = noteService.getNoteById(id);
        return toDescription(note);
    }

    public NoteDescription toDescription(Note note) {
        String description = note.getPayload();
        if (description.length() > 20) {
            description = description.substring(0, 20).concat("...");
        }
        return NoteDescription.builder()
                .id(note.getId())
                .description(description)
                .build();
    }
}

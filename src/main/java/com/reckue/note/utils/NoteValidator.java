package com.reckue.note.utils;

import com.reckue.note.models.entities.Note;

public class NoteValidator {

    public static Note validateNote(Note note) {
        String id = validateId(note.getId());
        String payload = validatePayload(note.getPayload());
        return Note.builder()
                .id(id)
                .payload(payload)
                .build();
    }

    public static String validateId(String id) {
        if (id != null) {
            return id;
        }
        return CustomGenerator.randomUUID();
    }

    public static String validatePayload(String payload) {
        if (payload == null) {
            return "";
        } else if (payload.length() > 1024) {
            return shortcutPayload(payload);
        }
        return payload;
    }

    public static String shortcutPayload(String payload) {
        return payload.substring(0, 1024).concat("...");
    }
}

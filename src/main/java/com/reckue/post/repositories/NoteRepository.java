package com.reckue.post.repositories;

import com.reckue.post.models.entities.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
}

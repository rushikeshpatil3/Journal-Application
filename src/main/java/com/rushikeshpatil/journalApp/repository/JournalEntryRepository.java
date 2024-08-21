package com.rushikeshpatil.journalApp.repository;

import com.rushikeshpatil.journalApp.entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalEntryRepository extends MongoRepository<JournalEntity, ObjectId> {
}

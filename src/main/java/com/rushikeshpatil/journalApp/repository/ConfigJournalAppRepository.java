package com.rushikeshpatil.journalApp.repository;

import com.rushikeshpatil.journalApp.entity.ConfigJournalAppEntity;
import com.rushikeshpatil.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {

}

package com.journal.journalapp.repository;

import com.journal.journalapp.model.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JournalEntryRepository extends MongoRepository<JournalEntry,String> {

    Optional<JournalEntry> findById(String id);

    void deleteById(String id);
}

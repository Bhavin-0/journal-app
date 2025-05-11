package com.journal.journalapp.service;

import com.journal.journalapp.model.JournalEntry;
import com.journal.journalapp.repository.JournalEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//Business logic
//tolks to the database
@Service
public class JournalService {
    private final JournalEntryRepository repository;

    public JournalService(JournalEntryRepository repository){
        this.repository = repository;
    }

    public JournalEntry createEntry(String title,String content){
        JournalEntry entry = new JournalEntry();
        entry.setTitle(title);
        entry.setContent(content);
        entry.setCreatedAt(LocalDateTime.now());
        return repository.save(entry);
    }

    public List<JournalEntry> getAllEntries(){
        return repository.findAll();
    }

    public Optional<JournalEntry> getEntryById(Long id){
        return repository.findById(id);
    }

    public void deleteEntryById(Long id){
        Optional<JournalEntry> optionalJournalEntry = repository.findById(id);
        if(optionalJournalEntry.isPresent()){
            repository.deleteById(id);
        }else{
            throw new RuntimeException("Entry with ID : "+id+" not found.");
        }
    }

    //to save a journal Entry
    public JournalEntry save(JournalEntry entry){
        return repository.save(entry);
    }
}

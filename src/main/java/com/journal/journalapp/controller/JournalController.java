package com.journal.journalapp.controller;

//handles https requests -> returns https response
//controller talks to the service layer

import com.journal.journalapp.model.JournalEntry;
import com.journal.journalapp.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController             //Makes this a REST API controller
@RequestMapping("/journal")         //base path for all endpoints
public class JournalController {
    private final JournalService journalService;

    @Autowired
    public JournalController(JournalService journalService){
        this.journalService = journalService;
    }

    //create new journal Entry
    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry){
        JournalEntry created = journalService.createEntry(entry.getTitle(),entry.getContent());
        return ResponseEntity.ok(created);
    }

    //get all entries
    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAllEntries(){
        return ResponseEntity.ok(journalService.getAllEntries());
    }

    @PutMapping("/{id}")
    public ResponseEntity<JournalEntry> updateEntry(@PathVariable Long id,@RequestBody JournalEntry entry){

        return journalService.getEntryById(id)
                .map(journal ->{
                    journal.setTitle(entry.getTitle());
                    journal.setContent(entry.getContent());
                    JournalEntry updateEntry = journalService.save(journal);
                    return ResponseEntity.ok(updateEntry);
                }).orElse(ResponseEntity.notFound().build());

    }

    //Get a specific journal Entry by ID
    @GetMapping("/{id}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable Long id){
        return journalService.getEntryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //delete Entry by Id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEntryById(@PathVariable Long id){
       try {
           journalService.deleteEntryById(id);
           return ResponseEntity.ok("Entry deleted Successfully");
       }catch(RuntimeException e){
           return ResponseEntity.notFound().build();
       }
    }

}

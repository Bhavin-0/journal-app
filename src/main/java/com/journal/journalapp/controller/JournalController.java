package com.journal.journalapp.controller;

//handles https requests -> returns https response
//controller talks to the service layer

import com.journal.journalapp.dto.JournalEntryDTO;
import com.journal.journalapp.model.JournalEntry;
import com.journal.journalapp.service.JournalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @PostMapping("/entries")
    public ResponseEntity<JournalEntry> createEntry(@Valid @RequestBody JournalEntryDTO dto){
        JournalEntry created = journalService.createEntry(dto.getTitle(),dto.getContent());
        return new ResponseEntity<>(journalService.save(created), HttpStatus.CREATED);
    }

    //get all entries
    @GetMapping("/entries")
    public ResponseEntity<List<JournalEntry>> getAllEntries(){
        return ResponseEntity.ok(journalService.getAllEntries());
    }

    @PutMapping("/entries/{id}")
    public ResponseEntity<JournalEntry> updateEntry(@Valid @PathVariable String id,@RequestBody JournalEntryDTO dto){

        return journalService.getEntryById(id)
                .map(journal ->{
                    journal.setTitle(dto.getTitle());
                    journal.setContent(dto.getContent());
                    JournalEntry updateEntry = journalService.save(journal);
                    return ResponseEntity.ok(updateEntry);
                }).orElse(ResponseEntity.notFound().build());

    }

    //Get a specific journal Entry by ID
    @GetMapping("/entries/{id}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable String id){
        return journalService.getEntryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //delete Entry by Id
    @DeleteMapping("/entries/{id}")
    public ResponseEntity<String> deleteEntryById(@PathVariable String id){
       try {
           journalService.deleteEntryById(id);
           return ResponseEntity.ok("Entry deleted Successfully");
       }catch(RuntimeException e){
           return ResponseEntity.notFound().build();
       }
    }

}

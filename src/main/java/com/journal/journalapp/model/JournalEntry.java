package com.journal.journalapp.model;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "journalEntry")
public class JournalEntry {
    @Id
    private String id;

    @NotBlank(message="Title must not be empty")
    @Size(max=100,message="Title must be under 100 characters")
    private String title;


    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //constructors
    public JournalEntry(){}

    public JournalEntry(String title, String content,LocalDateTime createdAt){
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    //getters and setters
    public String getId(){
        return  id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public LocalDateTime getCreatedAT(){
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    public void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    public void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
}

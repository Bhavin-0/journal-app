package com.journal.journalapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class JournalEntryDTO {

    @NotBlank(message = "Title must not be Blank")
    @Size(max = 100,message = "Title must be within 100 Characters")
    String title;

    String content;
    //getters setters
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
}

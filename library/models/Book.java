package library.models;

import java.util.*;
public class Book {
    String title;
    int bookId;
    String author;
    boolean issued;

    public Book(String title, int bookId, String author){
        this.author = author;
        this.title = title;
        this.bookId = bookId;
        this.issued = false;
    }


    public int getBookId(){
        return bookId;
    }

    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public void issue(){
        this.issued = true;
    }

    public void deissue(){
        this.issued = false;
    }

    public boolean checkIfIssued(){
        return this.issued;
    }
}

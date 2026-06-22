package library.repository;
import java.util.*;

import library.models.*;

public class BookRepository {
    private final HashMap<Integer,Book> bookRepository;

    public BookRepository(){
        this.bookRepository = new HashMap<>();
    }

    public void addBook(Book book){
        bookRepository.put(book.getBookId(), book);
    }

    public Book getBook(int bookId){
        if(bookRepository.containsKey(bookId)){
            return bookRepository.get(bookId);
        }
        return null;
    }

    public boolean bookExists(int bookId){
         if(bookRepository.containsKey(bookId)){
            return true;
        }
        return false;
    }

    public List<Book> getAllBooks(){
        return new ArrayList<>(bookRepository.values());
    }


}

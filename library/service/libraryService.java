package library.service;

import java.util.*;
import library.models.*;
import library.repository.*;

public class libraryService {
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
  Map<Integer, IssueRecord> issueRecords;
    private int bookId, memberId;

    public libraryService(BookRepository bookRepository,MemberRepository memberRepository){
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.issueRecords = new HashMap<>();
        this.bookId = 1;
        this.memberId = 1;
    }

    public void addBook(String title,String author){
        this.bookId++;
        if(bookRepository.bookExists(bookId)){
            throw new IllegalArgumentException("Book Already Exists");
        }

        Book book = new Book(title, bookId, author);
        bookRepository.addBook(book);
    }

    public void addMember(String name){
        this.memberId++;
        if(memberRepository.memberExists(memberId)){
            throw new IllegalArgumentException("Memeber Already Exists");
        }

        Member member = new Member(this.memberId, name);
        memberRepository.addMemeber(member);
       
    }


    public void borrowBook(int bookId, int memberId){
        if(bookRepository.bookExists(bookId)){
            Book book = bookRepository.getBook(bookId);
            if(book.checkIfIssued()){
                System.out.println("Book has been issued to a Member and hence not available at the moment");
            }else{
                IssueRecord record = new IssueRecord(book, memberRepository.getMember(memberId));
                issueRecords.put(book.getBookId(),record);
                book.issue();
                System.out.println("The book has been issued to you!");
            }
        }else{
             System.out.println("Book doesnt Exists");
        }
    }

    public void returnBook(int bookId, int memberId){
        if(bookRepository.bookExists(bookId)){
            Book book = bookRepository.getBook(bookId);
            if(book.checkIfIssued()){
                issueRecords.remove(bookId);
                book.deissue();
            }else{
                System.out.println("Duplicate this book was never issued");
            }
        }else{
             System.out.println("Book doesnt Exists");
        }
    }




}

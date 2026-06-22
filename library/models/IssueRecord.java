package library.models;

import java.time.LocalDate;

public class IssueRecord {

    private Book book;

    private Member member;

    private LocalDate issueDate;

    public IssueRecord(
            Book book,
            Member member) {

        this.book = book;
        this.member = member;
        this.issueDate = LocalDate.now();
    }

    public Book getBook() {
        return book;
    }

    public Member getMember() {
        return member;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }
}
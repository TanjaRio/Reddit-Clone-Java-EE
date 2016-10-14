package no.westerdals.riotan14.smallRedditClone.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.List;

/**
 * Created by Tanja Guntvedt Rio
 * PG5100 - Enterprise Programmering 1
 * Westerdals Oslo ACT
 */

@Entity
public class Comment {

    @Id @NotNull @GeneratedValue
    private Long id;

    @NotNull @Size(max = 100)
    private String author;

    @NotNull @Size(max = 1000)
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    private Date postedDate;

    private int numberOfUpvotes;
    private int numberOfDownvotes;

    public Comment(){}

    public Comment(Long id, String author, String text){
        this.id = id;
        this.author = author;
        this.text = text;
        numberOfDownvotes = 0;
        numberOfUpvotes = 0;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNumberOfUpvotes() {
        return numberOfUpvotes;
    }

    public void setNumberOfUpvotes(int numberOfUpvotes) {
        this.numberOfUpvotes = numberOfUpvotes;
    }

    public int getNumberOfDownvotes() {
        return numberOfDownvotes;
    }

    public void setNumberOfDownvotes(int numberOfDownvotes) {
        this.numberOfDownvotes = numberOfDownvotes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

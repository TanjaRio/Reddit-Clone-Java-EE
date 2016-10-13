package no.westerdals.riotan14.smallRedditClone.entity;

import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Created by Tanja Guntvedt Rio
 * PG5100 - Enterprise Programmering 1
 * Westerdals Oslo ACT
 */
public class Comment {

    private String author;
    private String text;
    private List<Comment> comments;
    private int numberOfUpvotes;
    private int numberOfDownvotes;
}

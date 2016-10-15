package no.westerdals.riotan14.smallRedditClone.backend.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Tanja Guntvedt Rio
 * PG5100 - Enterprise Programmering 1
 * Westerdals Oslo ACT
 */

@NamedQueries({
        @NamedQuery(name = Post.GET_COUNT_OF_POSTS_FROM_COUNTRY, query = "SELECT COUNT(e) FROM Post e JOIN User u WHERE u.country = :ucountry"),
        @NamedQuery(name = Post.GET_TOTAL_POST_COUNT, query = "SELECT COUNT(e) FROM Post e"),
        @NamedQuery(name = Post.GET_ALL, query = "SELECT p FROM Post p")
})
@Entity
public class Post {

    public static final String GET_COUNT_OF_POSTS_FROM_COUNTRY = "GET_COUNT_OF_POSTS_FROM_COUNTRY";
    public static final String GET_TOTAL_POST_COUNT = "GET_TOTAL_POST_COUNT";
    public static final String GET_ALL = "get_all";

    @Id @GeneratedValue
    private Long postId;

    @OneToMany(targetEntity=Comment.class, fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    //@OrderBy("postedDate DESC")
    private List<Comment> comments;

    private String author;

    private String content;

    private int numberOfUpvotes;

    private int numberOfDownvotes;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeOfPostPublished;

    public Post() {}

    public Post(Long postId, String content, String author) {
        this.postId = postId;
        this.content = content;
        this.author = author;
        this.comments = new ArrayList<>();
        this.numberOfUpvotes = 0;
        this.numberOfDownvotes = 0;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Date getTimeOfPostPublished() {
        return timeOfPostPublished;
    }

    public void setTimeOfPostPublished(Date timeOfPostPublished) {
        this.timeOfPostPublished = timeOfPostPublished;
    }

    /*
*/

}

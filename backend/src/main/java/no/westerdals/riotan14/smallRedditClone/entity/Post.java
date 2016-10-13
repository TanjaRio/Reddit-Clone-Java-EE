package no.westerdals.riotan14.smallRedditClone.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Tanja Guntvedt Rio
 * PG5100 - Enterprise Programmering 1
 * Westerdals Oslo ACT
 */

@NamedQueries({
        @NamedQuery(name = Post.GET_COUNT_OF_POSTS_FROM_COUTRY, query = "SELECT COUNT(e) FROM Post e JOIN User u WHERE u.country = :ucountry"),
        @NamedQuery(name = Post.GET_TOTAL_POST_COUNT, query = "SELECT COUNT(e) FROM Post e")
})
@Entity
public class Post {

    public static final String GET_COUNT_OF_POSTS_FROM_COUTRY = "GET_COUNT_OF_POSTS_FROM_COUTRY";
    public static final String GET_TOTAL_POST_COUNT = "GET_TOTAL_POST_COUNT";

    @Id @GeneratedValue
    private Long postId;

    @OneToMany(mappedBy = "userComments", fetch= FetchType.EAGER)
    private List<Comment> userComments;

    private String author;

    private String content;

    private int numberOfUpvotes;

    private int numberOfDownvotes;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeOfPostPublished;

    /*
*/

}

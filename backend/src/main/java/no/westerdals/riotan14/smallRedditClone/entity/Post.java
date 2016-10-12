package no.westerdals.riotan14.smallRedditClone.entity;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.List;

/**
 * Created by Tanja Guntvedt Rio
 * PG5100 - Enterprise Programmering 1
 * Westerdals Oslo ACT
 */

@NamedQueries({
        @NamedQuery(name = Post.GET_COUNTRIES, query = "SELECT COUNT(e) FROM Post e WHERE u.country = :ucountry")
})
public class Post {

    public static final String GET_COUNTRIES = "POST_GET_ALL";

    private List<Comment> userComments;
    private String author;
    private String content;
    private Date

    /*
    * Should contain info on the author,
the text of the news, when it was created, how many people up/down-voted it, etc. It can
have one or more comments by other users.
*/

}

/*
Post/News: the “news” that is published on the site. Should contain info on the author,
the text of the news, when it was created, how many people up/down-voted it, etc. It can
have one or more comments by other users.
* */
@javax.persistence.Entity
@Entity
public class Post {
    @Id @GeneratedValue
    private long id;

    @NotNull
    private String author;

    @NotNull
    private String text;

    private List<Comment>;
    private int numberOfUpvotes;
    private int numberOfDownvotes;

    @Temporal(TemporalType.DATE)
    private Date dateOfCreation;

}
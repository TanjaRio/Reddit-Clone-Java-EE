package no.westerdals.riotan14.smallRedditClone.backend.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = User.GET_ALL, query = "SELECT e FROM User e"),
        @NamedQuery(name = User.GET_ALL_USERS_FROM_SPECIFIC_COUNTRY, query = "SELECT COUNT(e) FROM User e WHERE e.country = :fcountry"),
        @NamedQuery(name = User.GET_ALL_COUNTRIES, query = "SELECT e.country FROM User e"),
        @NamedQuery(name = User.GET_USERS_WITH_MOST_POSTS, query = "SELECT u FROM User u ORDER BY MAX(u.userPosts) DESC"),
        @NamedQuery(name = User.GET_USERS_WITH_MOST_COMMENTS, query = "SELECT u FROM User u ORDER BY MAX(u.commentsByUser) DESC")
})
public class User {

    public static final String GET_ALL = "GET_ALL_USERS";
    public static final String GET_ALL_USERS_FROM_SPECIFIC_COUNTRY = "GET_ALL_USERS_FROM_SPECIFIC_COUNTRY";
    public static final String GET_ALL_COUNTRIES = "GET_ALL_COUNTRIES";
    public static final String GET_USERS_WITH_MOST_POSTS = "GET_USERS_WITH_MOST_POSTS";
    public static final String GET_USERS_WITH_MOST_COMMENTS = "GET_USERS_WITH_MOST_COMMENTS";

    @Id
    @NotNull
    @Pattern(regexp = "[A-Za-z0-9]{1,32}")
    @Size(min = 6)
    private String userID;

    @NotNull
    private String hash;

    @NotNull @Size(max = 26)
    private String salt;

    @NotNull @Size(min = 0, max = 100)
    private String firstName;

    @Size(min = 0, max = 100)
    private String middleName;

    @NotNull @Size(min = 0, max = 100)
    private String lastName;

    @NotNull @Size(min = 2, max = 150)
    private String email;

    @NotNull @Size(min = 2, max = 150)
    private String country;

    @OneToMany(targetEntity=Post.class, fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Post> userPosts;


    @OneToMany(targetEntity=Comment.class, fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Comment> commentsByUser;

    public User(){}


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Post> getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(List<Post> userPosts) {
        this.userPosts = userPosts;
    }

    public List<Comment> getCommentsByUser() {
        return commentsByUser;
    }

    public void setCommentsByUser(List<Comment> commentsByUser) {
        this.commentsByUser = commentsByUser;
    }
}


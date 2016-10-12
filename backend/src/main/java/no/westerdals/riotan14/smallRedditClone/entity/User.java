package no.westerdals.riotan14.smallRedditClone.entity;

import com.sun.istack.internal.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanja Guntvedt Rio
 * PG5100 - Enterprise Programmering 1
 * Westerdals Oslo ACT
 */

@Entity
@NamedQueries({
        @NamedQuery(name = User.GET_ALL, query = "SELECT u FROM User u"),
        @NamedQuery(name = User.GET_COUNTRY, query = "SELECT e FROM User e WHERE u.country = :ucountry")
})
public class User {

    public static final String GET_ALL = "USER_GET_ALL";
    public static final String GET_COUNTRY = "USER_GET_COUNTRY";

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

    @OneToMany(mappedBy= "userPosted", fetch= FetchType.EAGER)
    private List<Post> userPosts;

    @OneToMany
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


}

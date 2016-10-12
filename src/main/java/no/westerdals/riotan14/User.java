import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@javax.persistence.Entity;


/*the set of countries the users are from
◦ how many posts there are in total, and for a specific country
◦ the total number of users, and for a specific country
◦ the top X users that wrote the most posts/comments*/
@Entity
@NamedQueries({
        @NamedQuery(name = User.findByName, query = "select u from User u where u.name = :name"),
        @NamedQuery(name = User.findTotalNumberOfUserFromCountry, query = "count * from User u where u.country = :country")
})
public class User {

    @Id @GeneratedValue
    private long userId;

    private String address;

    @Pattern(regexp =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String email;
    private String name;
    private String surname;
    private String country;

    private List<Comment>;


    public User(){}

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
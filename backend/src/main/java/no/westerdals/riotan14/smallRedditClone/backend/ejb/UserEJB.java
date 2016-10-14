package no.westerdals.riotan14.smallRedditClone.backend.ejb;

import no.westerdals.riotan14.smallRedditClone.backend.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

/**
 * Created by Tanja Guntvedt Rio
 * PG5100 - Enterprise Programmering 1
 * Westerdals Oslo ACT
 */
@Stateless
public class UserEJB {

    @PersistenceContext
    private EntityManager em;

    public UserEJB(){}

    public boolean createUser(String userId, String password, String firstName, String email, String middleName, String lastName, String country) {
        if (userId == null || userId.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }

        User user = findUser(userId);
        if (user != null) {
            //a user with same id already exists
            return false;
        }

        user = new User();
        user.setUserID(userId);

        String salt = getSalt();
        user.setSalt(salt);

        String hash = computeHash(password, salt);
        user.setHash(hash);

        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setCountry(country);
        user.setEmail(email);

        em.persist(user);

        return true;
    }

    public User findUser(String userId){
        return em.find(User.class, userId);
    }

    /*@NamedQuery(name = User.GET_ALL, query = "SELECT e FROM User e"),
        @NamedQuery(name = User.GET_ALL_USERS_FROM_SPECIFIC_COUNTRY, query = "SELECT COUNT(e) FROM User e WHERE e.country = :fcountry"),
        @NamedQuery(name = User.GET_ALL_COUNTRIES, query = "SELECT e.country FROM User e"),
        @NamedQuery(name = User.GET_USERS_WITH_MOST_POSTS, query = "SELECT u FROM User u ORDER BY MAX(u.userPosts) DESC"),
        @NamedQuery(name = User.GET_USERS_WITH_MOST_COMMENTS, query = "SELECT u FROM User u ORDER BY MAX(u.commentsByUser) DESC")*/
    public List<User> getAllUsers() {
        return em.createNamedQuery(User.GET_ALL, User.class).getResultList();
    }

    public List<User> getUsersWithMostComments(int topNumberOfUsers) {
        return em.createNamedQuery(User.GET_USERS_WITH_MOST_POSTS, User.class).getResultList();
    }


    public List<User> getAllUsersFromSpecificCountry(String country){
        return em.createNamedQuery(User.GET_USERS_WITH_MOST_POSTS).setParameter("fcountry", country).getResultList();
    }

    public boolean login(String userId, String password) {
        if (userId == null || userId.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }

        User userDetails = findUser(userId);
        if (userDetails == null) {
            return false;
        }

        String hash = computeHash(password, userDetails.getSalt());

        boolean isValidUser = hash.equals(userDetails.getHash());
        return isValidUser;
    }



    //-----------------------------------Hash and Salt creation-------------------------------------


    @javax.validation.constraints.NotNull
    protected String computeHash(String password, String salt){
        String combined = password + salt;
        String hash = DigestUtils.sha256Hex(combined);
        return hash;
    }

    @javax.validation.constraints.NotNull
    protected String getSalt(){
        SecureRandom random = new SecureRandom();
        int bitsPerChar = 5;
        int twoPowerOfBits = 32; // 2^5
        int n = 26;
        assert n * bitsPerChar >= 128;

        String salt = new BigInteger(n * bitsPerChar, random).toString(twoPowerOfBits);
        return salt;
    }

}

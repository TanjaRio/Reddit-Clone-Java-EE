package no.westerdals.riotan14.smallRedditClone.ejb;

import no.westerdals.riotan14.smallRedditClone.entity.*;
import org.h2.engine.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

/**
 * Created by Tanja Guntvedt Rio
 * PG5100 - Enterprise Programmering 1
 * Westerdals Oslo ACT
 */
@Stateless
public class PostEJB {

    @PersistenceContext
    private EntityManager em;

    public PostEJB() {}

    public boolean createPost(String author, String content, Date date, String userId) {
        if (userId == null) {
            return false;
        }

        User user = em.find(User.class, userId);
        if (user == null) {
            return false;
        }


    }
}

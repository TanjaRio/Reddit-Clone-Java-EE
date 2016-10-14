package no.westerdals.riotan14.smallRedditClone.backend.ejb;

import no.westerdals.riotan14.smallRedditClone.backend.entity.Post;
import org.h2.engine.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

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

    public boolean createPost(String author, String content, String userId) {
        if (userId == null) {
            return false;
        }

        User user = em.find(User.class, userId);
        if (user == null) {
            return false;
        }

        Post post = new Post(null, author, content);
        em.persist(post);

        return true;
    }


    public int getCountOfPostsFromCountry(String country) {
        String count = em.createNamedQuery(Post.GET_COUNT_OF_POSTS_FROM_COUNTRY).setParameter("ucountry", country).toString();
        int result = Integer.parseInt(count);
        return result;
    }

    public int getTotalPostCount() {
        String count = em.createNamedQuery(Post.GET_TOTAL_POST_COUNT, Post.class).toString();
        int result = Integer.parseInt(count);
        return result;
    }

    public List<Post> getAllNews() {
        return em.createNamedQuery(Post.GET_ALL).getResultList();

    }
}

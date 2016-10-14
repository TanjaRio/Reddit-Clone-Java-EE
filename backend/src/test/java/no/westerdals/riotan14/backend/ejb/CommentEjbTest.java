package no.westerdals.riotan14.backend.ejb;

import no.westerdals.riotan14.smallRedditClone.backend.ejb.CommentEJB;
import no.westerdals.riotan14.smallRedditClone.backend.ejb.PostEJB;
import no.westerdals.riotan14.smallRedditClone.backend.ejb.UserEJB;
import no.westerdals.riotan14.smallRedditClone.backend.entity.Comment;
import no.westerdals.riotan14.smallRedditClone.backend.entity.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Tanja Guntvedt Rio
 * PG5100 - Enterprise Programmering 1
 * Westerdals Oslo ACT
 */
@RunWith(Arquillian.class)
public class CommentEjbTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "no.westerdals.riotan14.smallRedditClone")
                .addClass(DeleterEJB.class)
                .addPackages(true, "org.apache.commons.codec")
                .addAsResource("META-INF/persistence.xml");
    }

    @EJB
    private UserEJB userEJB;
    @EJB
    private CommentEJB commentEjb;
    @EJB
    private DeleterEJB deleterEJB;
    @EJB
    private PostEJB postEjb;

    @PersistenceContext
    EntityManager em;

    private boolean createUser(String user, String password) {
        return userEJB.createUser(user, password, "a", "t@gmail.com", "b", "c", "Norway");
    }

    private Long createComment(String userId, String text) {
        return commentEjb.createComment(text, userId);
    }

    @Test
    public void testCreateComment() {
        createUser("tommy39", "hemmelig");
        User user = em.find(User.class, "tommy39");
        String comment = "Hei jeg likte posten veldig godt";
        Long id = createComment(user.getUserID(), comment);

        List<Comment> comments = user.getCommentsByUser();
        assertEquals(comments.size(), 1);
        assertEquals(comments.get(0), comment);

    }

    @Test
    public void testCanCreateComment() {
        createUser("tommy39", "hemmelig");
        User user = em.find(User.class, "tommy39");
        String comment = "Hei jeg likte posten veldig godt";
        commentEjb.createComment(comment, user.getUserID());
        List<Comment> commentList = user.getCommentsByUser();
        assertTrue(commentList.contains(comment));
    }

    @Test
    public void testFindComment() {
        String userId = "ola";
        createUser(userId, "nordmann");
        User user = em.find(User.class, userId);
        Long commentId = createComment(user.getUserID(), "heisann");
        commentEjb.findComment(commentId);
        assertNotNull(commentId);
    }

    @Test
    public void testGetComments() {
        createUser("tommy39", "hemmelig");
        User user = em.find(User.class, "tommy39");
        String comment = "Hei jeg likte posten veldig godt";
        commentEjb.createComment(comment, user.getUserID());
        List<Comment> commentList = user.getCommentsByUser();
        assertTrue(commentList.contains(comment));
        assertEquals(commentList.size(), 1);
        assertEquals(commentList.get(0), comment);
    }
}

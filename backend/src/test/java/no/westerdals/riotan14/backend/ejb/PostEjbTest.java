package no.westerdals.riotan14.backend.ejb;
import no.westerdals.riotan14.smallRedditClone.backend.ejb.CommentEJB;
import no.westerdals.riotan14.smallRedditClone.backend.ejb.PostEJB;
import no.westerdals.riotan14.smallRedditClone.backend.ejb.UserEJB;

import no.westerdals.riotan14.smallRedditClone.backend.entity.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertEquals;

/**
 * Created by Tanja Guntvedt Rio
 * PG5100 - Enterprise Programmering 1
 * Westerdals Oslo ACT
 */
@RunWith(Arquillian.class)
public class PostEjbTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "no.westerdals.riotan14.smallRedditClone")
                .addClass(DeleterEJB.class)
                .addPackages(true, "org.apache.commons.codec")
                .addAsResource("META-INF/persistence.xml");
    }

    @PersistenceContext
    EntityManager em;
    @EJB
    private UserEJB userEJB;
    @EJB
    private CommentEJB commentEjb;
    @EJB
    private DeleterEJB deleterEJB;
    @EJB
    private PostEJB postEjb;


    @Before
    @After
    public void emptyDatabase(){
        deleterEJB.deleteEntities(User.class);
    }


    @Test
    public void testGetCountries(){
        userEJB.createUser("en", "test","tanja", "tanjario@hotmail.no", null, "rio", "Norway");
        int result = postEjb.getCountOfPostsFromCountry("Norway");
        assertEquals(1, result);
    }

    @Test
    public void testGetTotalPostCount(){
        int result = postEjb.getTotalPostCount();
        assertEquals(0, result);
    }
}

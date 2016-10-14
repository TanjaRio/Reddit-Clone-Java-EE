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
import javax.ejb.EJBException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Tanja Guntvedt Rio
 * PG5100 - Enterprise Programmering 1
 * Westerdals Oslo ACT
 */
@RunWith(Arquillian.class)
public class UserEJBTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "no.westerdals.riotan14")
                .addClass(DeleterEJB.class)
                .addPackages(true, "org.apache.commons.codec")
                .addAsResource("META-INF/persistence.xml");
    }

    @EJB
    private UserEJB userEJB;
    @EJB
    private CommentEJB commentEjb;
    @EJB
    private PostEJB postEjb;
    @EJB
    private DeleterEJB deleterEJB;


    @Before
    @After
    public void emptyDatabase() {
        deleterEJB.deleteEntities(User.class);
    }


    private boolean createUser(String user, String password) {
        return userEJB.createUser(user, password, "a", "t@gmail.com", "b", "c", "Norway");
    }

    @Test
    public void testCanCreateAUser() {

        String user = "user";
        String password = "password";

        boolean created = createUser(user, password);
        assertTrue(created);
    }


    @Test(expected = EJBException.class)
    public void testCreateAUserWithWrongId() {

        String user = "user!!!";
        String password = "password";

        createUser(user, password);
    }

    @Test(expected = EJBException.class)
    public void testCreateAUserWithEmpty() {

        String user = "    ";
        String password = "password";

        createUser(user, password);
    }


    @Test
    public void testNoTwoUsersWithSameId() {

        String user = "user";

        boolean created = createUser(user, "a");
        assertTrue(created);

        created = createUser(user, "b");
        assertFalse(created);
    }

    @Test
    public void testSamePasswordLeadToDifferentHashAndSalt() {

        String password = "password";
        String first = "first";
        String second = "second";

        createUser(first, password);
        createUser(second, password); //same password

        User f = userEJB.findUser(first);
        User s = userEJB.findUser(second);

        assertNotEquals(f.getHash(), s.getHash());
        assertNotEquals(f.getSalt(), s.getSalt());
    }

    @Test
    public void testVerifyPassword() {

        String user = "user";
        String correct = "correct";
        String wrong = "wrong";

        createUser(user, correct);

        boolean canLogin = userEJB.login(user, correct);
        assertTrue(canLogin);

        canLogin = userEJB.login(user, wrong);
        assertFalse(canLogin);
    }

    @Test
    public void testBeSurePasswordIsNotStoredInPlain() {

        String user = "user";
        String password = "password";

        createUser(user, password);

        User entity = userEJB.findUser(user);
        assertNotEquals(password, entity.getUserID());
        assertNotEquals(password, entity.getHash());
        assertNotEquals(password, entity.getSalt());
    }

}


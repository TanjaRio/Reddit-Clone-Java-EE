package no.westerdals.riotan14.backend.ejb;

import no.westerdals.riotan14.smallRedditClone.ejb.CommentEJB;
import no.westerdals.riotan14.smallRedditClone.ejb.PostEJB;
import no.westerdals.riotan14.smallRedditClone.ejb.UserEJB;
import no.westerdals.riotan14.smallRedditClone.entity.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.spi.ArquillianProxyException;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pg5100.exam_example.backend.entity.User;
import org.pg5100.exam_example.backend.util.DeleterEJB;

import javax.ejb.EJB;
import javax.ejb.EJBException;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class UserEJBTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "org.pg5100.exam_example")
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


    @Before
    @After
    public void emptyDatabase() {
        deleterEJB.deleteEntities(User.class);
    }


    private boolean createUser(String user, String password) {
        return userEJB.createUser(user, password, "a", "b", "c", "Norway");
    }

    @Test
    public void testCreateUser() {

        String userId = "user";
        String password = "password";
        String email = "123456@gmail.com";
        String country = "Norway";
        String firstName = "John";
        String middleName = "";
        String lastName = "Doe";
        userEJB.createUser(userId, password, firstName, null, lastName, email, country);

    }

    @Test(expected = ArquillianProxyException.class)public void testUserCannotBeCreatedWithoutName() {
        userEJB.createUser(null, "Anna", "surname", "name@surname.com", "12we34ty", "2", "");
    }

    @Test
    public void testGetAllUsers() {
        userEJB.createUser(user, password, "a", "b", "c", "Norway")
        List<User> userList = userEJB.getAllUsers();
    }

}

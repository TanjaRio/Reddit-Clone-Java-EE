package no.westerdals.riotan14.backend.ejb;

import no.westerdals.riotan14.smallRedditClone.ejb.CommentEJB;
import no.westerdals.riotan14.smallRedditClone.ejb.PostEJB;
import no.westerdals.riotan14.smallRedditClone.ejb.UserEJB;
import no.westerdals.riotan14.smallRedditClone.entity.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
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
    public void emptyDatabase(){
        deleterEJB.deleteEntities(User.class);
    }


    private boolean createUser(String user, String password){
        return userEJB.createUser(user,password,"a","b","c","Norway");
    }

    @Test
    public void testAttendEvent(){

        String userId = "user";
        String password = "password";
        createUser(userId,password);

        Long eventId = eventEjb.createEvent("title","text","Norway","location",userId);
        assertFalse(userEJB.isUserAttendingEvent(userId, eventId));

        userEJB.addEvent(userId, eventId);
        assertTrue(userEJB.isUserAttendingEvent(userId, eventId));

        userEJB.removeEvent(userId, eventId);
        assertFalse(userEJB.isUserAttendingEvent(userId, eventId));
    }

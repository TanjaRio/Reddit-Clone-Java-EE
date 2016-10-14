package no.westerdals.riotan14.smallRedditClone.backend.ejb;

import no.westerdals.riotan14.smallRedditClone.backend.entity.Comment;
import no.westerdals.riotan14.smallRedditClone.backend.entity.User;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NamedQueries;
import java.util.List;

/**
 * Created by Tanja Guntvedt Rio
 * PG5100 - Enterprise Programmering 1
 * Westerdals Oslo ACT
 */

@Singleton
public class StatisticsEJB {
    @Inject
    private UserEJB userEJB;

    @Inject
    private PostEJB postEJB;

    @Inject
    private CommentEJB commentEJB;

    private List<Comment> allComments;
    private int numberOfPosts;
    private List<PostEJB> allPosts;
    private List<User> allUsers;

    @Schedule(second="10", minute="*", hour="*")
    public void generateStatistics() {
        numberOfPosts = postEJB.getTotalPostCount();
        allUsers = userEJB.getAllUsers();
    }

}
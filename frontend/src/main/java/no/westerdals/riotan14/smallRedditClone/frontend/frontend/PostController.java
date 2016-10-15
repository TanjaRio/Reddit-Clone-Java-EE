package no.westerdals.riotan14.smallRedditClone.frontend.frontend;

import no.westerdals.riotan14.smallRedditClone.backend.ejb.PostEJB;
import no.westerdals.riotan14.smallRedditClone.backend.ejb.UserEJB;
import no.westerdals.riotan14.smallRedditClone.backend.entity.Post;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Tanja Guntvedt Rio
 * PG5100 - Enterprise Programmering 1
 * Westerdals Oslo ACT
 */
@Named
@SessionScoped
public class PostController implements Serializable {
    @EJB
    private PostEJB postEjb;
    @EJB
    private UserEJB userEJB;
    @Inject
    private LoggingController loggingController;

    private List<Post> posts;

    private boolean sortByTime;

    private boolean sortByPopularity;

    private boolean sortByDate;

    public List<Post> getPosts() {
        posts = postEjb.getAllPosts();
        return posts;
    }

    public boolean sortByDate() {
        return sortByDate;
    }

    public void setSortByDate(boolean sortByDate) {
        this.sortByDate = sortByDate;
    }

    public boolean isSortByPopularity() {
        return sortByPopularity;
    }

    public void setSortByPopularity(boolean sortByPopularity) {
        this.sortByPopularity = sortByPopularity;
    }
}

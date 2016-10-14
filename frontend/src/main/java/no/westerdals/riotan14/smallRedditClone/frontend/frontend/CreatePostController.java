package no.westerdals.riotan14.smallRedditClone.frontend.frontend;

import no.westerdals.riotan14.smallRedditClone.backend.ejb.PostEJB;
import no.westerdals.riotan14.smallRedditClone.backend.entity.User;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Tanja Guntvedt Rio
 * PG5100 - Enterprise Programmering 1
 * Westerdals Oslo ACT
 */

@Named
@RequestScoped
public class CreatePostController {
    @EJB
    private PostEJB postEJB;

    @Inject
    private LoggingController loggingController;

    private String formContent;
    private String formAuthor;

    public String registerNew(){

        boolean registered = false;

        User user = loggingController.getUser();
        try {
            registered = postEJB.createPost(formAuthor, formContent, user.getUserID());
        } catch (Exception e){
        }

        if(registered){
            return "home.jsf";
        } else {
            return "createPost.jsf";
        }
    }
}

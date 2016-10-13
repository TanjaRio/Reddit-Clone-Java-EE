package no.westerdals.riotan14.smallRedditClone.ejb;

import com.sun.istack.internal.NotNull;
import no.westerdals.riotan14.smallRedditClone.entity.Comment;
import no.westerdals.riotan14.smallRedditClone.entity.User;
import org.hibernate.validator.constraints.NotEmpty;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanja Guntvedt Rio
 * PG5100 - Enterprise Programmering 1
 * Westerdals Oslo ACT
 */

@Stateless
public class CommentEJB {

    @PersistenceContext
    private EntityManager em;

    public CommentEJB() {

    }
    public boolean createComment(String userId, String postId) {

    }


}

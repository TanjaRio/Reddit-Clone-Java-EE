package no.westerdals.riotan14.smallRedditClone.backend.ejb;

import no.westerdals.riotan14.smallRedditClone.backend.entity.*;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    public void save(@NotNull Long postId, @NotNull String author, @NotNull String content){

        Post post = em.find(Post.class, postId);
        if(post == null){
            return;
        }

        Comment comment = new Comment(null, content, author);
        em.persist(comment);

        post.getComments().add(comment);
    }

    public CommentEJB() {}
    public Long createComment(String text, String userId){

        User user = em.find(User.class, userId);
        if(user == null){
            throw new IllegalArgumentException("No user with id: "+userId);
        }


        Comment comment = new Comment();
        comment.setAuthor(userId);
        comment.setText(text);

        em.persist(comment);

        return comment.getId();
    }

    public Comment findComment(Long commentId){
        return em.find(Comment.class, commentId);
    }


}

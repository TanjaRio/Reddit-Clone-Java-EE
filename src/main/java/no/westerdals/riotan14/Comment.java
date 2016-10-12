/*Should contain info on the author,
the text of the news, when it was created, how many people up/down-voted it, etc. I
* */
@javax.persistence.Entity
@Entity
public class Comment {
    @Id @GeneratedValue
    private Long id;

    private String author;
}
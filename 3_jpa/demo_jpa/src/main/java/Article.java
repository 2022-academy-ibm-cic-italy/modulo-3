import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "ARTICLES")
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "article_id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @ManyToMany
    @JoinTable( name = "AUTHOR_ARTICLES",
            joinColumns = {
            @JoinColumn(name = "article_id")},
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> authors;

}

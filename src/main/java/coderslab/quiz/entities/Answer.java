package coderslab.quiz.entities;


import javax.persistence.*;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private boolean isTrue;

    @ManyToOne
    private Question question;


}

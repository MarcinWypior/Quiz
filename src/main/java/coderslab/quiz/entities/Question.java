package coderslab.quiz.entities;


import org.hibernate.validator.constraints.URL;

import javax.persistence.*;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private String query;
    private boolean isOpenAnswer;
    @URL
    private String picture;


}

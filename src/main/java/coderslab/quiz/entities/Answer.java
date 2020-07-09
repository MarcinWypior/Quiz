package coderslab.quiz.entities;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private boolean proper;

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }


    public void setQuestion(Question question) {
        this.question = question;
    }

    @ManyToOne
    private Question question;

    public Long getId() {
        return id;
    }

    @Size(max = 40)
    @NotEmpty
    public String getText() {
        return text;
    }


    public boolean isProper() {
        return proper;
    }

    public void setProper(boolean proper) {
        this.proper = proper;
    }

    public Question getQuestion() {
        return question;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", question=" + question +
                '}';
    }

}

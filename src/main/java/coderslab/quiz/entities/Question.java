package coderslab.quiz.entities;


import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String query;
    private boolean isOpenAnswer;
    @URL
    private String picture;

    @ManyToOne
    Category category;

    @OneToMany
    List<Answer> answerList;

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", query='" + query + '\'' +
                ", isOpenAnswer=" + isOpenAnswer +
                ", picture='" + picture + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public Category getCategory() {
        return category;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setOpenAnswer(boolean openAnswer) {
        isOpenAnswer = openAnswer;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Long getId() {
        return id;
    }



    public String getQuery() {
        return query;
    }

    public boolean isOpenAnswer() {
        return isOpenAnswer;
    }

    public String getPicture() {
        return picture;
    }
}

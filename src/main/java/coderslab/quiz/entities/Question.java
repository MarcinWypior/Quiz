package coderslab.quiz.entities;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 10,max = 60)
    private String query;
    private boolean isOpenAnswer;
    private String picture;

    @ManyToOne
    Category category;

    @OneToMany(mappedBy = "question",cascade = CascadeType.REMOVE)
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

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Long getId() {
        return id;
    }



    public String getQuery() {
        return query;
    }


    public String getPicture() {
        return picture;
    }

    public void setOpenAnswer(boolean openAnswer) {
        isOpenAnswer = openAnswer;
    }

    public boolean isOpenAnswer() {
        return isOpenAnswer;
    }


}

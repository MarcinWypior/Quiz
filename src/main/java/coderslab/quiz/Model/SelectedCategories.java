package coderslab.quiz.Model;

import coderslab.quiz.entities.Category;

import java.util.ArrayList;

public class SelectedCategories {
    private ArrayList<Category> categories;

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public SelectedCategories() {
    }
}

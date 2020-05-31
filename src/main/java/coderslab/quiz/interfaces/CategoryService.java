package coderslab.quiz.interfaces;

import coderslab.quiz.entities.Category;

import java.util.List;

public interface CategoryService {
    public boolean doesCategoryExist(String categoryName);
    public Category findById(Long id);
    public List<Category> findAll();
    public void save(Category category);
    public void update(Long id);
    public void delete(Long id);
}

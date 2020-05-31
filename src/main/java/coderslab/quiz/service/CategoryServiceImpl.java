package coderslab.quiz.service;


import coderslab.quiz.entities.Category;
import coderslab.quiz.interfaces.CategoryService;
import coderslab.quiz.repositories.CategoryRepository;
import coderslab.quiz.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final QuestionRepository questionRepository;

    public CategoryServiceImpl(
            CategoryRepository categoryRepository, QuestionRepository questionRepository
    ) {
        this.categoryRepository = categoryRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public boolean doesCategoryExist(String categoryName)
    {
        if(categoryRepository.findByCategoryName(categoryName)!=null)
        return true;
        else
            return false;
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findFirstById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void update(Long id) {

    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}

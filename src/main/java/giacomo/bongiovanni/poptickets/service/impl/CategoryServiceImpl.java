package giacomo.bongiovanni.poptickets.service.impl;

import giacomo.bongiovanni.poptickets.dto.CategoryDTO;
import giacomo.bongiovanni.poptickets.dto.mapper.CategoryMapper;
import giacomo.bongiovanni.poptickets.exception.EntityNotFoundException;
import giacomo.bongiovanni.poptickets.model.Category;
import giacomo.bongiovanni.poptickets.model.Event;
import giacomo.bongiovanni.poptickets.repository.CategoryRepository;
import giacomo.bongiovanni.poptickets.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"no category registered");
        }
        return CategoryMapper.INSTANCE.categoriesToCategoriesDTO(categories);
    }

    @Override
    public CategoryDTO findById(long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
        return CategoryMapper.INSTANCE.categoryToCategoryDTO(category);
    }

    @Override
    public CategoryDTO findByName(String name) {
        Category category = categoryRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException(name));
        return CategoryMapper.INSTANCE.categoryToCategoryDTO(category);
    }

    @Override
    public List<Event> findEventByCategoryId(long id) {
        return null;
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public CategoryDTO deleteById(long id) {
        return null;
    }

    @Override
    public CategoryDTO deleteByName(String name) {
        return null;
    }
}

package giacomo.bongiovanni.poptickets.service;

import giacomo.bongiovanni.poptickets.dto.CategoryDTO;
import giacomo.bongiovanni.poptickets.model.Event;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAll();
    CategoryDTO findById(long id);
    CategoryDTO findByName(String name);
    List<Event> findEventByCategoryId(long id);
    CategoryDTO save(CategoryDTO categoryDTO);
    CategoryDTO deleteById(long id);
    CategoryDTO deleteByName(String name);

}

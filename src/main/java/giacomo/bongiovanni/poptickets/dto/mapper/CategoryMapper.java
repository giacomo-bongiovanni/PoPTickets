package giacomo.bongiovanni.poptickets.dto.mapper;

import giacomo.bongiovanni.poptickets.dto.CategoryDTO;
import giacomo.bongiovanni.poptickets.model.Category;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
    List<CategoryDTO> categoriesToCategoriesDTO(List<Category> categories);
    @InheritInverseConfiguration
    Category categoryDTOToCategory(CategoryDTO categoryDTO);
}

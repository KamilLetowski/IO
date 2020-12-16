package pb.zio.games.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pb.zio.games.dto.CategoryDTO;
import pb.zio.games.entity.CategoryEntity;
import pb.zio.games.repository.CategoryRepository;

@Component
@RequiredArgsConstructor
public class CategoryMapper extends AbstractMapper<CategoryEntity, CategoryDTO> {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryEntity mapToEntity(CategoryDTO dto) {
        CategoryEntity category = dto.getId() != null ? categoryRepository.findById(dto.getId()).orElse(new CategoryEntity()) : new CategoryEntity();
        category.setName(dto.getName());
        return category;
    }

    @Override
    public CategoryDTO mapToDto(CategoryEntity entity) {
        return CategoryDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}

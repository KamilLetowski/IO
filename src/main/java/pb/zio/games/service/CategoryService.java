package pb.zio.games.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pb.zio.games.dto.CategoryDTO;
import pb.zio.games.mapper.CategoryMapper;
import pb.zio.games.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final @NonNull CategoryRepository repo;
    private final @NonNull CategoryMapper mapper;

    public void save(CategoryDTO dto) {
        repo.save(mapper.mapToEntity(dto));
    }

    public List<CategoryDTO> findAll() {
        return mapper.mapToDtos(repo.findAll());
    }
}

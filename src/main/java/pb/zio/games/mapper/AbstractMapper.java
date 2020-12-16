package pb.zio.games.mapper;

import pb.zio.games.dto.AbstractDTO;
import pb.zio.games.entity.AbstractEntity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractMapper<ENTITY extends AbstractEntity, DTO extends AbstractDTO> {

    public abstract ENTITY mapToEntity(DTO dto);

    public abstract DTO mapToDto(ENTITY entity);

    public List<ENTITY> mapToEntities(Collection<DTO> dtos) {
        return dtos.stream().map(this::mapToEntity).collect(Collectors.toList());
    }

    public List<DTO> mapToDtos(Collection<ENTITY> entities) {
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }
}

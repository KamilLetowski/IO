package pb.zio.games.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pb.zio.games.dto.UserDTO;
import pb.zio.games.entity.UserEntity;
import pb.zio.games.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class UserMapper extends AbstractMapper<UserEntity, UserDTO> {

    private final UserRepository userRepository;

    @Override
    public UserEntity mapToEntity(UserDTO dto) {
        UserEntity user = dto.getUsername() != null ? userRepository.findByUsername(dto.getUsername()).orElse(new UserEntity()) : new UserEntity();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setCreationDate(dto.getCreationDate());
        return user;
    }

    @Override
    public UserDTO mapToDto(UserEntity entity) {
        return UserDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .creationDate(entity.getCreationDate())
                .build();
    }
}

package pb.zio.games.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pb.zio.games.dto.GameDTO;
import pb.zio.games.dto.UserDTO;
import pb.zio.games.entity.GameEntity;
import pb.zio.games.entity.UserEntity;
import pb.zio.games.mapper.GameMapper;
import pb.zio.games.mapper.UserMapper;
import pb.zio.games.repository.GameRepository;
import pb.zio.games.repository.UserRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final @NonNull UserRepository repository;
    private final @NonNull UserMapper mapper;
    private final @NonNull GameRepository gameRepository;
    private final @NonNull GameMapper gameMapper;

    public String login(String username, String password) {
        Optional<UserEntity> user = repository.login(username, password);
        if (user.isPresent()) {
            String token = UUID.randomUUID().toString();
            UserEntity userEntity = user.get();
            userEntity.setToken(token);
            repository.save(userEntity);
            return token;
        }
        return StringUtils.EMPTY;
    }

    public Optional<User> findByToken(String token) {
        Optional<UserEntity> userEntity = repository.findByToken(token);
        if (userEntity.isPresent()) {
            UserEntity customer = userEntity.get();
            User user = new User(customer.getUsername(), customer.getPassword(), true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
            return Optional.of(user);
        }
        return Optional.empty();
    }

    public UserDTO getUserDetails(String username) {
        Optional<UserEntity> userEntity = repository.findByUsername(username);
        return userEntity.map(entity -> mapper.mapToDto(entity)).orElse(null);
    }

    public void addGameToFav(Long userId, Long gameId) {
        Optional<UserEntity> userEntity = repository.findById(userId);
        Optional<GameEntity> gameEntity = gameRepository.findById(gameId);
        if(userEntity.isPresent() && gameEntity.isPresent()) {
            Set<GameEntity> userGames = gameRepository.findByUserId(userId);
            userGames.add(gameEntity.get());
            userEntity.get().setFavorites(userGames);
            repository.save(userEntity.get());
        }
    }

    public void removeGameFromFav(Long userId, Long gameId) {
        Optional<UserEntity> userEntity = repository.findById(userId);
        Optional<GameEntity> gameEntity = gameRepository.findById(gameId);
        if(userEntity.isPresent() && gameEntity.isPresent()) {
            Set<GameEntity> userGames = gameRepository.findByUserId(userId);
            userGames.remove(gameEntity.get());
            userEntity.get().setFavorites(userGames);
            repository.save(userEntity.get());
        }
    }

    public List<GameDTO> getFavorites(Long userId) {
        Optional<UserEntity> userEntity = repository.findById(userId);
        if(userEntity.isPresent()) {
            return gameMapper.mapToDtos(gameRepository.findByUserId(userId));
        } else {
            throw new UsernameNotFoundException("User does not exist");
        }
    }
}

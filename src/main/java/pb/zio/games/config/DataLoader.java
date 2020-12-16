package pb.zio.games.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pb.zio.games.dto.CategoryDTO;
import pb.zio.games.entity.UserEntity;
import pb.zio.games.repository.UserRepository;
import pb.zio.games.service.CategoryService;

import java.time.LocalDateTime;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryService categoryService;

    public void run(ApplicationArguments args) {
        createUser1();
        createUser2();

        createCategory1();
        createCategory2();
        createCategory3();
        createCategory4();

    }

    private void createUser1() {
        UserEntity user = new UserEntity();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setCreationDate(LocalDateTime.now());
        userRepository.save(user);
    }

    private void createUser2() {
        UserEntity user = new UserEntity();
        user.setUsername("user");
        user.setPassword("user1234");
        user.setCreationDate(LocalDateTime.now());
        userRepository.save(user);
    }

    private void createCategory1() {
        categoryService.save(new CategoryDTO(null, "Strzelanki"));
    }

    private void createCategory2() {
        categoryService.save(new CategoryDTO(null, "MMORPG"));
    }

    private void createCategory3() {
        categoryService.save(new CategoryDTO(null, "Wyścigowe"));
    }

    private void createCategory4() {
        categoryService.save(new CategoryDTO(null, "Ekonomiczne"));
    }
}
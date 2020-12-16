package pb.zio.games.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pb.zio.games.dto.GameDTO;
import pb.zio.games.dto.filters.GameFilterDTO;
import pb.zio.games.dto.RateDTO;
import pb.zio.games.entity.GameEntity;
import pb.zio.games.service.GameService;
import pb.zio.games.service.RateService;
import pb.zio.games.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/game")
public class GameController extends AbstractCrudController<GameEntity, GameDTO, GameFilterDTO, Long> {

    private final GameService gameService;
    private final RateService rateService;
    private final UserService userService;

    public GameController(GameService service, RateService rateService, UserService userService) {
        super(service);
        this.gameService = service;
        this.rateService = rateService;
        this.userService = userService;
    }

    @GetMapping(value = "/{id}/rates", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RateDTO>> getRates(@PathVariable Long id) {
        List<RateDTO> rates = rateService.findByGameId(id);
        return new ResponseEntity<>(rates, HttpStatus.OK);
    }

    @GetMapping(value = "/{gameId}/fav/{userId}")
    public ResponseEntity<Void> addGameToFavorites(@PathVariable Long gameId, @PathVariable Long userId) {
        userService.addGameToFav(userId, gameId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{gameId}/fav/{userId}")
    public ResponseEntity<Void> removeGameFromFavorites(@PathVariable Long gameId, @PathVariable Long userId) {
        userService.removeGameFromFav(userId, gameId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

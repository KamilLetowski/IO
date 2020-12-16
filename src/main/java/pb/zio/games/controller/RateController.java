package pb.zio.games.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pb.zio.games.dto.RateDTO;
import pb.zio.games.service.RateService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/rate")
public class RateController {

    @Autowired
    private RateService rateService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RateDTO> create(@Valid @RequestBody RateDTO dto, HttpServletRequest request) throws URISyntaxException {
        RateDTO result = rateService.addRate(dto);
        return ResponseEntity.created(new URI(request.getRequestURI())).body(result);
    }
}

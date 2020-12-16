package pb.zio.games.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pb.zio.games.dto.AbstractDTO;
import pb.zio.games.dto.filters.AbstractFilterDTO;
import pb.zio.games.entity.AbstractEntity;
import pb.zio.games.service.AbstractCrudService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractCrudController<ENTITY extends AbstractEntity<ID>,
        DTO extends AbstractDTO, FILTER extends AbstractFilterDTO, ID extends Serializable> {

    protected final AbstractCrudService<ENTITY, DTO, FILTER, ID> service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DTO> create(@Valid @RequestBody DTO dto, HttpServletRequest request) throws URISyntaxException {
        DTO result = service.save(dto);
        return ResponseEntity.created(new URI(request.getRequestURI())).body(result);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DTO> update(@Valid @RequestBody DTO dto) {
        DTO result = service.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DTO> getOne(@PathVariable ID id) {
        Optional<DTO> result = service.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DTO>> getPage(@RequestParam(defaultValue = "0") Integer page,
                                             @RequestParam(defaultValue = "10") Integer size,
                                             @RequestBody FILTER filter) {
        Page<DTO> result = service.findAll(filter, page, size);
        return new ResponseEntity<>(result.getContent(), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DTO>> getAll() {
        List<DTO> result = service.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.model.Code;

import java.time.LocalDateTime;
import java.util.*;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class ApiController {

    private final CodeService service;

    @Autowired
    public ApiController(CodeService service) {
        this.service = service;
    }






    @GetMapping("/api/code/latest")
    public List<Code> getLatest() {
        return service.getLatestById();
    }
















    @GetMapping("/api/code/{id}")
    public ResponseEntity<Code> getById(@PathVariable String id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(service.getByUUID(id));
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<Map> createNew(@RequestBody Code accepted) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);

        accepted.setDate(LocalDateTime.now());
        accepted.setUUID(UUID.randomUUID().toString());
        if (accepted.getTime() > 0) {
            accepted.setTimeRestrict(true);
        }
        if (accepted.getViews() > 0) {
            accepted.setViewsRestrict(true);
        }
        service.add(accepted);

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(Collections.singletonMap("id", "" + accepted.getUUID()));
    }

}

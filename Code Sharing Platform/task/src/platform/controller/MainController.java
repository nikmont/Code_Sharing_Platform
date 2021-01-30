package platform.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import platform.model.Code;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.TEXT_HTML_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class MainController {

    private Code codeSnip = new Code();


    @GetMapping("/code")
    public ResponseEntity<String> getCode() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(CONTENT_TYPE, TEXT_HTML_VALUE);
        String htmlFileContent = "";
        try {
            File resource = new ClassPathResource("static/snippet.html").getFile();
            htmlFileContent = new String(Files.readAllBytes(resource.toPath()));
            htmlFileContent = htmlFileContent.replace("{date}", codeSnip.getDate());
            htmlFileContent = htmlFileContent.replace("{code_snippet}", codeSnip.getCode());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(htmlFileContent);
    }

    @GetMapping("/code/new")
    public ResponseEntity<String> postCode() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(CONTENT_TYPE, TEXT_HTML_VALUE);
        String htmlFileContent = "";
        try {
            File resource = new ClassPathResource("static/newcode.html").getFile();
            htmlFileContent = new String(Files.readAllBytes(resource.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(htmlFileContent);
    }

    @GetMapping("/api/code")
    public ResponseEntity<Code> getContent() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(codeSnip);
    }

    @PostMapping("/api/code/new")
    public String  createNew(@RequestBody Code code) {
//        HttpHeaders responseHeaders = new HttpHeaders();
//        responseHeaders.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);

        codeSnip.setCode(code.getCode());
        codeSnip.setDate(LocalDateTime.now());

        return "{}";
//        return ResponseEntity.ok()
//                .headers(responseHeaders).build();
    }

}

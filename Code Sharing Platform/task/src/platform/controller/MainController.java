package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.TEXT_HTML_VALUE;

@Controller
public class MainController {

    private final CodeService service;

    @Autowired
    public MainController(CodeService codeservice) {
        this.service = codeservice;
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

    @GetMapping("/code/{id}")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("code", service.getByUUID(id));
        return "snippet";
    }

    @GetMapping("/code/latest")
    public String getLatestPage(Model model) {
        model.addAttribute("codes", service.getLatestWithoutRestrict());
        return "latest";
    }

}

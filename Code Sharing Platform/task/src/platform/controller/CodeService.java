package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import platform.model.Code;
import platform.repo.CodeRepository;
import java.util.List;
import java.util.Optional;

@Service
public class CodeService {

    private final CodeRepository repository;

    @Autowired
    public CodeService(CodeRepository repository) {
        this.repository = repository;
    }

    public void add(Code code) {
        repository.save(code);
    }

    public List <Code> getLatestById() { //только по условиям просмотров и времени
        return repository.findTop10ByOrderByIdDesc();
    }

    public Code getByUUID(String id) {

        Optional<Code> code = repository.findCodeByUUID(id);

        return code.orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Invalid UUID"
                ));
    }

}

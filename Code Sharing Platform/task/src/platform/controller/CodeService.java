package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public List <Code> getLatestById() {
        return repository.findTop10ByOrderByIdDesc();
    }

    public Code getById(long id) {

        Optional<Code> code = repository.findById(id);

        return code.orElseThrow(
                () -> new IllegalStateException("Code snippet not found"));
    }

}

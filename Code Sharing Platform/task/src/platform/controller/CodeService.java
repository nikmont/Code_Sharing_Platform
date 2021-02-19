package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.dto.CodeDTO;
import platform.errors.CodeNotFoundException;
import platform.model.Code;
import platform.repo.CodeRepository;

import java.time.Duration;
import java.time.LocalDateTime;
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

    public CodeDTO getByUUID(String id) {

        Optional<Code> code = repository.findCodeByUUID(id);

        if (code.isPresent()) {
            Code temp = code.get();
            if (!temp.isViewsRestrict() && !temp.isTimeRestrict()) {
                return new CodeDTO(temp.getCode(), temp.getDate(), 0L, 0, false, false);
            } else if (temp.getViews() > 0 && !temp.isTimeRestrict()) {
                updateViews(temp);
                repository.save(temp);
                return new CodeDTO(temp.getCode(), temp.getDate(), temp.getTime(), temp.getViews() , true, false);
            } else if (getDurationsLeftInSeconds(temp) > 0 && !temp.isViewsRestrict()) {
                return new CodeDTO(temp.getCode(), temp.getDate(), getDurationsLeftInSeconds(temp), temp.getViews() , false, true);
            } else if (temp.getViews() > 0 && getDurationsLeftInSeconds(temp) > 0) {
                updateViews(temp);
                repository.save(temp);
                return new CodeDTO(temp.getCode(), temp.getDate(), getDurationsLeftInSeconds(temp), temp.getViews() , true, true);

            } else throw new CodeNotFoundException("One of the restrictions is applied");
        } else throw new CodeNotFoundException("UUID not found");
    }

    private long getDurationsLeftInSeconds(Code code) {

        long passed = Duration.between(code.getDateTime(), LocalDateTime.now()).toSeconds();
        return code.getTime() - passed;
    }

    private void updateViews(Code code) {

        code.setViews(code.getViews() - 1);

    }

    public List<Code> getLatestWithoutRestrict() {
        return repository.findTop10ByViewsRestrictFalseAndTimeRestrictFalseOrderByIdDesc();
    }

}

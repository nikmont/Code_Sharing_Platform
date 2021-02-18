package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import platform.errors.CodeNotFoundException;
import platform.model.Code;
import platform.repo.CodeRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
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

    public Code getByUUID(String id) {
        Optional<Code> code = repository.findCodeByUUID(id); //получаем по айди
        Code temp;

        if (code.isPresent()) {
            temp = code.get();
            if (!temp.isViewsRestrict() && !temp.isTimeRestrict()) { //если без ограничений сразу отдаем
                temp.setTime(0L);
                temp.setViews(0);
                return temp;
            } else if (temp.getTime() > 1 && temp.getViews() > 1) { //проверяем --мб надо будет просто до нуля отнимать?
                        updateTime(temp); //отнимаем просмотры
                        updateViews(temp); //вычитаем время
                        repository.save(temp);//сохраняем изменения в  бд
                        return temp; //возвращаем обновленный
                } else throw new CodeNotFoundException("One of the restrictions is applied");
        } else throw new CodeNotFoundException("UUID not found");
    }

    private void updateTime(Code code) { //слишком быстро
        LocalDateTime ldt = LocalDateTime.parse(code.getDate(), code.getFormatter());
        long first = ldt.toLocalTime().toSecondOfDay();
        long second = LocalTime.now().toSecondOfDay();
        long diff = second - first;
        code.setTime(code.getTime() - diff);
    }

    private void updateViews(Code code) {
        code.setViews(code.getViews() - 1);
    }















    public List <Code> getLatestById() { //только по условиям просмотров и времени
        return repository.findTop10ByOrderByIdDesc();
//        return repository.findByViewsGreaterThan0AndTimeGreaterThan0Top10ByOrderByIdDesc();
    }



}

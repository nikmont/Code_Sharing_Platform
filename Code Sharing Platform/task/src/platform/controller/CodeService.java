package platform.controller;

import org.springframework.stereotype.Component;
import platform.model.Code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CodeService {

    private List<Code> list = new LinkedList<>();

    public void add(Code code) {
        list.add(code);
    }

    public Code getById(int id) {
        return list.get(--id);
    }

    public List<Code> getLatest() {
        List<Code> latest = new ArrayList<>(list);
        Collections.reverse(latest);

        if (list.size() <= 10) {
            return latest;
        }

        return latest.stream()
                .limit(10)
                .collect(Collectors.toList());
    }

}

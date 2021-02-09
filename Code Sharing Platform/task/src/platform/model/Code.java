package platform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="code")
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    private String code_snippet;
    private LocalDateTime date;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Code() { }

    public Code(String code_snippet, LocalDateTime date) {

        this.code_snippet = code_snippet;
        this.date = date;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code_snippet;
    }

    public void setCode(String code_snippet) {
        this.code_snippet = code_snippet;
    }

    public String getDate() {
        return date.format(FORMATTER);
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}

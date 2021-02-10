package platform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    private Long time;
    private int views;

    @JsonIgnore
    private String UUID;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Code() { }

    public Code(String code_snippet, LocalDateTime date, Long time, int views, String UUID) {
        this.code_snippet = code_snippet;
        this.date = date;
        this.time = time;
        this.views = views;
        this.UUID = UUID;
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

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @JsonIgnore
    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }
}

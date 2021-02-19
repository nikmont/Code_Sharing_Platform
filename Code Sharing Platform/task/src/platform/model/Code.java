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
    private Long time;
    private int views;
    @JsonIgnore
    private boolean viewsRestrict;
    @JsonIgnore
    private boolean timeRestrict;
    @JsonIgnore
    private String UUID;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Code() { }

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

    @JsonIgnore
    public LocalDateTime getDateTime() { return date;}

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public boolean isViewsRestrict() {
        return viewsRestrict;
    }

    public void setViewsRestrict(boolean viewsRestrict) {
        this.viewsRestrict = viewsRestrict;
    }

    public boolean isTimeRestrict() {
        return timeRestrict;
    }

    public void setTimeRestrict(boolean timeRestrict) {
        this.timeRestrict = timeRestrict;
    }
}

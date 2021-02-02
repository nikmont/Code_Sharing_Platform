package platform.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Code {

    private String code_snippet;
    private LocalDateTime date;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Code(String code_snippet, LocalDateTime date) {
        this.code_snippet = code_snippet;
        this.date = date;
    }

    public Code() {
        this.code_snippet = "public static void ...";
        this.date = LocalDateTime.parse("2021-01-29T19:47:59.711568900");
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

package platform.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CodeDTO {

    private String code;
    private String date;
    private Long time;
    private int views;
    @JsonIgnore
    private boolean viewsRestrict;
    @JsonIgnore
    private boolean timeRestrict;


    public CodeDTO(String code, String date, Long time, int views, boolean viewsRestrict, boolean timeRestrict) {
        this.code = code;
        this.date = date;
        this.time = time;
        this.views = views;
        this.viewsRestrict = viewsRestrict;
        this.timeRestrict = timeRestrict;
    }

    public String getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }

    public Long getTime() {
        return time;
    }

    public int getViews() {
        return views;
    }

    public boolean isViewsRestrict() {
        return viewsRestrict;
    }

    public boolean isTimeRestrict() {
        return timeRestrict;
    }
}

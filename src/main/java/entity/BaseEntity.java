package entity;

import lombok.Data;

import java.util.Date;

@Data
public abstract class BaseEntity {
    private String creator;
    private Date ctime;
}

package entity;

import lombok.Data;

import java.io.Serializable;


@Data
public class User extends BaseEntity implements Serializable {
    private Integer id;
    private String username;
    private String password;
}

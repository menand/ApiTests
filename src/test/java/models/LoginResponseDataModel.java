package models;

import lombok.Data;

@Data
public class LoginResponseDataModel {
    private int id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
}

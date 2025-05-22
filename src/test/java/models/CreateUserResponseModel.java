package models;

import lombok.Data;

@Data
public class CreateUserResponseModel {
    private String name;
    private String job;
    private String id;
    private String createdAt;
}

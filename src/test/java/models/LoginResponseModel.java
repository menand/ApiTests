package models;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LoginResponseModel {
    private LoginResponseDataModel data;
}

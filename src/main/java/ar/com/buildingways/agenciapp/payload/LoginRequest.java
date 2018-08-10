package ar.com.buildingways.agenciapp.payload;

import javax.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank
    private Long username;

    @NotBlank
    private String password;

    public Long getUsername() {
        return username;
    }

    public void setUsername(Long username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
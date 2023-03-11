package com.econcours.econcoursservice.auth.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Credentials {
    private String username;
    private String password;

    public String getPassword() {
        return password != null ? password : "";
    }

    public String getUsername() {
        return username != null ? username : "";
    }
}

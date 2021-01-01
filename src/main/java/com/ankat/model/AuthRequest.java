package com.ankat.model;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthRequest {

    private String usrUsername;
    private String usrPassword;
    private String usrFullName;
    private String usrRole;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

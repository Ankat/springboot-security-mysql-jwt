package com.ankat.model;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthResponse {

    private Integer responseStatus;
    private String responseMessage;
    private Object responseData;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

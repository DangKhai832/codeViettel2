package com.example.demoViettel2.Entity.Api;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ApiModule {
    public int status;
    public String code;
    public ApiModule(int status, String code) {
        this.status = status;
        this.code = code;
    }
}

package com.softper.userservice.resources.comunications;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public abstract class BaseResponse {
    public String title;
    public String message;
    public Integer status;

    public BaseResponse(String title, String message, Integer status) {
        this.title = title;
        this.message = message;
        this.status = status;
    }
}

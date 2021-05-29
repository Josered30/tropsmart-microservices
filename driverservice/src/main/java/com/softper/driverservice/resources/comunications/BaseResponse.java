package com.softper.driverservice.resources.comunications;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public abstract class BaseResponse/*<T>*/ {
    public String title;
    public String message;
    public Integer status;

    public BaseResponse(String title, String message, Integer status) {
        this.title = title;
        this.message = message;
        this.status = status;
    }
    
    
    /*
    public T resource;
    public List<T> resourceList;

    public BaseResponse(T resource)
    {
        this.resource = resource;
        this.success = true;
        this.message = "Success";
        this.status = 0;
    }

    public BaseResponse(List<T> resource)
    {
        this.resourceList = resource;
        this.success = true;
        this.message = "Success";
        this.status = 0;
    }

    public BaseResponse(String message)
    {
        this.success = false;
        this.message = message;
        this.status = 0;
    }
    */

}

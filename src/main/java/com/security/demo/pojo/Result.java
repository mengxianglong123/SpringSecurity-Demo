package com.security.demo.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result {

    //返回码
    private Integer code;

    //返回消息
    private String message;

    //返回数据
    private Map<String, Object> data = new HashMap<String, Object>();

    public Result(){}

    public Result message(String message){
        this.setMessage(message);
        return this;
    }

    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}

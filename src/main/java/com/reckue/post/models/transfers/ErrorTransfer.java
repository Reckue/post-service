package com.reckue.post.models.transfers;

import lombok.Data;

import java.util.Map;

@Data
public class ErrorTransfer {
    private String message;
    private Integer status;

    public ErrorTransfer(int status, Map<String, Object> errorAttributes) {
        this.setStatus(status);
        this.setMessage((String) errorAttributes.get("message"));
    }

    public ErrorTransfer(String message, Integer status) {
        this.message = message;
        this.status = status;
    }
}

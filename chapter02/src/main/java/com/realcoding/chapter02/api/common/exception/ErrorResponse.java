package com.realcoding.chapter02.api.common.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.realcoding.chapter02.api.common.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String errMsg;

    public String convertToJson() throws JsonProcessingException {
        return JsonUtil.convertObjectToJson(this);
    }
}
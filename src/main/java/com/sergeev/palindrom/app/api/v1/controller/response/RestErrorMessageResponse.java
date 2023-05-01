package com.sergeev.palindrom.app.api.v1.controller.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RestErrorMessageResponse {

    private String message;
    private List<String> errors;

}

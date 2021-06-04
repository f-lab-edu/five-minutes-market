package kr.fiveminutesmarket.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ResponseDto<T> {

    private Integer resultCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public ResponseDto(Integer resultCode) {
        this.resultCode = resultCode;
        this.message = null;
        this.data = null;
    }

    public ResponseDto(Integer resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
        this.data = null;
    }

    public ResponseDto(Integer resultCode, String message, T data) {
        this.resultCode = resultCode;
        this.message = message;
        this.data = data;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}

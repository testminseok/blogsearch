package com.software.blog.search.common.response;

public enum ErrorCode {
    OK("OK", "정상처리되었습니다."),

    SERVER_ERROR("SERVER_ERROR", "서버에러가 발생하였습니다."),
    MSG("MSG", "커스텀 메시지입니다.");

    private final String errCd;
    private final String errMsg;

    ErrorCode(String errCd, String errMsg) {
        this.errCd = errCd;
        this.errMsg = errMsg;
    }

    public String getErrCd() {
        return errCd;
    }

    public String getErrMsg() {
        return errMsg;
    }
}

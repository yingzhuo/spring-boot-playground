package com.github.yingzhuo.playground.include.model;

import java.util.HashMap;

public final class ApiResult extends HashMap<String, Object> {

    public static ApiResult error(String errorCode, String errorMessage) {
        var ar = new ApiResult();
        ar.put("errorCode", errorCode);
        ar.put("errorMessage", errorMessage);
        return ar;
    }

    public static ApiResult of(Object data) {
        var ar = new ApiResult();
        ar.put("errorCode", "000");
        ar.put("data", data);
        return ar;
    }

}

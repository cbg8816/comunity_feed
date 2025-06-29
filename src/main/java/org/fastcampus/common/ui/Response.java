package org.fastcampus.common.ui;

import org.fastcampus.common.domain.exception.ErrorCode;

public record Response<T>(Integer code, String message, T value) {

  public static <T> Response<T> ok(T value){
    return new Response<T>(0, "OK", value);
  }
  public static <T> Response<T> error(ErrorCode error){
    return new Response<T>(error.getCode(), error.getMessage(), null);
  }
}

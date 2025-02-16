package com.dummy.library.exception;

public class KafkaSendException extends RuntimeException {
  public KafkaSendException(String message) {
    super(message);
  }

}

package br.com.pamcary.exception;

public class BusinessException extends Exception {

    public BusinessException() {
    }

    public BusinessException(String msg) {
        super(msg);
    }
}

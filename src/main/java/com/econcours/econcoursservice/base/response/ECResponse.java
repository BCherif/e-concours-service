package com.econcours.econcoursservice.base.response;

import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.function.Function;

import static java.util.Objects.nonNull;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@Data
public class ECResponse<T> implements Serializable {
    private T data;
    private boolean ok;
    private String message;

    private ECResponse(T data, String message, boolean ok) {
        this.data = data;
        this.message = message;
        this.ok = ok;
    }

    public static <E> ECResponse<E> success(E e, String message) {
        return new ECResponse(e, message, true);
    }

    public static <E> ECResponse<E> success(E e) {
        return ECResponse.success(e, null);
    }

    public static <E> ECResponse<E> error(String message) {
        return new ECResponse(null, message, false);
    }

    public ResponseEntity<ECResponse<T>> wrap(Function<ECResponse<T>, ResponseEntity<ECResponse<T>>> successFunc, Function<ECResponse<T>, ResponseEntity<ECResponse<T>>> errorFunc) {
        return isOk() ? nonNull(successFunc) ? successFunc.apply(this) : ok(this) : nonNull(errorFunc) ? errorFunc.apply(this) : badRequest().body(this);
    }

    public ResponseEntity<ECResponse<T>> wrap(Function<ECResponse<T>, ResponseEntity<ECResponse<T>>> successFunc) {
        return wrap(successFunc, null);
    }

    public ResponseEntity<ECResponse<T>> wrap() {
        return wrap(null);
    }

    public boolean isKo() {
        return !isOk();
    }
}

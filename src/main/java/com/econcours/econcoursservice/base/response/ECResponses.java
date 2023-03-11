package com.econcours.econcoursservice.base.response;


public final class ECResponses {

    public static <T> ECResponse<T> notFound() {
        return ECResponse.error("Cette entité n'existe pas");
    }
}

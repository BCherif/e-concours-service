package com.econcours.econcoursservice.auth.provider.jwt;

public class JwtConstants {
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER = "Authorization";
    public static final String SIGN_UP_URL = "/login";
    public static final String COMPETITION_URL = "/competitions/**";
    public static final String ESTABLISHMENT_URL = "/establishments/**";
    public static final String CANDIDACY_URL = "/candidacies/**";
    public static final String TOKEN_KEY = "__ac__";
}

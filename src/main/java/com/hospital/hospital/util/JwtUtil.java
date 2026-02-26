package com.hospital.hospital.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class JwtUtil {

    public static Integer getIdUsuario() {
        Object id = getJwt().getClaim("idUsuario");
        if (id instanceof Integer) return (Integer) id;
        if (id instanceof Long) return ((Long) id).intValue();
        if (id instanceof Number) return ((Number) id).intValue();
        return null;
    }

    public static String getCorreo() {
        return getJwt().getClaim("correo");
    }

    public static String getRol() {
        return getJwt().getClaim("rol");
    }

    private static Jwt getJwt() {
        JwtAuthenticationToken auth =
            (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return (Jwt) auth.getToken();
    }
}
package com.org.moviemail.securityconfig;

import jakarta.servlet.http.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        String jsonResponse = """
            {
              "status": 403,
              "error": "Forbidden",
              "message": "You do not have permission to access this resource.",
              "path": "%s"
            }
            """.formatted(request.getRequestURI());

        response.getWriter().write(jsonResponse);
    }
}


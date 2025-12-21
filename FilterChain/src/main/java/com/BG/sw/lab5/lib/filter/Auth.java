package com.BG.sw.lab5.lib.filter;

import static com.BG.sw.lab5.lib.http.HttpUtil.AUTHORIZATION;
import static com.BG.sw.lab5.lib.http.HttpUtil.UNAUTHORIZED;

import java.util.Optional;

import com.BG.sw.lab5.lib.http.HttpRequest;
import com.BG.sw.lab5.lib.http.HttpResponse;
import com.BG.sw.lab5.lib.security.Authentication;
import com.BG.sw.lab5.lib.security.SecurityContext;
import com.BG.sw.lab5.lib.security.SecurityUtil;

public class Auth implements Filter {
    private static final String AUTHENTICATION_SCHEMA = "Basic";
    
    private static final String username = "Baran";
    private static final String password = "keykeykey";

    @Override
    public void doFilter(HttpRequest request, HttpResponse response, FilterChain chain) throws Exception {
        
        Optional<String> authHeader = request.getHeader(AUTHORIZATION);
        if (authHeader.isEmpty() || !authHeader.get().startsWith(AUTHENTICATION_SCHEMA)) {
            response.setStatus(401, UNAUTHORIZED);
            response.setHeader("WWW-Authenticate", "Basic realm=\"Restricted\"");
            response.getWriter().println("Unauthorized"); 
            return;
        }

        String base64Credentials = authHeader.get().substring(AUTHENTICATION_SCHEMA.length()).trim();
        String credentials = SecurityUtil.decodeToString(base64Credentials);
        String[] values = credentials.split(":", 2);

        String providedUsername = values.length > 0 ? values[0] : "";
        String providedPassword = values.length > 1 ? values[1] : "";

        if (providedUsername.equals(username) && providedPassword.equals(password)) {
            Authentication auth = new Authentication(providedUsername);
            SecurityContext.setAuthentication(auth);

            chain.doFilter(request, response);
            SecurityContext.clear();

        } else {
            response.setStatus(401, UNAUTHORIZED);
            response.setHeader("WWW-Authenticate", "Basic realm=\"Restricted\"");
        }
    }
}

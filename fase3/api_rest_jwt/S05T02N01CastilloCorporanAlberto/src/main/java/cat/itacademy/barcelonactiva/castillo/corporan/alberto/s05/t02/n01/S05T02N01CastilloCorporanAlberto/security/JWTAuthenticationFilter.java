package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.security;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@AllArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        AuthCredentials authCredentials = new AuthCredentials();
        try {

            authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);

        } catch (IOException e) {

            System.err.println(e.getMessage());
        }
        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
                authCredentials.getNombre(),
                authCredentials.getPassword(),
                Collections.emptyList());

        return getAuthenticationManager().authenticate(usernamePAT);

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        String token = tokenUtils.createToken(userDetails.getUsername());
        Map<String, String> tokenResponse = new HashMap<>();
        tokenResponse.put("token", token);
        response.setContentType("application/json");
          new ObjectMapper().writeValue(response.getOutputStream(), tokenResponse);
                 
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter("nombre");
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        return request.getParameter("password");
    }
}

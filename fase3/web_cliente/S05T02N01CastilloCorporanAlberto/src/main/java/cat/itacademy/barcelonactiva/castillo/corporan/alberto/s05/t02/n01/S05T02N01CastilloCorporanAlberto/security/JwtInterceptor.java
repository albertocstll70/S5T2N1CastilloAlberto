package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.security;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtInterceptor implements ClientHttpRequestInterceptor {

    private final HttpServletRequest request;

    public JwtInterceptor(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        String requestPath = request.getURI().getPath();

        // Si la ruta de la solicitud es "/register", ejecutar la solicitud sin
        // modificarla.
        if ("/register".equals(requestPath) && "/login".equals(requestPath)) {
            return execution.execute(request, body);
        }

        // Recupera el token JWT de la sesión del usuario
        String token = (String) this.request.getSession().getAttribute("token");

        if (token == null) {
            throw new RuntimeException("No token found in session");
        }

        // Agrega el token JWT al encabezado Authorization de la solicitud
        request.getHeaders().add("Authorization", "Bearer " + token);

        return execution.execute(request, body);
    }

}

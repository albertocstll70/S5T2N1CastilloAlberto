package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.security;

import java.util.Arrays;
import java.util.LinkedHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.dto.JugadorDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.client.HttpStatusCodeException;

@Service
public class AuthenticationService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${dados.base-url}")
    private String baseUrl;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);

    public String authenticate(String nombre, String password, HttpSession session) {
        try {
            String url = baseUrl+"/login";
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<AuthCredentials> request = new HttpEntity<>(new AuthCredentials(nombre, password), headers);

            RestTemplate loginRestTemplate = new RestTemplate();

            ResponseEntity<Object> response = loginRestTemplate.exchange(url, HttpMethod.POST, request, Object.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                log.info("Response body: " + response.getBody());

                LinkedHashMap<String, Object> responseBody = (LinkedHashMap<String, Object>) response.getBody();
                String token = (String) responseBody.get("token");
                session.setAttribute("Authorization", "Bearer " + token);
                session.setAttribute("token", token);

                return token;
            }
        } catch (HttpStatusCodeException e) {
            log.error("Error while authenticating user", e);
        }
        return null;
    }

    // Obtiene un JugadorDTO para poder utilizar el id para las tiradas
    public ResponseEntity<JugadorDTO> callSecuredService(String nombre) {
        try {
            String url = baseUrl+"/buscar/"+nombre;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(headers);

            return restTemplate.exchange(url , HttpMethod.GET, request,
                    JugadorDTO.class);
        } catch (HttpStatusCodeException e) {
            log.error("Error while calling secured service", e);
            return new ResponseEntity<>(e.getStatusCode());
        }
    }


    
}
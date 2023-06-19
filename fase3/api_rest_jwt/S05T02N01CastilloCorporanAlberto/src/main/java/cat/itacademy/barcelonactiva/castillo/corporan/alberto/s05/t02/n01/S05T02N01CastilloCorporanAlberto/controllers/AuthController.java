package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.controllers;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.config.JugadorConfigMap;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.domain.Jugador;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.dto.JugadorDTO;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.repository.JugadorRepository;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.security.AuthCredentials;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.security.TokenUtils;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.service.UserDetailServiceImpl;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImpl userService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private JugadorConfigMap jugadorConfigMap;

    @Autowired
    private JugadorRepository jugadorRepository;

    @PostMapping("/players/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthCredentials authCredentials) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authCredentials.getNombre(),
                            authCredentials.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = tokenUtils.createToken(authCredentials.getNombre());

            HashMap<String, Optional<JugadorDTO>> responseAuthenticate = new HashMap<String, Optional<JugadorDTO>>();

            Optional<Jugador> jDTO = jugadorRepository.findByNombre(authCredentials.getNombre());

            responseAuthenticate.put(jwt, (Optional<JugadorDTO>) (Optional<?>) jDTO);

            return new ResponseEntity<HashMap<String, Optional<JugadorDTO>>>(responseAuthenticate, HttpStatus.OK);

        } catch (AuthenticationException e) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/players/register")
    public ResponseEntity<?> registerUser(@RequestBody AuthCredentials registerRequest) {

        Jugador jugador = userService.registerUser(registerRequest.getNombre(), registerRequest.getPassword());

        JugadorDTO jDTO = jugadorConfigMap.convertToDto(jugador);

        return new ResponseEntity<JugadorDTO>(jDTO, HttpStatus.OK);
    }
}

package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.domain.Jugador;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.exception.UserAlreadyExistsException;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.repository.JugadorRepository;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.security.UserDetailsImpl;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private JugadorRepository jugadorRepository;
    
   
    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {

        Jugador jugador = jugadorRepository
                .findByNombre(nombre)
                .orElseThrow(() -> new UsernameNotFoundException("El Jugador con " + nombre + " no existe"));
        return new UserDetailsImpl(jugador);
    }
    
    public Jugador registerUser(String nombre, String password) {
        
        if (jugadorRepository.findByNombre(nombre).isPresent()) {
            throw new UserAlreadyExistsException("El jugador con el nombre " + nombre + " ya existe");
        }

       
        Jugador newJugador = new Jugador();
        if (password == null) {
            throw new IllegalArgumentException("La contraseña no puede ser nula");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        password = passwordEncoder.encode(password);
        newJugador.setNombre(nombre);
        newJugador.setPassword(password); 
        return jugadorRepository.save(newJugador);
    }
}


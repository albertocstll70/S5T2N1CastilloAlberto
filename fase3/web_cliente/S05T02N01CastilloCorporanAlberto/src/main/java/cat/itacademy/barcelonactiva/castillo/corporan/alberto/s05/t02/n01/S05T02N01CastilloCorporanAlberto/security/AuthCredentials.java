package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.security;

import lombok.Data;

@Data
public class AuthCredentials {
    private String nombre;
    private String password;

    
    public AuthCredentials(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

    
    public AuthCredentials() {
    }


    public String getNombre() {
        return nombre;
    }
    public String getPassword() {
        return password;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void setPassword(String password) {
        this.password = password;
    }

   
}


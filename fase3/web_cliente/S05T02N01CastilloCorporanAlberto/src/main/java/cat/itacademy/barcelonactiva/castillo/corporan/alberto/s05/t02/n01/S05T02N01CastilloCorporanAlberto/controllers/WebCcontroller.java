package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.dto.JugadorDTO;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.security.AuthenticationService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")

public class WebCcontroller {

    @Autowired
    private AuthenticationService authenticationService;

      @ModelAttribute("jugador")
    public JugadorDTO getJugador(HttpSession session) {
        return (JugadorDTO) session.getAttribute("jugador");
    }


    @GetMapping("/players/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("nombre", new String());
        model.addAttribute("password", new String());

        if (error != null) {
            model.addAttribute("error", "Tu nombre de usuario y/o contraseña son incorrectos.");
        }
        return "login";
    }

    @PostMapping(value = "/players/login")
    public String login(@ModelAttribute("nombre") String nombre, @ModelAttribute("password") String password,
            Model model, HttpSession session) throws Exception {

        String authResponse = authenticationService.authenticate(nombre, password, session);

        if (authResponse == null) {

            model.addAttribute("error", "No se pudo autenticar al usuario");
            return "login";
        }

        model.addAttribute("token", authResponse);
        ResponseEntity<?> response = authenticationService.callSecuredService( nombre);
        JugadorDTO jugador = (JugadorDTO) response.getBody();

       // model.addAttribute("jugador", jugador);
        session.setAttribute("jugador", jugador);
        return "jugar";
    }


@GetMapping("players/jugar")
public String mostrarPlantilla(HttpSession session, Model model) {
    String token = (String) session.getAttribute("token");
    if (token != null) {
        JugadorDTO jugador = (JugadorDTO) session.getAttribute("jugador");
        if (jugador != null) {
            model.addAttribute("jugador", jugador);
            return "jugar";
        }
    }
    return "redirect:/players/login";
}



}
package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.service;

import java.util.HashMap;
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
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.domain.Jugador;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.dto.JugadorDTO;

@Service
public class JugadorRestTemplateServiceImpl implements JugadorRestTemplateService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${dados.base-url}")
	private String baseUrl;

	// registrar
	@Override
	public Boolean guardarJugador(Jugador jugador) {
		String url = baseUrl + "/register";
		RestTemplate registerRestTemplate = new RestTemplate();
		ResponseEntity<?> response = registerRestTemplate.postForEntity(url, jugador, Object.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			return true;
		} else {

		}
		return false;
	}

	// update nombre
	@Override
	public JugadorDTO updateJugador(Jugador  jugador) {
		String url = baseUrl;
		for(int i=20;i>0;i--){
		System.out.println(jugador.toString()+"*********************");
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Jugador> request = new HttpEntity<>(jugador ,headers);		
		ResponseEntity<JugadorDTO> response = restTemplate.exchange(url, HttpMethod.PUT, request, JugadorDTO.class);
       if(response.getStatusCode() == HttpStatus.OK){
		return (JugadorDTO) response.getBody();

	   }else {

		return null;
	   }

		

	}

	

	

	@Override
	public JugadorDTO[] jugadores() {
		String url = baseUrl;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(headers);
		ResponseEntity<JugadorDTO[]> response = restTemplate.exchange(url, HttpMethod.GET, request, JugadorDTO[].class);
		return response.getBody();

	}

	//ranking medio
	@Override
	public HashMap<String, Double> rankingMedio() {
		String url = baseUrl+"/ranking";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(headers);

		ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.GET, request, HashMap.class);
		
		 if(response.getStatusCode() == HttpStatus.OK){
		return (HashMap<String, Double>) response.getBody();

	   }else {

		return null;
	   }
		
	}

	//ranking loser
	@Override
	public JugadorDTO rankingLoser() {
		String url = baseUrl+"/ranking/loser";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(headers);

		ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.GET, request, JugadorDTO.class);
		
		 if(response.getStatusCode() == HttpStatus.OK){
		return  (JugadorDTO) response.getBody();

	   }else {

		return null;
	   }
	}

	@Override
	public JugadorDTO rankingWinner() {
		String url = baseUrl+"/ranking/winner";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(headers);

		ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.GET, request, JugadorDTO.class);
		
		 if(response.getStatusCode() == HttpStatus.OK){
		return  (JugadorDTO) response.getBody();

	   }else {

		return null;
	   }
	}

	
}

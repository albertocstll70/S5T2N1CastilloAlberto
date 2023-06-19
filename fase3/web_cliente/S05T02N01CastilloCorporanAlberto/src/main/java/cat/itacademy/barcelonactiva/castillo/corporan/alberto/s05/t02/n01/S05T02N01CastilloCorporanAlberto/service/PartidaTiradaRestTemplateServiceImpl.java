package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.service;

import java.util.List;
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

import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.dto.TiradaDTO;

@Service
public class PartidaTiradaRestTemplateServiceImpl implements PartidaTiradaRestTemplateService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${dados.base-url}")
	private String baseUrl;

	@Override
	public TiradaDTO crearPartidaTirada(String idJugador) {
		String url = baseUrl + "/" + idJugador + "/" + "games";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(headers);
		ResponseEntity<TiradaDTO> response = restTemplate.exchange(url, HttpMethod.POST, request, TiradaDTO.class);
		TiradaDTO tDTO = response.getBody();

		if (tDTO instanceof TiradaDTO) {
			return tDTO;
		} else {
			return null;
		}
	}

	@Override
	public String deleteTiradas(String idJugador) {

		String url = baseUrl + "/" + idJugador + "/" + "games";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(headers);
		ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.DELETE, request, HttpStatus.class);
		if (response.getStatusCode() == HttpStatus.OK) {

       return "Se han borrado las tiradas";
		}else{
			return "Error al  borrar las tiradas";
		}

		

	}

	@Override
	public List<TiradaDTO> listPartidaJugador(String idJugador) {
		String url = baseUrl + "/" + idJugador + "/" + "games";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<?> request = new HttpEntity<>(headers);
		ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, request, List.class);
		List listTiradas =  response.getBody();

		if (response.getStatusCode() == HttpStatus.OK) {
			return listTiradas;
		} else {
			return null;
		}
	}

}

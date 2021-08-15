package org.sid.cinema.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.sid.cinema.dao.FilmRepository;
import org.sid.cinema.dao.TicketRepository;
import org.sid.cinema.entities.Film;
import org.sid.cinema.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RestController
//@CrossOrigin("http://locahost:4200")   //j'autorise à partir de ce URL à m'envoyer des requêtes
@CrossOrigin("*") //ou n'inporte quel domaine peut m'envoyer des requêtes
public class CinemaRestController {
	
	@Autowired
	private FilmRepository  filmRepository;
	@Autowired
	private TicketRepository ticketRepository;
	
	@GetMapping("/listFilms")
	public List<Film> films(){
		return filmRepository.findAll();
	}
	/**
	 * Cette methode permet d'acceder une image à partir de son identifiant du film
	 * MediaType.IMAGE_JPEG_VALUE les données representent une iamage
	 * @param id
	 * @return
	 * @throws IOException 
	 */
	@GetMapping(path="/imageFilm/{id}", produces=MediaType.IMAGE_JPEG_VALUE)
	public byte[] image(@PathVariable(name="id")Long id) throws IOException{
		// Récuperation du film d'abord
		Film f = filmRepository.findById(id).get();
		//ici on connait unique le nom de la photo stocké en base de donnée
		String photoName = f.getPhoto();
		//System.getProperty("user.home") donne le dossier de l'utilisateur
		// où se trouve l'image du film téléchargé
		File file = new File(System.getProperty("user.home")+ "/cinema/images/"+photoName);
		Path path = Paths.get(file.toURI());
		return Files.readAllBytes(path);
		
	}
	
	@PostMapping("/payerTickets")
	@Transactional
	public List<Ticket> payerTickets( @RequestBody TicketFrom ticketFrom){
		// Création d'une liste de tous les tickets vendu
		List<Ticket> listTickets = new ArrayList<>();
		
		// on récupère la liste de tickets du client qui veut acheter
		ticketFrom.getTickets().forEach(idTicket -> {
			Ticket ticket= ticketRepository.findById(idTicket).get();
			ticket.setNomClient(ticketFrom.getNomClient());
			ticket.setReserve(true);
			System.out.println("Code payement :" + ticketFrom.getCodePayement());
 			ticket.setCodePayement(ticketFrom.getCodePayement());
			ticketRepository.save(ticket);
			listTickets.add(ticket);
		});
		
		return listTickets;
	}

}
@Data
class TicketFrom{
	private String nomClient;
	private Integer codePayement;
	private List<Long> tickets = new ArrayList<>();
	
}


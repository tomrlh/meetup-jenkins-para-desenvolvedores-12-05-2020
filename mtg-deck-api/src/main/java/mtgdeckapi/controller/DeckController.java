package mtgdeckapi.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mtgdeckapi.model.Deck;
import mtgdeckapi.repository.DeckRepository;

@RestController
@RequestMapping("/deck")
public class DeckController {
	@Autowired
	DeckRepository deckRepository;
	
	@GetMapping
	public ResponseEntity<List<Deck>> getAll() {
		List<Deck> decks = deckRepository.findAll();
		return !decks.isEmpty() ? ResponseEntity.ok(decks) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Deck> find(@PathVariable Integer id) {
		Optional<Deck> deck = deckRepository.findById(id);
		return !deck.isPresent() ? ResponseEntity.ok(deck.get()) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Deck> create(@Valid @RequestBody Deck deck, HttpServletResponse response) {
		Deck savedDeck = deckRepository.save(deck);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedDeck);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Deck> update(@RequestBody Deck newDeck, @PathVariable Integer id) {
		Deck deckUpdated = deckRepository.findById(id)
		.map(deck -> {
			deck.setName(newDeck.getName());
			deck.setImageUrl(newDeck.getImageUrl());
			return deckRepository.save(deck);
		})
		.orElseGet(() -> {
			newDeck.setId(id);
			return deckRepository.save(newDeck);
		});
		return ResponseEntity.ok(deckUpdated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Optional<Deck> deletedDeck = deckRepository.findById(id);
		if(deletedDeck.isPresent()) {
			deckRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		else
			return ResponseEntity.notFound().build();
	}
}

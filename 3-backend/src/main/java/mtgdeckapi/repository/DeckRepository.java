package mtgdeckapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mtgdeckapi.model.Deck;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Integer> {

}

package tn.esprit.templatespring.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.templatespring.Entities.Conges;
import tn.esprit.templatespring.Entities.DemandeCongeEnAttente;

public interface DemandeCongeEnAttenteRepository extends JpaRepository<DemandeCongeEnAttente, Long> {
    List<DemandeCongeEnAttente> findByEtat(String etat);
    List<DemandeCongeEnAttente> findByUser_IdUser(Long userId);

}
package tn.esprit.templatespring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.templatespring.Entities.Conges;
import tn.esprit.templatespring.Entities.User;
@Repository
public interface CongesCRUD extends JpaRepository <Conges,Long> {
    List<Conges> findByUser_IdUser(Long userId);

}

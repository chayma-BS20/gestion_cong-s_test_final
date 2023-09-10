package tn.esprit.templatespring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.templatespring.Entities.Presence;
@Repository
public interface PresenceCRUD extends JpaRepository <Presence,Long>{

}

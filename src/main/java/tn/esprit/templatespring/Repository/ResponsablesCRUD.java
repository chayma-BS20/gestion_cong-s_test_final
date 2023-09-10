package tn.esprit.templatespring.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.templatespring.Entities.responsables;

@Repository
public interface ResponsablesCRUD extends JpaRepository <responsables,Long>{
	
}

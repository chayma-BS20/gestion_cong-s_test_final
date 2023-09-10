package tn.esprit.templatespring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.templatespring.Entities.StructureHierarchique;
import tn.esprit.templatespring.Entities.User;


@Repository

public interface StructureHierarchiqueCRUD extends JpaRepository <StructureHierarchique,Long> {
	StructureHierarchique findById(long id);

}

package tn.esprit.templatespring.Services;

import java.util.List;
import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import tn.esprit.templatespring.Entities.StructureHierarchique;
import tn.esprit.templatespring.Repository.StructureHierarchiqueCRUD;
@ComponentScan("Repository")


@Service

public class SHService implements ISHService  {
	
	@Autowired
	StructureHierarchiqueCRUD shrepo ;
	
	
	private List<StructureHierarchique> liste = new ArrayList<>();
	

	public List<StructureHierarchique> getListe() {
		return liste;
	}

	public void setListe(List<StructureHierarchique> liste) {
		this.liste = liste;
	}

	
	@Override
	public List<StructureHierarchique> ConsulterSH() {
		return liste = shrepo.findAll();
	}

	@Override
	public StructureHierarchique addSH(StructureHierarchique sh) {
		return shrepo.save(sh);
	}

	@Override
	public StructureHierarchique findSH(Long idSH) {
		return shrepo.getById(idSH);
	}

}

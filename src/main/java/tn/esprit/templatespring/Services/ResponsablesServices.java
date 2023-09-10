package tn.esprit.templatespring.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.templatespring.Entities.responsables;
import tn.esprit.templatespring.Repository.ResponsablesCRUD;

@Service
public class ResponsablesServices implements IResponsablesService{
	@Autowired
	ResponsablesCRUD repo ;
	
	@Override
	public List<responsables> Consulterresponsables() {
		return repo.findAll();
	}

	@Override
	public responsables addResponsables(responsables r) {
		return repo.save(r);

	}

}

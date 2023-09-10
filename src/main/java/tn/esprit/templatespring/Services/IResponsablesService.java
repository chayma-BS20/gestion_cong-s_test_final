package tn.esprit.templatespring.Services;

import java.util.List;

import tn.esprit.templatespring.Entities.responsables;


public interface IResponsablesService {

	   List<responsables> Consulterresponsables();
	    responsables addResponsables(responsables r);
}

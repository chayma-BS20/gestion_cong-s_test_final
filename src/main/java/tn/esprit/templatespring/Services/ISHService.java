package tn.esprit.templatespring.Services;

import java.util.List;

import tn.esprit.templatespring.Entities.StructureHierarchique;
import tn.esprit.templatespring.Entities.User;

public interface ISHService {
	   List<StructureHierarchique> ConsulterSH();
	   StructureHierarchique addSH(StructureHierarchique sh);
	   StructureHierarchique findSH(Long idSH);


}

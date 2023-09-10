package tn.esprit.templatespring.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.templatespring.Entities.Presence;
import tn.esprit.templatespring.Repository.PresenceCRUD;
@Service
public class PresenceService implements IPresenceService {
	@Autowired
	PresenceCRUD prepo ;

	@Override
	public List<Presence> ConsulterPresence() {
		return prepo.findAll();	}

	@Override
	public Presence addPresence(Presence presence) {
		System.out.println ("add service");
		System.out.println (presence.getDatePointage());
		return prepo.save(presence);
	}

}

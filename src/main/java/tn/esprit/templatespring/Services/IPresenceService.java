package tn.esprit.templatespring.Services;

import java.util.List;

import tn.esprit.templatespring.Entities.Presence;

public interface IPresenceService {
	    List<Presence> ConsulterPresence();
	    Presence addPresence(Presence presence);

}

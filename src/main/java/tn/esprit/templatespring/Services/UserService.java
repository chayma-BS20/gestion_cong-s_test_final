package tn.esprit.templatespring.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import tn.esprit.templatespring.Entities.User;
import tn.esprit.templatespring.Repository.UserCRUD;

@Service

public class UserService implements IUserService {
	
  @  Autowired
 private   UserCRUD userrepo ;

	@Override
	public List<User> findAllUser() {
    return userrepo.findAll();
	}
	
	public Optional<User> findUserById(Long idUser) {
		return userrepo.findById(idUser);	
	}
	@Override
	public User updateUser(User user) {
	    User user1 = userrepo.findById(user.getIdUser()).orElse(null);
	    user1.setIdUser(user.getIdUser());
//user1 est le meme user mais user1 est le user changé avec les nouvelles informations , on a utilisé user pour acceder a l'id 
	    user1.setNom(user.getNom());
	    user1.setPrenom(user.getPrenom());
	    user1.setCin(user.getCin());
	    user1.setRole(user.getRole());
	    user1.setNumeroTelephone(user.getNumeroTelephone());
	    user1.setGenre(user.getGenre());
	    user1.setEmail(user.getEmail());
	//    user1.setIdDepartement(user.getIdDepartement());
	//    user1.setIdResponsable(user.getIdResponsable());
	    user1.setDateDeNaissance(user.getDateDeNaissance());
	    user1.setLogin(user.getLogin());
	    user1.setMdp(user.getMdp());
	    user1.setNb_solde(26);
	    return userrepo.save(user1);
	}

	

	 @Override
	    public void removeUser(Long idUser) {
	        userrepo.deleteById( idUser);
	    }




	public User findUser(Long idUser) {
    return userrepo.getById(idUser);	}
	

	@Override
	public User addUser(User user) {
		return userrepo.save(user);
	}
	  public Optional<User> getUserInfo(Long userId) {
	        Optional<User> user = userrepo.findById(userId);
	        
	        // Vérifiez si l'utilisateur existe
	        if (user == null) {
	           System.out.println("Utilisateur non trouvé pour l'ID : " + userId);
	        }

	        return user;
	    }
	}


	


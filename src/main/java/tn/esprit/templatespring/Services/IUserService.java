package tn.esprit.templatespring.Services;

import java.util.List;

import tn.esprit.templatespring.Entities.User;

	public interface IUserService {
		 List<User> findAllUser();
		   // List<User> findUserByRole(String role);
		   // User blockUser(Long idUser);
		   //User unBlockUser(Long idUser);
		    User updateUser (User user);
		    User findUser(Long idUser);
		    void removeUser(Long idUser);
		    User addUser(User user);
	

}

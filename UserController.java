package controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Services.UserService;
import model.User;

@RestController
@RequestMapping("/users")
@ComponentScan("Services")

public class UserController {
	@Autowired
	UserService us ;
   
	@GetMapping
    public List<User> getAllUsers() {
		System.out.println("");
        return us.findAllUser();
    }
//	  @GetMapping("/{id}")
//	    public User getUser(@PathVariable int id) {
//	        return us.findUser(id);
//	    }
//		@PostMapping("/add")
//		
//		public User addUser(@RequestBody User user) {
//			return us.addUser(user);
//			
//		}
	
	@PostMapping("/add")
	public User crUser(@Valid @RequestBody User user) {
		return us.addUser(user);
	}
	
}

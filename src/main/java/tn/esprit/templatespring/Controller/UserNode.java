package tn.esprit.templatespring.Controller;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.templatespring.Entities.User;

public class UserNode {
	 private User u;
	    private List<UserNode> children;
	    @Override
		public String toString() {
			return  u.getNom()  + children ;
		}

		public UserNode(User u) {
	        this.u = u;
	        this.children = new ArrayList<>();
	    }
	    
	    public User getUser() {
	        return u;
	    }
	    
	    public List<UserNode> getChildren() {
	        return children;
	    }
	    
	    public void addChild(UserNode child) {
	        children.add(child);
	    }
	}




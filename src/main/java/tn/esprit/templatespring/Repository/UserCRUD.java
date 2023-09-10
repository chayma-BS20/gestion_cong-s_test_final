package tn.esprit.templatespring.Repository;




import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import tn.esprit.templatespring.Entities.User;

@Repository
public interface UserCRUD extends JpaRepository <User,Long> {
    User findById(long id);


}

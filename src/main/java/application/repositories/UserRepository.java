package application.repositories;

import application.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository {

    public List<User> findAll();
    public User findOne(long id);
    public void saveUser(User user);
    public void update(long id, User updateUser);
    public void deleteUser(long id);
}

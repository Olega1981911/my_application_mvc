package application.services;


import application.models.User;
import application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class UserServices implements ServiceUsers{
    private final UserRepository userRepository;


    @Autowired
    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(long id) {
        return userRepository.findOne(id);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.saveUser(user);
    }

    @Override
    @Transactional
    public void update(long id, User updateUser) {
        userRepository.update(id,updateUser);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteUser(id);
    }
}

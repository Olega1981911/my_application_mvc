package application.services;

import application.models.User;

import java.util.List;

public interface ServiceUsers {
    public List<User> findAll();

    public User findOne(long id);

    public void saveUser(User user);

    public void update(long id, User updateUser);

    public void deleteUser(long id);
}

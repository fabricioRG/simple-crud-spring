package blautech.gt.backend.service;

import blautech.gt.backend.domain.User;

import java.util.List;

public interface UserService {

    public List<User> userList();

    public User save(User user);

    public void delete(User user);

    public User findUserById(String id);

    public List<User> findUserByEmail(User user);

    public List<User> findUserByUsername(User user);

}

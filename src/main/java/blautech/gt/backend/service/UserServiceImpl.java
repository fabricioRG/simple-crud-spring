package blautech.gt.backend.service;

import blautech.gt.backend.domain.User;
import blautech.gt.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> userList() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }


    @Override
    @Transactional(readOnly = true)
    public List<User> findUserByEmail(User user) {
        return userRepository.findUserByEmail(user.getEmail());
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findUserByUsername(User user) {
        return userRepository.findUserByUsername(user.getUsername());
    }


}

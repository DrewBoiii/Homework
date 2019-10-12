package example.drew.homework.service.model;

import example.drew.homework.persistence.dao.UserRepository;
import example.drew.homework.persistence.model.User;
import example.drew.homework.service.dao.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {

    }
}

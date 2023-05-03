package com.example.blogappweek9.Service.ServiceImpl;

import com.example.blogappweek9.DTO.UserDTO;
import com.example.blogappweek9.Enum.Role;
import com.example.blogappweek9.Model.User;
import com.example.blogappweek9.Respositories.UserRepository;
import com.example.blogappweek9.Service.UserService;
import com.example.blogappweek9.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user){
        try{
            User users = userRepository.save(user);
            return users;
        } catch(Exception e){
            throw new RuntimeException(user.getUsername() + " cannot be saved");
        }

    }

    @Override
    public String deleteUser(Long id, Long loggedInUserId){
        User loggedUser = userRepository.findById(loggedInUserId)
                .orElseThrow(() -> new NullPointerException(loggedInUserId + " is not assigned to a user"));
        if(loggedUser.getRole() == Role.USER || loggedUser.isBlocked()){
            return "Unable to perform delete operation. Please contact admin.";
        }
        userRepository.deleteById(id);
        return "user has been deleted successfully";
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User loginUser(UserDTO userDTO) {
        User user= userRepository.findByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword());
        if(user.isBlocked())
            throw new RuntimeException("user is blocked. Access denied");
        else if (user==null)
            throw new UserNotFoundException("user does not exist. Access denied");
         else
            return user;

    }
}

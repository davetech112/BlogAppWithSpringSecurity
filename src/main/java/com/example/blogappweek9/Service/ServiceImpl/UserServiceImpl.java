package com.example.blogappweek9.Service.ServiceImpl;

import org.springframework.transaction.annotation.Transactional;
import com.example.blogappweek9.DTO.UserDTO;
import com.example.blogappweek9.Enum.Role;
import com.example.blogappweek9.Model.UserEntity;
import com.example.blogappweek9.Respositories.TokenRepository;
import com.example.blogappweek9.Respositories.UserRepository;
import com.example.blogappweek9.Service.UserService;
import com.example.blogappweek9.config.SecurityUtil;
import com.example.blogappweek9.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private TokenRepository tokenRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }




    @Override
    public UserEntity saveUser(UserDTO userdto){
        try{
            UserEntity user  = UserEntity.builder()
                    .firstname(userdto.getFirstname())
                    .lastname(userdto.getLastname())
                    .email(userdto.getEmail())
                    .role(userdto.getRole())
                            .build();
            return userRepository.save(user);

        } catch(Exception e){
            throw new RuntimeException(userdto.getEmail() + " cannot be saved");
        }

    }

    @Override
    @Transactional
    public String deleteUser(Long id){
        UserEntity loggedUserEntity = userRepository.findByEmail(SecurityUtil.getSessionUser())
                .orElseThrow(() -> new NullPointerException( " is not assigned to a user"));
        if(loggedUserEntity.getRole() == Role.USER || loggedUserEntity.isBlocked()){
            return "Unable to perform delete operation. Please contact admin.";
        }
        tokenRepository.deleteByUserId(id);
        userRepository.deleteById(id);

        return "user has been deleted successfully";
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }


}

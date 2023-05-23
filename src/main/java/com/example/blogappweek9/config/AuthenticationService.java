package com.example.blogappweek9.config;


import com.example.blogappweek9.Controller.AuthenticationRequest;
import com.example.blogappweek9.Controller.AuthenticationResponse;
import com.example.blogappweek9.Controller.RegisterRequest;
import com.example.blogappweek9.Enum.Role;
import com.example.blogappweek9.Enum.TokenType;
import com.example.blogappweek9.Model.UserEntity;
import com.example.blogappweek9.Respositories.TokenRepository;
import com.example.blogappweek9.Respositories.UserRepository;
import com.example.blogappweek9.token.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = UserEntity.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToke(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToke(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void revokeAllUserTokens(UserEntity user){
        var validUserTokens = tokenRepository.findAllValidTokensByUser(user.getId());
        if(validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
    private void saveUserToke(UserEntity user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }
}

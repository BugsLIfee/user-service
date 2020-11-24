package com.erbf.bugsLife.oauth.controller;

import com.erbf.bugsLife.oauth.exception.BadRequestException;
import com.erbf.bugsLife.oauth.model.AuthProvider;
import com.erbf.bugsLife.oauth.model.Role;
import com.erbf.bugsLife.oauth.model.User;
import com.erbf.bugsLife.oauth.payload.ApiResponse;
import com.erbf.bugsLife.oauth.payload.AuthResponse;
import com.erbf.bugsLife.oauth.payload.LoginRequest;
import com.erbf.bugsLife.oauth.payload.SignUpRequest;
import com.erbf.bugsLife.oauth.repository.UserRepository;
import com.erbf.bugsLife.oauth.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthController {
	//this controller is only for login & sign up 
	//if you want to userInfo  -> move to UserContoller

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        
    	if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException(" �ش� �̸��� ������ �̹� �����մϴ�.");
        }
        User user=User.builder()
        	.name(signUpRequest.getName())
        	.email(signUpRequest.getEmail())
        	.emailVerified(false)
        	.provider(AuthProvider.local)
        	.role(Role.USER)
                .isAttend(false)
                .attenBefore(false)
                .attendCnt(0)
        	.enrollDate(LocalDateTime.now())
        	.password(passwordEncoder.encode(signUpRequest.getPassword()))
        	.build();

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, " ȸ�������� �Ϸ�Ǿ����ϴ� !"));
    }




}

package com.infosat.appRecruitment.controller;


import com.infosat.appRecruitment.model.ERole;
import com.infosat.appRecruitment.model.Role;
import com.infosat.appRecruitment.model.User;
import com.infosat.appRecruitment.payload.request.LoginRequest;
import com.infosat.appRecruitment.payload.request.SignupRequest;
import com.infosat.appRecruitment.payload.response.JwtResponse;
import com.infosat.appRecruitment.payload.response.MessageResponse;
import com.infosat.appRecruitment.repository.RoleRepository;
import com.infosat.appRecruitment.repository.UserRepository;
import com.infosat.appRecruitment.security.jwt.JwtUtils;
import com.infosat.appRecruitment.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        List<Role> roles = new ArrayList<>();

        if (strRoles == null) {
            ERole userRole = ERole.ROLE_USER;
           /* Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            System.out.println("user role" + userRole);
            roles.add(userRole);
            System.out.println("user roles"+ roles);*/
        }/* else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        System.out.println("admin role !" + adminRole);
                        roles.add(adminRole);
                        System.out.println("admin roles" + roles);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        System.out.println("mod role" + modRole);
                        roles.add(modRole);

                        System.out.println("mod roles" + roles);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        System.out.println("default");
                        roles.add(userRole);
                        System.out.println("default roles" + roles);
                }
            });
        }*/

        user.setRoles(roles);
        System.out.println("user before saving " + user.toString());
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
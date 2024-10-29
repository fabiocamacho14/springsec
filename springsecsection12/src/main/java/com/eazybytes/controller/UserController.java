package com.eazybytes.controller;

import com.eazybytes.constants.ApplicationConstants;
import com.eazybytes.model.Customer;
import com.eazybytes.model.LoginRequestDTO;
import com.eazybytes.model.LoginResponseDTO;
import com.eazybytes.repository.CustomerRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private Environment env;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer registerUser(@RequestBody Customer customer) {
        String pwd = passwordEncoder.encode(customer.getPwd());
        customer.setPwd(pwd);
        return customerRepository.save(customer);
    }

    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(authentication.getName());
        return optionalCustomer.orElseThrow(null);
    }

    @PostMapping("/apiLogin")
    public ResponseEntity<LoginResponseDTO> apiLogin(@RequestBody LoginRequestDTO loginRequest) {
        String jwt = "";
        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(),
                loginRequest.password());
        Authentication authenticationResponse = authenticationManager.authenticate(authentication);

        if (authenticationResponse != null && authenticationResponse.isAuthenticated()) {
            if (env != null) {
                String secret = env.getProperty(ApplicationConstants.JWT_SECRET,
                        ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);
                SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
                jwt = Jwts.builder().issuer("Eazy Bank").subject("JWT Token")
                        .claim("username", authenticationResponse.getName())
                        .claim("authorities", authenticationResponse.getAuthorities().stream().map(
                                GrantedAuthority::getAuthority
                        ).collect(Collectors.joining(",")))
                        .issuedAt(new Date())
                        .expiration(new Date(new Date().getTime() + 30000000))
                        .signWith(secretKey)
                        .compact();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).header(ApplicationConstants.JWT_HEADER, jwt).body(new LoginResponseDTO(HttpStatus.OK.getReasonPhrase(), jwt));
    }
}

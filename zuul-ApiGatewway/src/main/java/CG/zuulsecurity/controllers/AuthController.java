package CG.zuulsecurity.controllers;

import static org.springframework.http.ResponseEntity.ok;

import java.util.*;

import CG.zuulsecurity.configs.JwtTokenProvider;
import CG.zuulsecurity.models.User;
import CG.zuulsecurity.models.Wallet;
import CG.zuulsecurity.repositories.UserRepository;
import CG.zuulsecurity.services.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService userService;

    @Autowired
    RestTemplate restTemplate;


    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthBody data) {
        try {
            String username = data.getUsername();
            System.out.println(username);
            try {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.println(
            		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()))
                    		
            		);
            String token = jwtTokenProvider.createToken(username, this.userRepository.findByUsername(username).getRoles());
            Map<Object, Object> model = new HashMap<>();
            User loggedInUser = userRepository.findByUsername(username);
            System.out.println(loggedInUser);
            model = putToModel(loggedInUser,username,token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    private Map<Object,Object> putToModel( User loggedInUser, String username,String token) {
        Map<Object, Object> model = new HashMap<>();
        model.put("token",token);
        model.put("userId",loggedInUser.getUserId());
        model.put("username",loggedInUser.getUsername());
        model.put("roles",loggedInUser.getRoles());
        return model;
    }


    @SuppressWarnings("rawtypes")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user) {
        User userExists = userService.findUserByUsername(user.getUsername());
        if (userExists != null) {
            throw new BadCredentialsException("User with username: " + user.getUsername() + " already exists");
        }
        userService.saveUser(user);
        Wallet wallet = new Wallet();
        wallet.setWalletId(user.getUserId());
        wallet.setUserId(user.getUserId());
        wallet.setBalance(0);
        restTemplateCallWallet(wallet);
        Map<Object, Object> model = new HashMap<>();
        model.put("message", "User registered successfully");
        return ok(model);
    }

    void restTemplateCallWallet(Wallet wallet){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Wallet> entity = new HttpEntity<Wallet>(wallet, headers);
        logger.trace("creating wallet");
        restTemplate.exchange(
                "http://localhost:9000/reservation/user/createWallet", HttpMethod.POST, entity, Wallet.class).getBody();
    }





}
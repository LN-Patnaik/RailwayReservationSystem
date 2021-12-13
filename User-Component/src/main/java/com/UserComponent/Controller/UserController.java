package com.UserComponent.Controller;

import com.UserComponent.Service.UserService;
import com.UserComponent.models.Ticket;
import com.UserComponent.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String displayHelloWorld()
    {
        return "Hello World";
    }

    @PostMapping("/addUser")
   public User addUser(@RequestBody User user)
   {
       User addedUser = userService.addUser(user);
       return addedUser;
   }

   @GetMapping("/users")
   public List<User> getUsers()
   {
       List<User> users = userService.findAllUsers();
       return users;
   }

   @GetMapping("/user/{id}")
   public User getUser(@PathVariable String id)
   {
       User fetchedUser = userService.getUserByUserId(id);
       return fetchedUser;
   }

   @DeleteMapping("/deleteUser/{id}")
   public String deleteUser(@PathVariable String id)
   {
       userService.deleteUserByUserId(id);
       return "user deleted with id" +id;
   }
    @PutMapping("/updateUser")
   public User updateUser(@RequestBody User user)
   {
       User updatedUser = userService.updateUser(user);
       return updatedUser;
   }

    @Autowired
    RestTemplate restTemplate;

    @PostMapping(value = "/user/createTicket")
    public Ticket createTicket(@RequestBody Ticket ticket) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Ticket> entity = new HttpEntity<Ticket>(ticket,headers);

        return restTemplate.exchange(
                "http://localhost:8081/reservation/addTicket", HttpMethod.POST, entity, Ticket.class).getBody();
    }

    @GetMapping(value = "/cancelTicket/{pnrNum}")
    public String cancelTicket(@PathVariable String pnrNum) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        return restTemplate.exchange(
                "http://localhost:8081/reservation/cancelTicket/"+pnrNum, HttpMethod.GET, entity, String.class).getBody();
    }

}
package com.UserComponent.Controller;

import com.UserComponent.Service.UserService;
import com.UserComponent.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
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
}
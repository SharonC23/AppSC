package com.miApp.AppS.controller;

import com.miApp.AppS.dto.UserDTO;
import com.miApp.AppS.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("api/all")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("api/{userId}")
    public ResponseEntity<UserDTO> getUserById(Long userId) {
        UserDTO user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping("api/create")
    public ResponseEntity<UserDTO> createUser(@Validated @RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        return ResponseEntity.ok(userDTO);

    }
    @PutMapping ("api/update/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        UserDTO user = userService.updateUser(userId, userDTO);
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping ("api/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    //Vistas Thymeleaf

    @GetMapping
    public String listUsersView(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "users/list";
    }

    @GetMapping("/users/new")
    public String showCreateForm(Model model){
        model.addAttribute("userDTO", new UserDTO());
        return "users/form";
    }

    @PostMapping("/users/create")
    public String seveUserView(
            @Validated @ModelAttribute("userDTO") UserDTO userDTO,
            BindingResult result,
            RedirectAttributes redirect){
        if(result.hasErrors()){
            return "users/form";
        }
        userService.createUser(userDTO);
        redirect.addFlashAttribute("success", "Usuario creado");
        return  "redirect:/users";
    }



}

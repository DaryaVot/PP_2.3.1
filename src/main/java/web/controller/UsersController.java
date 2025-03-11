package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String show(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("allUsers", users);
        return "users";
    }

    @PostMapping("/add")
    public String add(
            @RequestParam String name,
            @RequestParam String lastName,
            @RequestParam int age) {
        User user = new User(name, lastName, age);
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("/update")
    public String update(@RequestParam long id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            return "redirect:/users";
        }
        model.addAttribute("user", user);
        return "updateUser";
    }

    @PostMapping("/update/")
    public String saveUpdate(@RequestParam long id,
                             @RequestParam String name,
                             @RequestParam String lastName,
                             @RequestParam int age) {
        User user = userService.findById(id);
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        userService.update(user);
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}





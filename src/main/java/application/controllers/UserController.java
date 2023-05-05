package application.controllers;

import application.models.User;
import application.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("users", userServices.findAll());
        return "index";
    }



    @GetMapping("/new")
    public String newPerson(@ModelAttribute("users") User user) {
        return "new";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("users", userServices.findOne(id));
        return "edit";
    }

    @PostMapping()
    public String create(@ModelAttribute("users") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "new";

        userServices.saveUser(user);
        return "redirect:/ ";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("users") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") long id) {
        if (bindingResult.hasErrors())
            return "edit";

        userServices.update(id, user);
        return "redirect:/ ";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userServices.deleteUser(id);
        return "redirect:/ ";
    }
}

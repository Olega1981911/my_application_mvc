package application.controllers;

import application.models.User;
import application.services.ServiceUsersImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class UserController {
    private final ServiceUsersImp serviceUsersImp;

    @Autowired
    public UserController(ServiceUsersImp serviceUsersImp) {
        this.serviceUsersImp = serviceUsersImp;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("users", serviceUsersImp.findAll());
        return "index";
    }



    @GetMapping("/new")
    public String newPerson(@ModelAttribute("users") User user) {
        return "new";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("users", serviceUsersImp.findOne(id));
        return "edit";
    }

    @PostMapping()
    public String create(@ModelAttribute("users") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "new";

        serviceUsersImp.saveUser(user);
        return "redirect:/index";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("users") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") long id) {
        if (bindingResult.hasErrors())
            return "edit";

        serviceUsersImp.update(id, user);
        return "redirect:/index";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        serviceUsersImp.deleteUser(id);
        return "redirect:/index";
    }
}

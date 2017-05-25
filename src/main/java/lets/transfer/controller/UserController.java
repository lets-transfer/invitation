package lets.transfer.controller;

import lets.transfer.domain.user.User;
import lets.transfer.domain.user.UserDto;
import lets.transfer.domain.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("")
    public String viewMember(Model model) {
        model.addAttribute("orders", userService.list());
        return "user/userList";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newMember(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "user/insertEdit";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String saveMember(@ModelAttribute UserDto userDto,
                             RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("result", "saved");
        userService.save(userDto);
        return "redirect:/user";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String editMember(@PathVariable long id, Model model) {
        model.addAttribute("user", userService.get(id));
        return "user/insertEdit";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteMember(@PathVariable long id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("result", "Deleted");
        userService.remove(id);
        return "redirect:/user";
    }
}

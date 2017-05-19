package lets.transfer.controller;

import lets.transfer.domain.membership.MemberShip;
import lets.transfer.domain.membership.MemberShipService;
import lets.transfer.domain.team.Member;
import lets.transfer.domain.team.MemberService;
import lets.transfer.domain.team.TeamService;
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
@RequestMapping("/membership")
public class MemberShipController {

    private MemberShipService membershipservice;

    @Autowired
    public MemberShipController(MemberShipService membershipservice) {
        this.membershipservice = membershipservice;
    }

    @RequestMapping("")
    public String viewMember(Model model) {
        model.addAttribute("membership", membershipservice.list());
        return "membership/memberShipList";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newMember(Model model) {
        model.addAttribute("membership", new MemberShip());
        return "membership/insertEdit";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String saveMember(@ModelAttribute MemberShip membership,
                             RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("result", "saved");
        membershipservice.save(membership);
        return "redirect:/membership";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String editMember(@PathVariable long id, Model model) {
        model.addAttribute("membership", membershipservice.get(id));
        return "membership/insertEdit";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteMember(@PathVariable long id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("result", "Deleted");
        membershipservice.remove(id);
        return "redirect:/membership";
    }
}

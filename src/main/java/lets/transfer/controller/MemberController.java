package lets.transfer.controller;

import lets.transfer.domain.team.Member;
import lets.transfer.domain.team.MemberService;
import lets.transfer.domain.team.Team;
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

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final TeamService teamService;

    @Autowired
    public MemberController(MemberService memberService, TeamService teamService) {

        this.memberService = memberService;
        this.teamService = teamService;
    }

    @RequestMapping("")
    public String viewMember(Model model) {
        model.addAttribute("members", memberService.list());
        return "team/memberList";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newMember(Model model) {
        model.addAttribute("member", new Member());
        return "team/insertEdit";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String saveMember(@ModelAttribute Member member,
                             RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("result", "saved");

        List<Team> teams = teamService.getTeam();
        memberService.save(member,teams);
        return "redirect:/member";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String editMember(@PathVariable long id, Model model) {
        model.addAttribute("member", memberService.get(id));
        return "team/insertEdit";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteMember(@PathVariable long id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("result", "Deleted");

        memberService.remove(id);

        return "redirect:/member";
    }
}

package lets.transfer.controller;

import lets.transfer.domain.team.Team;
import lets.transfer.domain.team.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/team")
public class TeamController {
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {

        this.teamService = teamService;
    }

    @RequestMapping("")
    public String viewIndex(Model model) {
        model.addAttribute("teams", teamService.list());
        return "team/teamList";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newTeam(Model model) {
        model.addAttribute("team", new Team());
        return "team/insertEdit";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String saveTeam(@ModelAttribute Team team,
                               RedirectAttributes redirectAttributes,
                               @RequestParam(value = "file", required = false) MultipartFile file) {

        redirectAttributes.addFlashAttribute("result", "saved");
        teamService.save(team, file);
        return "redirect:/team";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String editTeam(@PathVariable long id, Model model) {
        model.addAttribute("team", teamService.get(id));
        return "team/insertEdit";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteTeam(@PathVariable long id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("result", "Deleted");
        teamService.remove(id);
        return "redirect:/team";
    }
}

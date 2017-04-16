package lets.transfer.controller;

import lets.transfer.domain.memberInfo.MemberInfo;
import lets.transfer.domain.memberInfo.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by HeeyeonNah on 2017. 4. 16..
 */

@Controller
@RequestMapping("/admin")
public class AdminContoroller {
    private final MemberInfoService memberInfoService;

    @Autowired
    public AdminContoroller(MemberInfoService memberInfoService) {
        this.memberInfoService = memberInfoService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String viewIndex(Model model) {
        model.addAttribute("memberInfo", memberInfoService.list());
        return "memberInfo/index";
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.GET)
    public String editMemberInfo(@PathVariable long id, Model model) {
        model.addAttribute("memberInfo", memberInfoService.get(id));
        return "memberInfo/insertEdit";
    }

    @RequestMapping(value ="/new", method = RequestMethod.GET)
    public String newMemberInfo(Model model) {
        model.addAttribute("memberInfo", new MemberInfo());
        return "memberInfo/insertEdit";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String saveMemberInfo(@ModelAttribute MemberInfo memberInfo, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("result", "saved");
        memberInfoService.save(memberInfo);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteMemberInfo(@PathVariable long id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("result", "Deleted");
        memberInfoService.remove(id);
        return "redirect:/admin";
    }
}

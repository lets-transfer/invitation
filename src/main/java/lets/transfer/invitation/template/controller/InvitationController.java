package lets.transfer.invitation.template.controller;

import lets.transfer.invitation.template.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/invitation")
public class InvitationController {

	final InvitationService invitationService;

	@Autowired
	public InvitationController(InvitationService invitationService) {
		this.invitationService = invitationService;
	}

	@RequestMapping(value = "/templates", method = RequestMethod.GET)
	public String viewInvitationTemplates(Model model) {
		model.addAttribute("invitation_templates", invitationService.fetchInvitationTemplates());
		return "invitation/templates";
	}
}

package lets.transfer.invitation.template.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Entity
public class InvitationTemplate {

	@Id @GeneratedValue
	long id;

	String name;

	String description;

	String templatePath;

	String author;

	Date registrationDate;

}

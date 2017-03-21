package lets.transfer.domain.templateList;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class TemplateList {
	@Id @GeneratedValue
	private long id;
	private String name;
	private String value;
}

package lets.transfer.domain.template;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Template {
	@Id @GeneratedValue
	private long id;
	private String name;
	private String explain;
	private String filepath;
	private String owner;
	//private Date date;
}

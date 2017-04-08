package lets.transfer.domain.template;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Template {
	@Id @GeneratedValue
	private long id;
	private String name;
	private String description;
	private String filePath;
	private String owner;
	private Date date;
}

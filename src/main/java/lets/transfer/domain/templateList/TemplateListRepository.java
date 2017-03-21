package lets.transfer.domain.templateList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateListRepository extends JpaRepository<TemplateList, Long>{

}

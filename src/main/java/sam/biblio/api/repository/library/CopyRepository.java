package sam.biblio.api.repository.library;

import org.springframework.data.jpa.repository.JpaRepository;
import sam.biblio.api.model.library.Copy;

public interface CopyRepository extends JpaRepository<Copy, Long> {

}

package sam.biblio.api.repository.library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sam.biblio.api.model.library.Copy;

@Repository
public interface CopyRepository extends JpaRepository<Copy, Long> {

}

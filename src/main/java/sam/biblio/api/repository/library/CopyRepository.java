package sam.biblio.api.repository.library;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import sam.biblio.api.model.library.Copy;
import sam.biblio.api.model.library.CopyStatusEnum;

@RepositoryRestResource
public interface CopyRepository extends JpaRepository<Copy, Long> {

    @RestResource(exported = true)
    Page<Copy> findByDocumentIdAndStatusAndLendingNull(Long documentId, CopyStatusEnum status, Pageable page);


}

package sam.biblio.api.repository.library;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sam.biblio.api.model.library.Document;

@RepositoryRestResource
public interface DocumentRepository extends JpaRepository<Document, Long> {

    @RestResource(exported = true)
    Page<Document> findByAuthorContainingIgnoreCaseOrTitleContainingIgnoreCase(String author, String title, Pageable page);

    @Query(value="SELECT d FROM Document d, Copy c WHERE c.document=d AND c.id=:copyId")
    Document findByCopyId(Long copyId);

}

package sam.biblio.api.repository.library;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import sam.biblio.api.model.library.Document;

@RepositoryRestResource
public interface DocumentRepository extends JpaRepository<Document, Long> {

    Page<Document> findByAuthorContainsOrTitleContains(String author, String title, Pageable page);

    default Page<Document> findByText(String text, Pageable page) {
        return findByAuthorContainsOrTitleContains(text, text, page);
    }

}

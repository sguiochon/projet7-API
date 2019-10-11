package sam.biblio.api.repository.library;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sam.biblio.api.model.library.Document;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    //@Override
    //@EntityGraph(value = "Document.copies", type = EntityGraph.EntityGraphType.FETCH)
    //Optional<Document> findById(Long id);
    Page<Document> findByAuthorContainsOrTitleContains(String author, String title, Pageable page);


    default Page<Document> findByText(String text, Pageable page){
     return findByAuthorContainsOrTitleContains(text, text, page);
    }

}

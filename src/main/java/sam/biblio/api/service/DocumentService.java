package sam.biblio.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sam.biblio.api.model.library.Document;
import sam.biblio.api.repository.library.DocumentRepository;

import java.util.List;

@Service
public class DocumentService {

    private DocumentRepository documentRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository){
        this.documentRepository = documentRepository;
    }

    public List<Document> findAll(){
        return documentRepository.findAll();
    }
/*
    public Document findById(Long id) throws ResourceNotFoundException {
        return documentRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("No document exists having id=" + id));
    }
*/
    public Document findById(Long id) {
        return documentRepository.findById(id).get();
    }

    public Document save(Document newDocument) {
        return null;
    }
}

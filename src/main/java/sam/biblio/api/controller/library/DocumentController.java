package sam.biblio.api.controller.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sam.biblio.api.assembler.library.CopyResourceAssembler;
import sam.biblio.api.assembler.library.DocumentResourceAssembler;
import sam.biblio.api.exception.ResourceNotFoundException;
import sam.biblio.api.model.library.Copy;
import sam.biblio.api.model.library.Document;
import sam.biblio.api.repository.library.DocumentRepository;
import org.springframework.hateoas.Resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

//@RestController
public class DocumentController {

    private DocumentRepository documentRepository;

    private DocumentResourceAssembler documentAssembler;

    private CopyResourceAssembler copyAssembler;

    @Autowired
    public DocumentController(DocumentRepository documentRepository, DocumentResourceAssembler documentAssembler, CopyResourceAssembler copyAssembler){
        this.documentRepository = documentRepository;
        this.documentAssembler = documentAssembler;
        this.copyAssembler = copyAssembler;
    }

    @GetMapping(value = "/documents", produces = {"application/json"})
    public Resources<Resource<Document>> all(@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer pageIndex) {
        List<Resource<Document>> resources = null;
        List<Link> links = new ArrayList<>();
        links.add(linkTo(methodOn(DocumentController.class).all(pageSize, pageIndex)).withSelfRel());
        if (pageSize==null && pageIndex==null) {
            resources = documentRepository.findAll().stream().map(documentAssembler::toResource).collect(Collectors.toList());
        }
        else{
            Page<Document> pageOut = documentRepository.findAll(PageRequest.of(pageIndex, pageSize));
            resources = pageOut.stream().map(documentAssembler::toResource).collect(Collectors.toList());
            if (pageOut.hasPrevious())
                links.add(linkTo(methodOn(DocumentController.class).all(pageSize, pageIndex-1)).withRel("previous"));
            if (pageOut.hasNext())
                links.add(linkTo(methodOn(DocumentController.class).all(pageSize, pageIndex+1)).withRel("next"));
        }
        return new Resources<>(resources, links);
    }


    @GetMapping(value= "/documents/{id}", produces = {"application/json"})
    public Resource<Document> one(@PathVariable Long id) {
        Document document = documentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("document", id));
        document.setCopies(null);
        return documentAssembler.toResource(document);
    }

    @GetMapping(value = "/documents/{id}/copies")
    public Resources<Resource<Copy>> copies(@PathVariable Long id){
        Document document = documentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("document", id));
        List<Resource<Copy>> ressources = document.getCopies().stream().map(copyAssembler :: toResource).collect(Collectors.toList());
        return new Resources<>(ressources);
    }

    @PostMapping("/documents")
    ResponseEntity<?> newEmployee(@RequestBody Document newDocument) throws URISyntaxException {

        Resource<Document> resource = documentAssembler.toResource(documentRepository.save(newDocument));
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @PutMapping("/documents/{id}")
    ResponseEntity<?> replaceEmployee(@RequestBody Document updatedDocument, @PathVariable Long id) throws URISyntaxException {

        Document document = documentRepository.findById(id)
                .map(document1 -> {
                    document1.setTitle(updatedDocument.getTitle());
                    document1.setDescription(updatedDocument.getDescription());
                    document1.setAuthor(updatedDocument.getAuthor());
                    return documentRepository.save(document1);
                })
                .orElseGet(() -> {
                    updatedDocument.setId(id);
                    return documentRepository.save(updatedDocument);
                });
        Resource<Document> resource = documentAssembler.toResource(document);
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @DeleteMapping("/documents/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        documentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

package sam.biblio.api.controller.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.RepositorySearchesResource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.LinkBuilder;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sam.biblio.api.assembler.library.DocumentResourceAssembler;
import sam.biblio.api.model.library.Copy;
import sam.biblio.api.model.library.Document;
import sam.biblio.api.repository.library.DocumentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RepositoryRestController
public class DocumentController {//implements RepresentationModelProcessor<RepositorySearchesResource> {

    private final EntityLinks entityLinks;
    private DocumentRepository documentRepository;
    private DocumentResourceAssembler documentResourceAssembler;

    @Autowired
    public DocumentController(DocumentRepository documentRepository, DocumentResourceAssembler documentResourceAssembler, EntityLinks entityLinks) {
        this.documentRepository = documentRepository;
        this.documentResourceAssembler = documentResourceAssembler;
        this.entityLinks = entityLinks;
    }

    @GetMapping(path = "documents/search/findByText")
    @ResponseBody
    public ResponseEntity<PagedModel<EntityModel<Document>>> findByText(@RequestParam(name = "text", required = true) String text, @RequestParam(name = "size", required = false) Integer pageSize, @RequestParam(name = "page", required = false) Integer pageIndex) {
        List<EntityModel<Document>> resources = null;
        List<Link> links = new ArrayList<>();
        links.add(linkTo(methodOn(DocumentController.class).findByText(text, pageSize, pageIndex)).withSelfRel());
        Page<Document> pageOut = null;
        if (pageSize == null && pageIndex == null) {
            pageOut = documentRepository.findByAuthorContainingIgnoreCaseOrTitleContainingIgnoreCase(text, text, null);
            resources = pageOut.stream().map(documentResourceAssembler::toModel).collect(Collectors.toList());
        } else {
            pageOut = documentRepository.findByAuthorContainingIgnoreCaseOrTitleContainingIgnoreCase(text, text, PageRequest.of(pageIndex, pageSize));
            resources = pageOut.stream().map(documentResourceAssembler::toModel).collect(Collectors.toList());
            if (pageOut.hasPrevious())
                links.add(linkTo(methodOn(DocumentController.class).findByText(text, pageSize, pageIndex - 1)).withRel("previous"));
            if (pageOut.hasNext())
                links.add(linkTo(methodOn(DocumentController.class).findByText(text, pageSize, pageIndex + 1)).withRel("next"));
        }
        PagedModel.PageMetadata pageMetaData = new PagedModel.PageMetadata(pageOut.getSize(), pageOut.getNumber(), pageOut.getTotalElements(), pageOut.getTotalPages());
        return new ResponseEntity<PagedModel<EntityModel<Document>>>(new PagedModel<EntityModel<Document>>(resources, pageMetaData, links), HttpStatus.OK);
    }

    /**
     * Allows adding custom search methods to be listed by Spring Data Rest.
     *
     * @param repositorySearchesResource
     * @return
     */
    //@Override
    public RepositorySearchesResource process(RepositorySearchesResource repositorySearchesResource) {
        if (Document.class.equals(repositorySearchesResource.getDomainType())) {
            LinkBuilder lb = entityLinks.linkFor(Copy.class, "findByText");
            repositorySearchesResource.add(new Link(lb.toString() + "/search/findByText{?text,page,size}", "findByText"));
        }
        return repositorySearchesResource;
    }

}

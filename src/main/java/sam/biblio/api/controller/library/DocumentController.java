package sam.biblio.api.controller.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sam.biblio.api.assembler.library.DocumentResourceAssembler;
import sam.biblio.api.model.library.Document;
import sam.biblio.api.repository.library.DocumentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RepositoryRestController
public class DocumentController {

    private DocumentRepository documentRepository;
    private DocumentResourceAssembler documentAssembler;

    @Autowired
    public DocumentController(DocumentRepository documentRepository, DocumentResourceAssembler documentAssembler) {
        this.documentRepository = documentRepository;
        this.documentAssembler = documentAssembler;
    }

    @GetMapping(path = "/documents/search/findByText")
    @ResponseBody
    public Resources<Resource<Document>> findByText(@RequestParam(name = "text", required = true) String text, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer pageIndex) {
        List<Resource<Document>> resources = null;
        List<Link> links = new ArrayList<>();
        links.add(linkTo(methodOn(DocumentController.class).findByText(text, pageSize, pageIndex)).withSelfRel());
        if (pageSize == null && pageIndex == null) {
            resources = documentRepository.findByAuthorContainsOrTitleContains(text, text, null).stream().map(documentAssembler::toResource).collect(Collectors.toList());
        } else {
            Page<Document> pageOut = documentRepository.findByAuthorContainsOrTitleContains(text, text, PageRequest.of(pageIndex, pageSize));
            resources = pageOut.stream().map(documentAssembler::toResource).collect(Collectors.toList());
            if (pageOut.hasPrevious())
                links.add(linkTo(methodOn(DocumentController.class).findByText(text, pageSize, pageIndex - 1)).withRel("previous"));
            if (pageOut.hasNext())
                links.add(linkTo(methodOn(DocumentController.class).findByText(text, pageSize, pageIndex + 1)).withRel("next"));
        }
        return new Resources<>(resources, links);
    }

}

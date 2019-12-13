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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sam.biblio.api.assembler.library.CopyResourceAssembler;
import sam.biblio.api.model.library.Copy;
import sam.biblio.api.model.library.CopyStatusEnum;
import sam.biblio.api.repository.library.CopyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RepositoryRestController
public class CopyController implements RepresentationModelProcessor<RepositorySearchesResource> {

    @Autowired
    CopyRepository copyRepository;
    @Autowired
    CopyResourceAssembler copyResourceAssembler;
    @Autowired
    private EntityLinks entityLinks;

    @RequestMapping(value = "copies/search/searchFreeCopyOfDocument", method = RequestMethod.GET)
    public ResponseEntity<PagedModel<EntityModel<Copy>>> searchFreeCopyOfDocument(@RequestParam(value = "documentId", required = true) Long documentId, @RequestParam(required = false) Integer size, @RequestParam(required = false) Integer page) {
        List<EntityModel<Copy>> resources = null;
        List<Link> links = new ArrayList<>();
        Page<Copy> pageOut = null;

        if (size == null && page == null) {
            pageOut = copyRepository.findByDocumentIdAndStatusAndLendingNull(documentId, CopyStatusEnum.AVAILABLE, null);
            resources = pageOut.stream().map(copyResourceAssembler::toModel).collect(Collectors.toList());
        } else {
            pageOut = copyRepository.findByDocumentIdAndStatusAndLendingNull(documentId, CopyStatusEnum.AVAILABLE, PageRequest.of(page, size));
            resources = pageOut.stream().map(copyResourceAssembler::toModel).collect(Collectors.toList());
            if (pageOut.hasPrevious())
                links.add(linkTo(methodOn(CopyController.class).searchFreeCopyOfDocument(documentId, size, page - 1)).withRel("previous"));
            if (pageOut.hasNext())
                links.add(linkTo(methodOn(CopyController.class).searchFreeCopyOfDocument(documentId, size, page + 1)).withRel("next"));
        }
        PagedModel.PageMetadata pageMetaData = new PagedModel.PageMetadata(pageOut.getSize(), pageOut.getNumber(), pageOut.getTotalElements(), pageOut.getTotalPages());
        return new ResponseEntity<PagedModel<EntityModel<Copy>>>(new PagedModel<EntityModel<Copy>>(resources, pageMetaData, links), HttpStatus.OK);
    }

    //@Override
    public RepositorySearchesResource process(RepositorySearchesResource repositorySearchesResource) {
        if (Copy.class.equals(repositorySearchesResource.getDomainType())) {
            LinkBuilder lb = entityLinks.linkFor(Copy.class, "freeCopies");
            repositorySearchesResource.add(new Link(lb.toString() + "/search/searchFreeCopyOfDocument{?documentId,page,size}", "searchFreeCopyOfDocument"));
        }
        return repositorySearchesResource;
    }

}

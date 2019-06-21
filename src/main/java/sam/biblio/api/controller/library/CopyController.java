package sam.biblio.api.controller.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sam.biblio.api.assembler.library.CopyResourceAssembler;
import sam.biblio.api.exception.ResourceNotFoundException;
import sam.biblio.api.model.library.Copy;
import sam.biblio.api.repository.library.CopyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

//@RestController
public class CopyController {

    private CopyRepository copyRepository;

    private CopyResourceAssembler copyAssembler;

    @Autowired
    public CopyController(CopyRepository copyRepository, CopyResourceAssembler copyAssembler){
        this.copyRepository = copyRepository;
        this.copyAssembler = copyAssembler;
    }

    @GetMapping(value = "/copies", produces = {"application/json"})
    public Resources<Resource<Copy>> all(@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer pageIndex) {
        List<Resource<Copy>> resources = null;
        List<Link> links = new ArrayList<>();
        links.add(linkTo(methodOn(CopyController.class).all(pageSize, pageIndex)).withSelfRel());

        if (pageSize==null && pageIndex==null) {
            resources = copyRepository.findAll().stream().map(copyAssembler::toResource).collect(Collectors.toList());
        }
        else{
            Page<Copy> pageOut = copyRepository.findAll(PageRequest.of(pageIndex, pageSize));
            resources = pageOut.stream().map(copyAssembler::toResource).collect(Collectors.toList());
            if (pageOut.hasPrevious())
                links.add(linkTo(methodOn(CopyController.class).all(pageSize, pageIndex-1)).withRel("previous"));
            if (pageOut.hasNext())
                links.add(linkTo(methodOn(CopyController.class).all(pageSize, pageIndex+1)).withRel("next"));
        }
        return new Resources<>(resources, links);
    }

    @GetMapping(value= "/copies/{id}", produces = {"application/json"})
    public Resource<Copy> one(@PathVariable Long id) {
        Copy copy = copyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("copy", id));
        return copyAssembler.toResource(copy);
    }
/*
    @GetMapping(value= "/copies/{id}/lending", produces = {"application/json"})
    public Resource<Lending> lending(@PathVariable Long id) {
        Copy copy = copyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("copy", id));
        copy.getLending()
        return copyAssembler.toResource(copy);
    }
*/

}

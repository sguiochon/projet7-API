package sam.biblio.api.controller.security;

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
import sam.biblio.api.assembler.security.UserResourceAssembler;
import sam.biblio.api.controller.library.DocumentController;
import sam.biblio.api.exception.ResourceNotFoundException;
import sam.biblio.api.model.security.User;
import sam.biblio.api.repository.security.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

//@RestController
public class UserController {

    private UserRepository userRepository;
    private UserResourceAssembler userAssembler;

    @Autowired
    public UserController(UserRepository userRepository, UserResourceAssembler userResourceAssembler) {
        this.userRepository = userRepository;
        this.userAssembler = userResourceAssembler;
    }

    @GetMapping(value = "/users", produces = {"application/json"})
    public Resources<Resource<User>> all(@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer pageIndex) {

        List<Resource<User>> resources = null;
        List<Link> links = new ArrayList<>();
        links.add(linkTo(methodOn(UserController.class).all(pageSize, pageIndex)).withSelfRel());

        if (pageSize == null && pageIndex == null) {
            resources = userRepository.findAll().stream().map(userAssembler::toResource).collect(Collectors.toList());
        } else {
            Page<User> pageOut = userRepository.findAll(PageRequest.of(pageIndex, pageSize));
            resources = pageOut.stream().map(userAssembler::toResource).collect(Collectors.toList());
            if (pageOut.hasPrevious())
                links.add(linkTo(methodOn(DocumentController.class).all(pageSize, pageIndex - 1)).withRel("previous"));
            if (pageOut.hasNext())
                links.add(linkTo(methodOn(DocumentController.class).all(pageSize, pageIndex + 1)).withRel("next"));
        }
        return new Resources<>(resources, links);
    }

    @GetMapping(value = "/users/{email}", produces = {"application/json"})
    public Resource<User> one(@PathVariable String email) {
        User user = userRepository.findByEmailIgnoreCase(email).orElseThrow(() -> new ResourceNotFoundException("user", email));
        return userAssembler.toResource(user);
    }

/*
    @GetMapping(value = "/users/{id}", produces = {"application/json"})
    public Resource<User> one(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", id));
        return userAssembler.toResource(user);
    }
*/
}

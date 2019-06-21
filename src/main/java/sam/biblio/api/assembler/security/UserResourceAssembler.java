package sam.biblio.api.assembler.security;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import sam.biblio.api.controller.security.UserController;
import sam.biblio.api.model.security.User;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class UserResourceAssembler implements ResourceAssembler<User, Resource<User>> {
    @Override
    public Resource<User> toResource(User user) {
        return new Resource<>(user);
        /*
        return new Resource<>(user,
                linkTo(methodOn(UserController.class).one(user.getEmail())).withSelfRel(),
                linkTo(methodOn(UserController.class).all(null, null)).withRel("users"));
*/
    }
}

package sam.biblio.api.assembler.library;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import sam.biblio.api.controller.library.DocumentController;
import sam.biblio.api.model.library.Member;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

//@Component
public class MemberResourceAssembler{// implements ResourceAssembler<Member, Resource<Member>> {
    /*@Override
    public Resource<Member> toResource(Member member) {
        return new Resource<>(member,
                linkTo(methodOn(DocumentController.class).one(member.getId())).withSelfRel(),
                linkTo(methodOn(DocumentController.class).all(null, null)).withRel("members"));
    }
    */
}

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
import sam.biblio.api.assembler.library.MemberResourceAssembler;
import sam.biblio.api.exception.ResourceNotFoundException;
import sam.biblio.api.model.library.Document;
import sam.biblio.api.model.library.Member;
import sam.biblio.api.repository.library.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

//@RestController
public class MemberController {
/*
    private MemberRepository memberRepository;

    private MemberResourceAssembler memberAssembler;

    @Autowired
    public MemberController(MemberRepository memberRepository, MemberResourceAssembler memberAssembler){
        this.memberRepository = memberRepository;
        this.memberAssembler = memberAssembler;
    }

    @GetMapping(value = "/members", produces = {"application/json"})
    public Resources<Resource<Member>> all(@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer pageIndex) {
        List<Resource<Member>> resources = null;
        List<Link> links = new ArrayList<>();
        links.add(linkTo(methodOn(DocumentController.class).all(pageSize, pageIndex)).withSelfRel());
        if (pageSize==null && pageIndex==null) {
            resources = memberRepository.findAll().stream().map(memberAssembler::toResource).collect(Collectors.toList());
        }
        else{
            Page<Member> pageOut = memberRepository.findAll(PageRequest.of(pageIndex, pageSize));
            resources = pageOut.stream().map(memberAssembler::toResource).collect(Collectors.toList());
            if (pageOut.hasPrevious())
                links.add(linkTo(methodOn(DocumentController.class).all(pageSize, pageIndex-1)).withRel("previous"));
            if (pageOut.hasNext())
                links.add(linkTo(methodOn(DocumentController.class).all(pageSize, pageIndex+1)).withRel("next"));
        }
        return new Resources<>(resources, links);
    }

    @GetMapping(value= "/members/{id}", produces = {"application/json"})
    public Resource<Member> one(@PathVariable Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("document", id));
        //member.setCopies(null);
        return memberAssembler.toResource(member);
    }
    */
}

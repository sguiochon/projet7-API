package sam.biblio.api.model.library;

import sam.biblio.api.model.UrlIdentifiedRessource;

import javax.persistence.*;

@Entity
public class Copy extends UrlIdentifiedRessource {

    @Enumerated(value = EnumType.STRING)
    private CopyStatus status;
    @ManyToOne(fetch = FetchType.EAGER)
    private Document document;
    @OneToOne(mappedBy = "copy")
    private Lending lending;

    public Lending getLending() {
        return lending;
    }

    public void setLending(Lending lending) {
        this.lending = lending;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CopyStatus getStatus() {
        return status;
    }

    public void setStatus(CopyStatus status) {
        this.status = status;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Copy(String url){
        super("/copies/", url);
    }

    public Copy(){
        super();
    }
}

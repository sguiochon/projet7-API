package sam.biblio.api.model.library;

import javax.persistence.*;

@Entity
public class Copy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;
    @Enumerated(value = EnumType.STRING)
    private CopyStatus status;
    @ManyToOne(fetch = FetchType.EAGER)
    private Document document;
    @OneToOne
    private Lending lending;

    public Lending getLending() {
        return lending;
    }

    public void setLending(Lending lending) {
        this.lending = lending;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
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
}

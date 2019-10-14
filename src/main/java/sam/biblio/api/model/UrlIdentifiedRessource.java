package sam.biblio.api.model;

import javax.persistence.*;


@MappedSuperclass
public abstract class UrlIdentifiedRessource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    protected UrlIdentifiedRessource(String resourcePath, String url){
        int index = url.toLowerCase().lastIndexOf(resourcePath);
        id = Long.valueOf(url.substring(index + resourcePath.length()));
    }

    public UrlIdentifiedRessource() {

    }
}


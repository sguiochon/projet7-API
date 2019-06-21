package sam.biblio.api.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import sam.biblio.api.model.library.Document;
import sam.biblio.api.repository.library.DocumentRepository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
//@WebMvcTest(DocumentController.class)
@AutoConfigureMockMvc
public class DocumentControllerTest {

    private static Logger LOGGER = LoggerFactory.getLogger(DocumentControllerTest.class);

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DocumentRepository documentRepository;
/*
    @TestConfiguration
    static class TestContextConfiguration {
        @Bean
        public DocumentController documentController(DocumentRepository documentRepository, DocumentResourceAssembler documentAssembler, CopyResourceAssembler copyAssembler) {
            return new DocumentController(documentRepository, documentAssembler, copyAssembler);
        }
    }*/

    @Before
    public void setUp() {
        List<Document> documents = new ArrayList<>();
        Document document1  = new Document();
        document1.setId(1L);
        document1.setAuthor("Le pape François");
        document1.setTitle("La vie éternelle");
        documents.add(document1);

        Mockito.when(documentRepository.findAll()).thenReturn(documents);
    }

    @Test
    public void givenNothing_whenGetAllDocuments_thenReturnJsonArray()
            throws Exception {

        mvc.perform(get("/documents")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

    }

}

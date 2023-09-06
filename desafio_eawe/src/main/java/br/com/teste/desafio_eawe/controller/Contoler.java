package br.com.teste.desafio_eawe.controller;

import br.com.teste.desafio_eawe.model.Modelo;
import br.com.teste.desafio_eawe.repositories.JpaRepEawe;
import org.hibernate.boot.cfgxml.spi.CfgXmlAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.Path;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.time.LocalDate;

@RestController
@RequestMapping(path="/api")
public class Contoler  {

@Autowired
    private JpaRepEawe jpaRepEawe;

@PostMapping(path="/gravar{arquivo}")
    public ResponseEntity<Modelo> gravar(@PathVariable String arquivo)  throws ParserConfigurationException, SAXException {
    Modelo modelo = new Modelo();
    try {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(false);
        DocumentBuilder db = dbf.newDocumentBuilder();
        String file = arquivo;

        Document document = db.parse(new File(file));
        document.getDocumentElement().normalize();

        System.out.println("Elemento principal :" + document.getDocumentElement().getNodeName());
        NodeList nList = document.getElementsByTagName("agente");
        for (int i = 0; i < nList.getLength(); i++) {
            Node nxml = nList.item(i);
            Element element = (Element) nxml;
            modelo.setCodigo(Integer.valueOf(element.getAttribute("codigo")));
            modelo.setData(LocalDate.parse(element.getAttribute("data")));
            modelo.setRegiao_sigla(element.getAttribute("regiao_sigla"));

            if (document.getNodeName().equals("geracao"))
                modelo.setGeracao_valor(Double.parseDouble(element.getAttribute("valor")));

            if (document.getNodeName().equals("compra"))
                modelo.setCompra_valor(Double.parseDouble(element.getAttribute("valor")));

            if (document.getNodeName().equals("precoMedio"))
                modelo.setPrecoMedio(Double.parseDouble(element.getAttribute("valor")));
        }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    jpaRepEawe.save(modelo);
    return ResponseEntity.status(HttpStatus.OK).body(modelo);

   }
}

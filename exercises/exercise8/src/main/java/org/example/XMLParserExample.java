package org.example;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

import java.io.File;

public class XMLParserExample {
    public static void main(String... args){
        try{
            File file = new File("exercise8\\src\\main\\resources\\bookshelf.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            System.out.println("Root Node: " + doc.getDocumentElement().getNodeName());
            NodeList bookList = doc.getElementsByTagName("Book");
            for (int i = 0; i < bookList.getLength(); i++) {
                Node book = bookList.item(i);
                String title = book.getAttributes().getNamedItem("title").getTextContent();
                int numberOfPages = Integer.parseInt(book.getAttributes().getNamedItem("numberOfPages").getTextContent());
                int publishedYear = Integer.parseInt(book.getAttributes().getNamedItem("publishedYear").getTextContent());

                // Get the authors of the book
                NodeList authorList = book.getChildNodes();
                for (int j = 0; j < authorList.getLength(); j++) {
                    Node author = authorList.item(j);
                    if (author.getNodeType() == Node.ELEMENT_NODE) {
                        String authorName = author.getTextContent().trim().replaceAll("(?<=\\S)\\s{2,}(?=\\S(?!\\s))", ", ");
                        System.out.println("Book: " + title + "\n number Of Pages: " + numberOfPages + "\n published Year: " +
                                publishedYear + "\n Authors: " + authorName + "\n");
                    }
                }
            }

            // Create a new book element
            Element newBook = doc.createElement("Book");
            Attr title = doc.createAttribute("title");
            title.setTextContent("Life's amazing secrets");
            Attr publishedYear = doc.createAttribute("publishedYear");
            publishedYear.setTextContent("2018");
            Attr numberOfPages = doc.createAttribute("numberOfPages");
            numberOfPages.setTextContent("216");
            Element authors = doc.createElement("authors");
            Element author1 = doc.createElement("author");
            author1.setTextContent("Gaur Gopal Das");
            Element author2 = doc.createElement("author");
            author2.setTextContent("Hari Charan Das");
            authors.appendChild(author1);
            authors.appendChild(author2);
            doc.getElementsByTagName("Book");
            newBook.setAttributeNode(title);
            newBook.setAttributeNode(publishedYear);
            newBook.setAttributeNode(numberOfPages);
            newBook.appendChild(authors);

            // Add the new book to the bookshelf
            Element bookshelf = doc.getDocumentElement();
            bookshelf.appendChild(newBook);

            // Write the updated XML to file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("exercise8\\src\\main\\resources\\bookshelf.xml"));
            transformer.transform(source, result);
        }catch (Exception e){
            System.out.println("Error occurred: " +e.getMessage());
        }
    }
}

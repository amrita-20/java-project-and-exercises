package org.example;

import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.FileReader;
import java.io.FileWriter;

public class JSONParserExample {
    private final JSONParser jsonParser = new JSONParser();
    private JSONObject jsonObject;
    private JSONObject bookshelf;
    private JSONArray books;

    //Create method for reading JSON file
    public void parseJSONFromFile(String filePath) {
        try {
            FileReader file = new FileReader(filePath);
            Object object = jsonParser.parse(file);
            jsonObject = (JSONObject) object;
            bookshelf = (JSONObject) jsonObject.get("Bookshelf");
            books = (JSONArray) bookshelf.get("Book");
            for (Object bookObj : books) {
                JSONObject book = (JSONObject) bookObj;
                String title = (String) book.get("title");
                Long noOfPages = (Long) book.get("numberOfPages");
                Long publishedYear = (Long) book.get("publishedYear");
                JSONArray authors = (JSONArray) book.get("authors");
                StringBuilder sb = new StringBuilder();

                for (Object authorObj : authors) {
                    JSONObject author = (JSONObject) authorObj;
                    String authorName = (String) author.get("author");
                    sb.append(authorName).append(", ");
                }
                sb.setLength(sb.length() - 2);   //remove the trailing comma and space from the stringBuilder
                System.out.println("\n Book Title: " + title + ", Published Year: " + publishedYear + ", Number of Pages: " + noOfPages + "\n Authors: " + sb);
            }
        } catch (Exception e) {
            System.out.println("Error Occurred: " + e.getMessage());
        }
    }

    public void addNewEntry(String filePAth){
        try{
            //Create a new book object if it's not created already
            if(books.size() < 4){
                JSONObject newBook = new JSONObject();
                newBook.put("title", "Life's amazing secrets");
                newBook.put("publishedYear", 2018);
                newBook.put("numberOfPages", 216);
                JSONArray authors = new JSONArray();
                JSONObject author1 = new JSONObject();
                author1.put("author", "Gaur Gopal Das");
                JSONObject author2 = new JSONObject();
                author2.put("author", "Hari Charan Das");
                authors.add(author1);
                authors.add(author2);
                newBook.put("authors", authors);

                // Add the new book to the existing book array
                books.add(newBook);

                // Print the updated JSON
                bookshelf.put("Book", books);
                jsonObject.put("Bookshelf", bookshelf);

                System.out.println(jsonObject.toJSONString());

                // Write the updated JSON to file
                FileWriter fileWriter = new FileWriter(filePAth);
                fileWriter.write(jsonObject.toJSONString());
                fileWriter.close();
            }else{
                System.out.println("New book entry is already added");
            }

        }catch (Exception e){
            System.out.println("Error Occurred: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        /*
            Provide JSON file path
            Create object of JSONParser class
            And then call jsonParser method
         */
        String filaPath = "exercise8\\src\\main\\resources\\bookshelf.json";
        JSONParserExample jsonParserExample = new JSONParserExample();
        jsonParserExample.parseJSONFromFile(filaPath);
        jsonParserExample.addNewEntry(filaPath);
        jsonParserExample.parseJSONFromFile(filaPath);
    }
}

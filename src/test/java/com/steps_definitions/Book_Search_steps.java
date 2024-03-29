package com.steps_definitions;

import com.Utils.BrowserUtils;
import com.Utils.DBUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;

import java.util.ArrayList;
import java.util.List;

import com.testbase.PagesInitializer;

import static com.Utils.BrowserUtils.getText;
import static com.Utils.BrowserUtils.sendText;

public class Book_Search_steps extends PagesInitializer {
    String bookName="";

    @When("user searches for {string} book")
    public void user_searches_for_book(String name) {
        bookName=name;
        sendText( booksPage.searchBooksInput, bookName + Keys.ENTER);
        BrowserUtils.sleep(1);
    }
    @Then("book information must match with the Database")
    public void book_information_must_match_with_the_database() {
        DBUtils.createConnection();

        List<String> bookInfoUI = new ArrayList<>();
        bookInfoUI.add(getText(booksPage.bookName));
        bookInfoUI.add(getText(booksPage.bookAuthor));
        bookInfoUI.add(getText(booksPage.bookYear));

        List<String> bookInfoDB;
        String query = "select name, author, year from books where name='"+bookName+"';";
        DBUtils.runQuery(query);
        bookInfoDB= DBUtils.getRowDataAsList(1);

        System.out.println("bookInfoUI = " + bookInfoUI);
        System.out.println("bookInfoDB = " + bookInfoDB);

        Assert.assertEquals(bookInfoUI, bookInfoDB);
    }
}

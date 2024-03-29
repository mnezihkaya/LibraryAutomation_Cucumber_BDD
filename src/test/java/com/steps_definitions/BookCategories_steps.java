package com.steps_definitions;
import com.Utils.DBUtils;
import com.pages.BooksPage;
import com.testbase.PagesInitializer;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

import static com.Utils.BrowserUtils.dropdownOptionsAsString;

public class BookCategories_steps extends PagesInitializer {
    List<String> bookCategoriesUI;
    List<String> bookCategoriesDB;
    @When("take all book categories in UI")
    public void take_all_book_categories_in_ui() {
        bookCategoriesUI = dropdownOptionsAsString(booksPage.bookCategories);
    }

    @When("execute a query to get book categories")
    public void execute_a_query_to_get_book_categories() {
        DBUtils.createConnection();
        String query = "select name from book_categories;";
        DBUtils.runQuery(query);
        bookCategoriesDB = DBUtils.getColumnDataAsList(1);
    }

    @Then("verify book categories must match the book_categories table from DB")
    public void verify_book_categories_must_match_the_book_categories_table_from_db() {
        bookCategoriesUI.removeIf(p-> p.equals("ALL"));

        Assert.assertEquals(bookCategoriesUI, bookCategoriesDB);

        System.out.println("bookCategoriesUI = " + bookCategoriesUI);
        System.out.println("bookCategoriesDB = " + bookCategoriesDB);
    }
}

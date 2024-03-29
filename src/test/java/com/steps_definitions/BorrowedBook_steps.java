package com.steps_definitions;

import com.Utils.BrowserUtils;
import com.Utils.DBUtils;
import com.testbase.PagesInitializer;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class BorrowedBook_steps extends PagesInitializer {
    String actualBorrowedBooks = "";

    @When("user take borrowed books number")
    public void user_take_borrowed_books_number() {
        actualBorrowedBooks = BrowserUtils.getText(dashboardPage.borrowedBooksNum);
    }

    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {
        DBUtils.createConnection();
        String query = "select count(*)  from book_borrow where is_returned=0;";
        DBUtils.runQuery(query);
        String expectedBorrowedBooks = DBUtils.getCellValue(1, 1);
        System.out.println("actualBorrowedBooks = " + actualBorrowedBooks);
        System.out.println("expectedBorrowedBooks = " + expectedBorrowedBooks);
        Assert.assertEquals(expectedBorrowedBooks, actualBorrowedBooks);
    }

}

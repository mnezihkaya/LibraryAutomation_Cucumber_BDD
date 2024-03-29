package com.steps_definitions;

import com.Utils.DBUtils;
import com.testbase.PagesInitializer;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Collections;
import java.util.List;

public class DB_Information_Check extends PagesInitializer {
    List<String> userIDs;
    List<String> columns;

    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {
        DBUtils.createConnection();
        String query = "select * from users;";
        DBUtils.runQuery(query);
        userIDs = DBUtils.getColumnDataAsList("ID");
    }

    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
        for (String each : userIDs) {
            int freq = Collections.frequency(userIDs, each);
            Assert.assertEquals(1, freq);
        }
    }

    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {
        String query = "select * from users;";
        DBUtils.runQuery(query);
        columns = DBUtils.getAllColumnNamesAsList();
    }

    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(List<String> expectedList) {
        Assert.assertEquals(expectedList, columns);
    }
}

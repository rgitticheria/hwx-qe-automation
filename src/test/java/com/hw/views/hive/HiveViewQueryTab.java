package com.hw.views.hive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ritticheria on 3/4/16.
 */


/*SQl is case sensitive - any database or table oe column created should maintain its case for proper mirroring of the SQL
  environment = hive view is changing everything to lower case*/

public class HiveViewQueryTab {

    String dbName = "ATESTDB1";
    String tbName1 = "testTbl1";
    String ambariAddress = "https://172.22.64.206:8443/#";
    String hiveViewInstance = "HiveViewTest3";
    String userName = "admin";
    String userPassword = "admin";
    String strCurrentDb;

    String selectQuery = String.format("SELECT * FROM %s;",tbName1);
    String createDbQuery = String.format("CREATE DATABASE %s;",dbName);
    String createTablQuery = String.format("CREATE TABLE %s (col1 INT, col2 STRING, col3 INT);",tbName1);
    String insertTablQuery1 = String.format("INSERT INTO %s VALUES (1,'abc',11);",tbName1);
    String insertTablQuery2 = String.format("INSERT INTO %s VALUES (2,'def',12);",tbName1);

    String delTablQuery = String.format("DROP TABLE %s", tbName1);
    String delDbQuery = String.format("DROP DATABASE %s", dbName);


    String ambariHiveViewInstance = String.format("%s/main/views/HIVE/1.0.0/%s",ambariAddress,hiveViewInstance);
    String ambariLogin = String.format("%s/login",ambariAddress);

    WebDriver driver = new FirefoxDriver();
    HiveViewPage hiveView = PageFactory.initElements(driver,HiveViewPage.class);

//    public WebDriver getDriver() { return driver; }

    /**
     * Function to logout of Ambari
     */
    public void logoutAmbari(){ driver.close(); }

    public List<String> getListElementsFromDrop(List<WebElement> elements){
        List<String> strListElements = new ArrayList<String>();
        for (WebElement webElement: elements) {
            strListElements.add(webElement.getAttribute("data-value"));
        }
        return strListElements;
    }

    /**
     * Function to get list of Elements from Tree structure in Database Explorer
     * @param elements
     * @return list of element names
     */
    public List<String> getListElementsFromTree(List<WebElement> elements){
        List<String> strListElements = new ArrayList<String>();
        for (WebElement webElement: elements) {
            strListElements.add(webElement.getText());
        }
        return strListElements;
    }

    /**
     * Function to check if given name matches element in given list
     * @param strDatabaseNamesList
     * @param compareString
     * @return return 0 for match found otherwise return 1
     */
    public int checkValueInList(List<String> strDatabaseNamesList,String compareString){
        for (String str: strDatabaseNamesList) {
            if (str.compareToIgnoreCase(compareString) == 0) {
                return 0;
            }
        }
        return 1;
    }


    /**
     * Function to navigate to Query tab of Hive View Instance
     * @throws InterruptedException
     */
    public void navigateToHiveQueryTab() throws InterruptedException{
        driver.manage().window().maximize();
        driver.get(ambariLogin);
        LoginPage login = PageFactory.initElements(driver,LoginPage.class);
        login.loginAmbari(userName,userPassword);
        Thread.sleep(10000);
        driver.get(ambariHiveViewInstance);
        Thread.sleep(15000);
        driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
        hiveView.selectQueryTab();
    }

    /**
     * Function to create database and make it current database
     * @throws InterruptedException
     */
    public void createDatabaseOperation() throws InterruptedException{
        hiveView.submitExecuteQuery(createDbQuery,driver);
        System.out.println("Create db query submitted");
        hiveView.refreshDatabases();
        Thread.sleep(2000);
        hiveView.selectDbDropList(dbName);
        System.out.println(dbName + " database selected");
    }

    /**
     * Function to create table in current database
     */
    public void createTableOperation(){
        hiveView.submitExecuteQuery(createTablQuery,driver);
        System.out.println("Create table query submitted");
        hiveView.refreshDatabases();

    }

    /**
     * Function to delete database
     */
    public void deleteDatabaseOperation(){
        hiveView.submitExecuteQuery(delDbQuery,driver);
        hiveView.refreshDatabases();

    }

    /**
     * Function to delete table
     */
    public void deleteTableOperation(){
        hiveView.submitExecuteQuery(delTablQuery,driver);
        hiveView.refreshDatabases();

    }

/**
 ********************* TESTS START HERE ***********************
 **************************************************************
 */

    /** FT01
     Default database should be for all newly created hive view instances
     1. Login to Ambari
     2. Goto Hive View Instance
     3. Check if 'default' database exists
     4. Log out of Ambari
    */
    @Test
    public void checkForDefaultDb() throws InterruptedException{
        navigateToHiveQueryTab();

        strCurrentDb = hiveView.getCurrentDatabase();
        if(strCurrentDb.compareToIgnoreCase("Default")==0)
            System.out.println("Default database exists !");
        else{
            if(hiveView.findDatabaseInTree("Default") == 0)
                System.out.println("Default database exists !");
            else
                System.out.println("Default database does not exist !");
        }
        logoutAmbari();
    }




    /** FT02 & 03
     Create new Database and Table and view them in Database explorer
     1. Login to Ambari
     2. Goto Hive View Instance
     3. Create a new database 'ATESTDB1'
     4. Refresh databases and check if the new database is visible in Database Explorer
     5. Select the new database and create a new table
     6. Refresh databases and check if the new table is visible in Database Explorer
     7. List out the columns of the table from Database Explorer
     8. Log out of Ambari
     TODO: Rename, ADD/Drop columns
     */
    @Test
    public void checkDbOperations() throws InterruptedException{
        List<String> strDatabaseNamesList;
        List<String> strColumnsList;
        navigateToHiveQueryTab();
        Thread.sleep(5000);
        hiveView.selectQueryTab();

        createDatabaseOperation();
        Thread.sleep(2000);

        strDatabaseNamesList = getListElementsFromTree(hiveView.getDatabaseTreeList());
        if(checkValueInList(strDatabaseNamesList,dbName)==0){
                System.out.println("New database " + dbName + " is created successfully and displayed in tree structure");
        }
//        else
//            System.out.print("");  //================================================>>>>
        strDatabaseNamesList = getListElementsFromDrop(hiveView.getDatabaseNamesList());
        if(checkValueInList(strDatabaseNamesList,dbName)==0) {
            System.out.println("New database " + dbName + " is created successfully");
            hiveView.selectDbDropList(dbName);
            System.out.println(dbName + " database selected");
            hiveView.submitExecuteQuery(createTablQuery,driver);
            hiveView.refreshDatabases();
            Thread.sleep(2000);
            if(hiveView.selectDbTreeList(dbName) == 0){
                if(hiveView.selectTableTreeList(tbName1) == 0) {
                    System.out.println(tbName1 + " created successfully");
                    strColumnsList = hiveView.getColumnNames();
                    for (String column : strColumnsList) {
                        System.out.println(column);
                    }
                }
            }

            createTableOperation();
            Thread.sleep(2000);
            deleteTableOperation();
            Thread.sleep(2000);
            hiveView.selectDbTreeList(dbName);
            if(hiveView.selectDbTreeList(dbName) == 0){
                if(hiveView.selectTableTreeList(tbName1) == 1)
                    System.out.println(tbName1 + "successfully deleted");
            }

            deleteDatabaseOperation();
            Thread.sleep(2000);
            hiveView.selectDbTreeList(dbName);
            if(hiveView.selectDbTreeList(dbName) == 1)
                System.out.println(dbName + "successfully deleted");
        }
        else
            System.out.println("New database " + dbName + " is NOT created successfully");
        logoutAmbari();
    }




    /** FT04
     Validate Database and Table icons - list out first 100 records for selected table
     1. Login to Ambari
     2. Goto Hive View Instance
     3. Create a new database 'ATESTDB1'
     4. Refresh databases and check if the new database is visible in Database Explorer
     5. Select the new database and create a new table
     6. Refresh databases and check if the new table is visible in Database Explorer
     7. List out the columns of the table from Database Explorer
     8. Log out of Ambari
     */
    @Test
    public void validateDatabaseTableIcons() throws InterruptedException{
        List<String> strDatabaseNamesList;
        String getTextFromEditor;
        navigateToHiveQueryTab();
        hiveView.selectQueryTab();

        createDatabaseOperation();
        Thread.sleep(2000);
        createTableOperation();
        Thread.sleep(2000);
        strDatabaseNamesList = getListElementsFromTree(hiveView.getDatabaseTreeList());
        if(checkValueInList(strDatabaseNamesList,dbName)==0){
            hiveView.clickIconDatabase();
            System.out.println("Clicking on Database icon opens its list of tables");
            hiveView.clickIconTblList();
            getTextFromEditor = hiveView.readFromQueryEditor();
            System.out.println("After clicking on table icon this query is printed in editor '" + getTextFromEditor + "'");
        }
        //TODO read first trwo rows of select results
        deleteTableOperation();
        Thread.sleep(2000);
        deleteDatabaseOperation();
        Thread.sleep(2000);
        logoutAmbari();
    }




    /**FT07 & 08
     * Search for Tables and columns for a database and display search results
     * 1. Create Database an table
     * 2. Click on Search tables input box and enter Table name to search
     * 3. View search results
     * 4. Click on Search Columns input box and enter Column Name to search
     * 5. View Search Results
     * 6. Delete created table and database
     * 7. Logout of Ambari
     */
    @Test
    public void searchOperationOnTablesColumns() throws InterruptedException{
        List<String> searchResults;
        navigateToHiveQueryTab();
        Thread.sleep(2000);

        hiveView.selectQueryTab();
        createDatabaseOperation();
        Thread.sleep(2000);
        createTableOperation();
        Thread.sleep(2000);

        hiveView.fnSearchTable(tbName1);
        searchResults = hiveView.getSearchResultsTables();
        System.out.println("Search Results for '" + tbName1 + "':");
        for (String tablename: searchResults) {
            System.out.println(tablename);
        }
        Thread.sleep(2000);
        hiveView.fnSearchColumn("col");
        System.out.println("Search Results for 'col':");
        searchResults = hiveView.getSearchResultsColumns();
        for (String tablename: searchResults) {
            System.out.println(tablename);
        }
        Thread.sleep(2000);

        deleteTableOperation();
        Thread.sleep(2000);
        deleteDatabaseOperation();
        Thread.sleep(2000);
        logoutAmbari();
    }




    /**
     *
     */
    @Test
    public void foo() throws InterruptedException{

        String batchQuery = createDbQuery + "\n" + createTablQuery + "\n" + insertTablQuery1 +
                "\n" + insertTablQuery2 + "\n" + selectQuery;

        navigateToHiveQueryTab();
        Thread.sleep(2000);
        hiveView.selectQueryTab();
        hiveView.submitExecuteQuery(batchQuery,driver);
        System.out.println("Executed Bulk Query \n" + batchQuery + "\n Executed successfully");

        String st = hiveView.getSearchResults();
        System.out.println("\n " + st + " \n\n");

        deleteTableOperation();
        Thread.sleep(2000);
        deleteDatabaseOperation();
        Thread.sleep(2000);
        logoutAmbari();
//        uytredwertyuiop9tyrew
    }

}

/*
CREATE DATABASE ATESTDB1;
CREATE TABLE testTbl1 (col1 INT, col2 STRING, col3 INT);
INSERT INTO testTbl1 VALUES (1,'abc',11);
INSERT INTO testTbl1 VALUES (2,'def',12);
SELECT * FROM testTbl1;
* */

/**
 * Check wait till object function by looking into previous test scripts
 * check the insert queries - multi inserts throwing socket issue
 * start reading log values as well
 *
 */

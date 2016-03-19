package com.hw.views.hive;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ritticheria on 3/1/16.
 */
public class HiveViewPage {

//    ================================= Query Tab ========================================

    @FindBy(how = How.CSS, using="ul.navbar-nav li:nth-child(1)")   // Query Tab
    private WebElement tabQuery;
    @FindBy(how = How.CSS, using="ul.navbar-nav li:nth-child(2)")   // Saved Queries Tab
    private WebElement tabSavedQueries;
    @FindBy(how = How.CSS, using="ul.navbar-nav li:nth-child(3)")   // History Tab
    private WebElement tabHistory;
    @FindBy(how = How.CSS, using="ul.navbar-nav li:nth-child(4)")   // UDF Tab
    private WebElement tabUDF;
    @FindBy(how = How.CSS, using="ul.navbar-nav li:nth-child(5)")   // Upload Table Tab
    private WebElement tabUploadTable;

    @FindBy(how = How.CSS, using="i.fa-refresh")                //Refresh Databases
    private WebElement btnRefreshDb;
    @FindBy(how = How.CSS, using="div.item")                    //Select Current Database
    private WebElement strCurrentDb;

    @FindAll(value = {@FindBy(how = How.CSS, using="div.selectize-dropdown-content div")})      //List of all dbs in drop list
    private List<WebElement> createdDbDropList;
    @FindAll(value = {@FindBy(how = How.CSS, using = "div.tables collapsible")})              // List of tables in the tree structure
    private List<WebElement> tablesInDbList;
    @FindAll(value = {@FindBy(how = How.CSS, using = "div.columns div.column-name")})          // List of columns in  the tree structure
    private List<WebElement> columnsInTableList;
    @FindAll(value = {@FindBy(how = How.CSS, using = "panel.database-explorer input.ember-text-field")})              // Get the inputs for search tables and columns
    private List<WebElement> searchElementsList;

    @FindBy(how = How.CSS, using = "a.fa-database")
    private WebElement iconDatabase;
    @FindBy(how = How.CSS, using="a.fa-list")                   // list 100 records of table icon
    private WebElement iconTblList;

    @FindAll(value = {@FindBy(how = How.CSS, using = "div.tables a")})
    private List<WebElement> foundTablesList;
    @FindAll(value = {@FindBy(how = How.CSS, using = "div.columns div")})
    private List<WebElement> foundColumnsList;
    @FindAll(value = {@FindBy(how = How.CSS, using="div.databases collapsible")})      //List of all dbs in tree structure
    private List<WebElement> createdDbTreeList;
    @FindBy(how = How.CSS, using = "textarea#code-mirror")
    private WebElement txtareaQueryEditor;

    @FindBy(how = How.CSS, using="button.execute-query")        // Execute
    private WebElement btnExecute;
    @FindBy(how = How.CSS, using="button.btn-default")          // Explain
    private WebElement btnExplain;
    @FindBy(how = How.CSS, using="button.save-query-as")        //Save as
    private WebElement btnSaveAs;
    @FindBy(how = How.CSS, using="button.pull-right")           //New Worksheet
    private WebElement btnNewSheet;

    @FindBy(how = How.CSS, using = "div.CodeMirror-code")
    private WebElement strFromQueryEditor;
    @FindAll(value = {@FindBy(how = How.CSS, using="panel.query-editor-panel li")})      //List of all open worksheets
    private List<WebElement> openWkstList;

    @FindBy(how = How.CSS, using="div.query-menu span:nth-child(1)")             //SQL
    private WebElement tabSQL;
    @FindBy(how = How.CSS, using="div.query-menu span:nth-child(2)")          //Settings
    private WebElement tabSettings;
    @FindBy(how = How.CSS, using="div.query-menu span:nth-child(3)")     //Visualization
    private WebElement tabVisualization;
    @FindBy(how = How.CSS, using="div.query-menu span:nth-child(4)")    //Visual Explain
    private WebElement tabVizExplin;
    @FindBy(how = How.CSS, using="div.query-menu span:nth-child(5)")               //Tez
    private WebElement tabTez;
    @FindBy(how = How.CSS, using="div.query-menu span:nth-child(6)")     //Messages
    private WebElement tabMessages;



//    @FindBy(how = How.CSS, using = "panel.query-process-results-panel ul.nav-tabs")
//    private WebElement

    @FindBy(how = How.CSS, using = "table.table-expandable thead" )
    private WebElement strResultTableHeading;
    @FindAll(value = {@FindBy(how = How.CSS, using = "table.table-expandable tbody")})
    private List<WebElement> strResultTableBodyList;

//panel.query-process-results-panel div.panel-heading

//    @FindBy(how = How.CSS, using="panel.database-explorer li:nth-child(1)")                 //Databases in tree structure
//    private WebElement tabDatabases;
//    @FindBy(how = How.CSS, using="panel.database-explorer li:nth-child(2)")         // Results after Search tables
//    private WebElement tabFoundTables;
//    @FindBy(how = How.CSS, using="i.fa-expand")                 // Expand Query editor
//    private WebElement btnExpndQEditor;
//    @FindBy(how = How.CSS, using="ul.nav-tabs")                 // Open Worksheets Tabs
//    private WebElement tabWorksheet; /**/
//    @FindBy(how = How.CSS, using="div.fa-reorder")
//    private WebElement btnReorder;

    @FindBy(how = How.CSS, using="div.notification")            // Notification message for any operation
    private WebElement msgNotification;
    @FindBy(how = How.CSS, using="panel.query-process-results-panel div.panel-body li:nth-child(1)")   // Logs
    private WebElement tabQueryLogs;
    @FindBy(how = How.CSS, using="panel.query-process-results-panel div.panel-body li:nth-child(2)")   //Results
    private WebElement tabQueryResults;
    @FindBy(how = How.CSS, using="div.query-results-tools input.input-sm")      //Filter Columns in Result
    private WebElement tabFilterCol;
    @FindBy(how = How.CSS, using="div.query-results-tools button:nth-child(1)") // Previous (results)
    private WebElement btnPrevious;
    @FindBy(how = How.CSS, using="div.query-results-tools button:nth-child(2)") //Next (results)
    private WebElement btnNext;

//check this section how to do ********************************************
    @FindBy(how = How.CSS, using="div.settings-control button:nth-child(1)")            // Notification message for any operation
    private WebElement btnAddSetting;
    @FindBy(how = How.CSS, using="div.settings-control button:nth-child(2)")            // Notification message for any operation
    private WebElement btnRemoveSetting;
    @FindBy(how = How.CSS, using="div.settings-control button:nth-child(3)")            // Notification message for any operation
    private WebElement btnSaveDefaultSetting;
//  ***********************************************************************
// TODO in depth for each of the sub tabs

//    =========================== End of Query Tab ==================================

//    ========================== Saved Query Tab ====================================
    @FindBy(how = How.CSS, using="div.content thead column-filter:nth-child(1)")        // preview
    private WebElement txtPreview;
    @FindBy(how = How.CSS, using="div.content thead column-filter:nth-child(2)")        // Title
    private WebElement txtTitle;
    @FindBy(how = How.CSS, using="div.content thead column-filter:nth-child(3)")        //Database
    private WebElement txtDatabase;
    @FindBy(how = How.CSS, using="div.content thead column-filter:nth-child(4)")        //owner
    private WebElement txtOwner;
    @FindBy(how = How.CSS, using="button.clear-filters")                        //  Clear filters
    private WebElement btnClearFilters;

    @FindAll(value = {@FindBy(how=How.CSS, using="div.content tbody tr:nth-child(1)")})
    private List<WebElement> previewSavedQeuries;
    @FindAll(value = {@FindBy(how=How.CSS, using="div.content tbody tr:nth-child(2)")})
    private List<WebElement> titleSavedQeuries;
    @FindAll(value = {@FindBy(how=How.CSS, using="div.content tbody tr:nth-child(3)")})
    private List<WebElement> dbSavedQeuries;
    @FindAll(value = {@FindBy(how=How.CSS, using="div.content tbody tr:nth-child(4)")})
    private List<WebElement> ownerSavedQeuries;

    @FindBy(how = How.CSS, using="a.fa-gear")
    private WebElement iconSettings;

//    ======================[END] Saved Query Tab ====================================
//    ========================== History Tab ====================================

    @FindBy(how = How.CSS, using="div.content column-filter:nth-child(1)")          //Title
    private WebElement inpTitle;
    @FindBy(how = How.CSS, using="div.content column-filter:nth-child(2)")          //Status
    private WebElement inpStauts;
    @FindBy(how = How.CSS, using="div.content column-filter:nth-child(3)")          //Date of Execution
    private WebElement dateDatePicker;
    @FindBy(how = How.CSS, using="div.content column-filter:nth-child(4)")          //Duration
    private WebElement sldbarDuration;
    @FindBy(how = How.CSS, using="a.fa-expand")          //Expand Query in history
    private WebElement iconExpandQuery;

//   TODO :ask - same elements different page
    @FindAll(value = {@FindBy(how=How.CSS, using="div.content tbody tr:nth-child(1)")})
    private List<WebElement> titleQueriesList;
    @FindAll(value = {@FindBy(how=How.CSS, using="div.content tbody tr:nth-child(2)")})
    private List<WebElement> statusQueriesList;
    @FindAll(value = {@FindBy(how=How.CSS, using="div.content tbody tr:nth-child(3)")})
    private List<WebElement> dateQueriesList;
    @FindAll(value = {@FindBy(how=How.CSS, using="div.content tbody tr:nth-child(4)")})
    private List<WebElement> durationQueriesList;

//    @FindBy(how = How.CSS, using="button.clear-filters")          //Clear filters in History Tab TODO
//    private WebElement btnClearFilters;
//    ==========================[END] History Tab ====================================

//    ========================== UDF Tab ====================================

    @FindBy(how = How.CSS, using="div.content column-filter:nth-child(2)")          //Search for UDF by name
    private WebElement inpUDFName;
    @FindBy(how = How.CSS, using="div.content column-filter:nth-child(3)")          //Search for UDF by class
    private WebElement inpUDFClassName;
    @FindBy(how = How.CSS, using="button.clear-filters")          //Search for UDF by class
    private WebElement btnClearFilter;
    @FindBy(how = How.CSS, using="button.add-udf")          //Search for UDF by class
    private WebElement btnNewUDF;

    @FindAll(value = {@FindBy(how=How.CSS, using="div.content tr:nth-child(1)")})
    private List<WebElement> fileResourceList;
    @FindAll(value = {@FindBy(how=How.CSS, using="div.content tr:nth-child(2)")})
    private List<WebElement> udfNameList;
    @FindAll(value = {@FindBy(how=How.CSS, using="div.content tr:nth-child(3)")})
    private List<WebElement> udfClassList;

//    ==========================[END] UDF Tab ====================================

//    ========================== Upload Table Tab ====================================

    @FindBy(how = How.CSS, using="div.selectize-control")          //Upload into this database
    private WebElement dropListSelectedDb;
    @FindAll(value = {@FindBy(how = How.CSS, using="div.selectize-dropdown-content div")}) //Select database to upload table into
    private List<WebElement> availableDbList;

    @FindBy(how = How.CSS, using="div.col-md-3 input.form-control")          //Set Table name
    private WebElement inpTableName;
    @FindBy(how = How.CSS, using="div.col-md-3 input#isFirstRowHeader")          //Is first row header check
    private WebElement chkIsHeader;
//    @FindBy(how = How.CSS, using="div.query-results-tools button.btn-sm")          //Upload table
//    private WebElement chkIsHeader;

    @FindAll(value = {@FindBy(how = How.CSS, using="div.upload-table tr:nth-child(1)")})
    private List<WebElement> columnNameList;

//    @FindBy(how = How.CSS, using="div.upload-table tr:nth-child(1)")          //
//    private List<WebElement>;



//    ==========================[END] Upload Table Tab ====================================

    public String getCurrentDatabase(){
        return strCurrentDb.getText();
    }
    public List<WebElement> getDatabaseNamesList(){ return createdDbDropList; }
    public List<WebElement> getDatabaseTreeList(){ return createdDbTreeList; }
    public void refreshDatabases() { btnRefreshDb.click(); }

    public void selectQueryTab() { tabQuery.click(); }
    public void selectSavedQueriesTab() { tabSavedQueries.click(); }
    public void selectHistoryTab() { tabHistory.click(); }
    public void selectUDFTab() { tabUDF.click(); }
    public void selectUploadTableTab() { tabUploadTable.click(); }

    public void executeQuery() { btnExecute.click(); }
    public void explainQuery() { btnExplain.click(); }
    public void saveWorksheetAs() { btnSaveAs.click(); }
    public void createNewWorksheet() { btnNewSheet.click(); }
    public void clickIconDatabase() { iconDatabase.click(); }
    public void clickIconTblList() { iconTblList.click(); }


    public String getSearchResults(){
        String strSearchResults;

        strSearchResults = strResultTableHeading.getText() + "\n";
        for (WebElement tableRow: strResultTableBodyList) {
            strSearchResults = tableRow.getText() + "\n";
        }
        return strSearchResults;
    }

    /** Find database from DROP LIST
     * @param strFindDb
     * @return
     */
    public int findDatabaseInTree(String strFindDb){
        for (WebElement strDb: createdDbTreeList) {
            if (strDb.getText().compareToIgnoreCase(strFindDb)==0) {
                return 0;
            }
        }return 1;
    }


    /** Find database from TREE LIST
     * @param matchDbName
     */
    public void selectDbDropList(String matchDbName){
        strCurrentDb.click();
        for (WebElement database: createdDbDropList) {
            if(database.getAttribute("data-value").compareToIgnoreCase(matchDbName)==0){
                database.click();
                break;
            }
        }
    }

    public int selectDbTreeList(String matchDbName){
        for (WebElement database: createdDbTreeList) {
            if(database.getText().compareToIgnoreCase(matchDbName)==0) {
                database.click();
                System.out.println("Database visible in Tree Structure");
                return 0;
            }
        }
        return 1;
    }
    public int selectTableTreeList(String matchDbName){
        for (WebElement table: tablesInDbList) {
            if(table.getText().compareToIgnoreCase(matchDbName)==0) {
                table.click();
                System.out.println("Table created successfully");
                return 0;
            }
        }
        return 1;
    }

    public List<String> getColumnNames(){
        List<String> columnsList = new ArrayList<String>();
        for (WebElement columns: columnsInTableList) {
            columnsList.add(columns.getText());
        }
        return columnsList;
    }

    public void fillQueryInQueryEditor(String query){
        txtareaQueryEditor.click();
        txtareaQueryEditor.clear();
        txtareaQueryEditor.sendKeys(query);
    }

    public void submitExecuteQuery(String query, WebDriver driver){
        WebDriver actualDriver = WebDriverSyncWrapper.obtainActualDriver(driver);
        JavascriptExecutor js = (JavascriptExecutor) actualDriver;
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", txtareaQueryEditor,"display:yes;");
        fillQueryInQueryEditor(query);
        executeQuery();
    }

    public String readFromQueryEditor(){
        String getQuery  = strFromQueryEditor.getText();
        return getQuery;
    }

    public List<String> getSearchResultsTables(){
        List<String> foundTableNames = new ArrayList<String>();
        for (WebElement table: foundTablesList) {
            foundTableNames.add(table.getText());
        }
        return foundTableNames;
    }

    public List<String> getSearchResultsColumns(){
        List<String> foundColumnNames = new ArrayList<String>();
        for (WebElement column: foundColumnsList) {
            foundColumnNames.add(column.getText());
        }
        return foundColumnNames;
    }

    public void fnSearchTable(String tableName){
        searchElementsList.get(0).click();
        searchElementsList.get(0).sendKeys(tableName);
        searchElementsList.get(0).sendKeys(Keys.RETURN);
    }

    public void fnSearchColumn(String columnName){
        searchElementsList.get(1).click();
        searchElementsList.get(1).sendKeys(columnName);
        searchElementsList.get(1).sendKeys(Keys.RETURN);
    }

}

/*
* panel.database-explorer div.panel-body input.nth-child(1)
*
*
* */
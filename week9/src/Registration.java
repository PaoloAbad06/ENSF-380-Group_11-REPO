/*
Copyright Ann Barcomb and Emily Marasco, 2022-2024
Licensed under GPL v3
See LICENSE.txt for more information.
*/

//package edu.ucalgary.oop;

import java.sql.*;

public class Registration{

    public final String DBURL;
    public final String USERNAME;
    public final String PASSWORD;    
    
    private Connection dbConnect;
    private ResultSet results;
    
    public Registration(String url, String user, String pw){

        // Database URL
        this.DBURL = url;

        //  Database credentials
        this.USERNAME = user;
        this.PASSWORD = pw;
        try{
                dbConnect = DriverManager.getConnection("jdbc:postgresql://localhost/competition", "oop", "ucalgary");

        }
         catch (SQLException ex) {
    ex.printStackTrace();
    }   
    }


//Must create a connection to the database, no arguments, no return value    
    public void initializeConnection(){

/***********ADD CODE HERE***********/                
/* Throw SQL exception if connection does not work */

    }
    
    String getDburl(){
        return this.DBURL;
    }

    String getUsername(){
        return this.USERNAME;
    }
    
    String getPassword(){
        return this.PASSWORD;
    }

    
    public String selectAllNames(String tableName){     

public String selectAllNames(String tableName) {     

    // 1. Create an empty String to store the list of names we will return [cite: 25]
    String list = ""; 
    
    try {
        // 2. Create a 'Statement' object to send our SQL command to the database 
        Statement myStmt = dbConnect.createStatement();
        
        // 3. Send the 'SELECT' command to get every row from the table (competitor or teacher) [cite: 54]
        // The results are stored in the 'results' variable 
        results = myStmt.executeQuery("SELECT * FROM " + tableName);
        
        // 4. Use a while loop to move through the table, row by row 
        // results.next() returns true as long as there is another row to read
        while (results.next()) {

            // 5. Get the text stored in the "LName" column for the current row [cite: 17]
            String lastName = results.getString("LName");
            
            // 6. Get the text stored in the "FName" column for the current row [cite: 17]
            String firstName = results.getString("FName");

            // 7. Combine the names with a comma and a new line to match output.txt [cite: 10, 54]
            String formattedLine = lastName + ", " + firstName + "\n";

            // 8. Add this new line of text to our main list string [cite: 26]
            list = list + formattedLine;
        }
        
        // 9. Close the statement to free up database resources 
        myStmt.close(); 
    } 
    // 10. If the database connection fails or the SQL is wrong, catch the error here [cite: 25]
    catch (SQLException ex) {
        // 11. Print the error details to the console so we can debug it
        ex.printStackTrace(); 
    }

    // 12. Return the final string containing all the names [cite: 25]
    return list;
/***********ADD CODE HERE***********/                
/* Use selectAllNames method to return a list of competitiors and a list of teachers (two separate calls) Must take in a String for the table name and return a String */
    
    }
    
    
    public void insertNewCompetitor(String id, String lName, String fName, int age, String instrument, String teacherID) {
       try{
        if(!validateTeacher(teacherID)){
            throw new IllegalArgumentException("Student must have a registered teacher.");
        }

        if(age < 5 || age > 18){
            throw new IllegalArgumentException("Student must be between the ages of 5 and 18.");
        } 

        String query = "INSERT INTO COMPETITOR (CompetitorID, LName, FName, Age, Instrument, TeacherID) VALUES   (?, ?, ?, ?, ?, ?)"; 
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
            
            myStmt.setString(1, id);
            myStmt.setString(2, lName);
            myStmt.setString(3, fName);
            myStmt.setInt(4, age);    
            myStmt.setString(5, instrument);
            myStmt.setString(6, teacherID);
        myStmt.execute();
    }
    catch (SQLException ex) {
ex.printStackTrace();
}
    }    

    
    // Used to ensure that any new students are connected to an existing teacher    
    private boolean validateTeacher(String teacherID){
        
        boolean validTeacher = false;
                    
        try {                    
            Statement myStmt = dbConnect.createStatement();
            
            // Execute SQL query
            results = myStmt.executeQuery("SELECT * FROM teacher");
            
            // Process the results set
            while (results.next()){
                if(results.getString("TeacherID").equals(teacherID))
                    validTeacher = true;
            }
            
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return validTeacher;

    }    
    
 
    public void deleteCompetitor(String id){
        try {
            String query = "DELETE FROM competitor WHERE CompetitorID=?";
            PreparedStatement pStmt = dbConnect.prepareStatement(query);
            pStmt.setString(1, id);
            pStmt.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }     

    public void close() {
        
/***********ADD CODE HERE***********/      
    try {          
        dbConnect.close();
        results.close();
        }
    catch (SQLException ex)
        {
             ex.printStackTrace();
        }
}
    
    public static void main(String[] args) {

	// Remember that each time you execute the given .sql file, the database will be reset.
	// You should reset the database before each test run of your code.

        Registration myJDBC = new Registration("jdbc:postgresql://localhost/competition","oop","ucalgary");
        
        myJDBC.initializeConnection();
                
        System.out.println("------------------------------");
        System.out.println("***Printing list of competitors:***");
        System.out.println(myJDBC.selectAllNames("competitor"));
        System.out.println("------------------------------");
        System.out.println("***Printing list of teachers:***");
        System.out.println(myJDBC.selectAllNames("teacher"));

        System.out.println("------------------------------");
        System.out.println("***Now testing insert statements...***");                

        myJDBC.insertNewCompetitor("234", "Robertson", "Ebba", 15, "Trombone", "0023");
        System.out.println("Competitor #234 Robertson, Ebba should now be added.");
        //Check to make sure the database has been updated with a new competitor        

        try{
            myJDBC.insertNewCompetitor("678", "Jordan", "Ali", 10, "Oboe", "9807");
        }catch(IllegalArgumentException e){
            System.out.println("Sucessfully threw exception when no registered teacher can be found for a competitor.");
        }
        
        try{
            myJDBC.insertNewCompetitor("654", "Smyth", "Ace", 4, "Oboe", "1012");
        }catch(IllegalArgumentException e){
            System.out.println("Sucessfully threw exception when competitor is outside valid age range.");
        }
        
        System.out.println("------------------------------");
        System.out.println("***Now testing delete statements...***");                        
        myJDBC.deleteCompetitor("205");
        System.out.println("Competitor #205 Kamilla, Mala should now be deleted.");

        myJDBC.close();
        System.out.println("------------------------------");
        System.out.println("***End of tests.***"); 
    }
}



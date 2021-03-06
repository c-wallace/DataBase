import java.sql.ResultSet;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MarshallHousing {

    //Present a report listing the Manager’s name and telephone number for each hall of residence.
    public static void managerNamePhone(Statement stmt) throws SQLException {
        String select = "select fName, lName, phoneNum from Staff As s, HallResidence As h WHERE s.staffID = h.staffID GROUP BY h.name";

        ResultSet rset = stmt.executeQuery(select);

        // Step 4: Process the ResultSet by going forward via next().
        //  For each row, retrieve the contents of the cells with getXxx(columnName).
        System.out.println("The records selected are:");
        int rowCount = 0;
        while(rset.next()) {   // Move the cursor to the next row, return false if no more row
            String fName = rset.getString("fName");
            System.out.println(fName);
            String lName = rset.getString("lName");
            System.out.println(lName);
            String phoneNum = rset.getString("phoneNum");
            System.out.println(phoneNum);
            ++rowCount;
        }
        System.out.println("Total number of records = " + rowCount);
    }

    //Present a report listing the names and MU numbers of students with the details of their lease agreements
    public static void studentLeaseInfo(Statement stmt) throws SQLException {
        String select = "SELECT fName, lName, muID, leaseNum, rentalPeriod, beginDate, endDate,invoice,placeNum FROM Student AS s, Lease AS l, WHERE s.muID = l.muID";

        ResultSet rset = stmt.executeQuery(select);

        // Step 4: Process the ResultSet by going forward via next().
        //  For each row, retrieve the contents of the cells with getXxx(columnName).
        System.out.println("The records selected are:");
        int rowCount = 0;
        while(rset.next()) {   // Move the cursor to the next row, return false if no more row
            String fName = rset.getString("fName");
            System.out.println(fName);
            String lName = rset.getString("lName");
            System.out.println(lName);
            int muID = rset.getInt("muID");
            System.out.println(muID);
            int leaseNum = rset.getInt("leaseNum");
            System.out.println(leaseNum);
            int rentalPeriod = rset.getInt("rentalPeriod");
            System.out.println(rentalPeriod);
            Date beginDate = rset.getDate("beginDate");
            System.out.println(beginDate);
            Date endDate = rset.getDate("endDate");
            System.out.println(endDate);
            int invoiceID = rset.getInt("invoiceID");
            System.out.println(invoiceID);
            int placeNum = rset.getInt("placeNum");
            System.out.println(placeNum);

            ++rowCount;
        }
        System.out.println("Total number of records = " + rowCount);
    }

    //Display the details of lease agreements that include the summer semester
    public static void summerLeases(Statement stmt) throws SQLException {
        String select = "SELECT * FROM Lease WHERE rentalPeriod = 'Year'";

        ResultSet rset = stmt.executeQuery(select);

        // Step 4: Process the ResultSet by going forward via next().
        //  For each row, retrieve the contents of the cells with getXxx(columnName).
        System.out.println("The records selected are:");
        int rowCount = 0;
        while(rset.next()) {   // Move the cursor to the next row, return false if no more row
            int leaseNum = rset.getInt("leaseNum");
            System.out.println(leaseNum);
            int rentalPeriod = rset.getInt("rentalPeriod");
            System.out.println(rentalPeriod);
            Date beginDate = rset.getDate("beginDate");
            System.out.println(beginDate);
            Date endDate = rset.getDate("endDate");
            System.out.println(endDate);
            int invoiceID = rset.getInt("invoiceID");
            System.out.println(invoiceID);
            int placeNum = rset.getInt("placeNum");
            System.out.println(placeNum);
            int muID = rset.getInt("muID");
            System.out.println(muID);

            ++rowCount;
        }
        System.out.println("Total number of records = " + rowCount);
    }

    //Display the details of the total rent paid by a given student
    public static void totalRent(Statement stmt, int muID) throws SQLException{
        String select = "SELECT SUM(rentRate) AS totalRent FROM Student AS s LEFT JOIN Lease AS l ON s.muID="+muID+" AND "+muID+"=l.muID JOIN Room AS r ON r.placeNum = l.placeNum";

        ResultSet rset = stmt.executeQuery(select);

        // Step 4: Process the ResultSet by going forward via next().
        //  For each row, retrieve the contents of the cells with getXxx(columnName).
        System.out.println("The records selected are:");
        int rowCount = 0;
        while(rset.next()) {   // Move the cursor to the next row, return false if no more row
            String totalRent = rset.getString("totalRent");
            System.out.println(totalRent);
            ++rowCount;
        }
        System.out.println("Total number of records = " + rowCount);
    }

    //Present a report on students who have not paid their invoices by a given date
    public static void nonpaymentDueDate(Statement stmt) throws SQLException{
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        String select="SELECT s.* FROM Student AS s LEFT JOIN Lease AS l ON s.muID = l.muID JOIN Invoice AS i ON l.leaseNum = i.leaseNum WHERE paymentMade = 'N' AND paymentDateDue <"+ dtf.format(localDate);

        ResultSet rset = stmt.executeQuery(select);

        // Step 4: Process the ResultSet by going forward via next().
        //  For each row, retrieve the contents of the cells with getXxx(columnName).
        System.out.println("The records selected are:");
        int rowCount = 0;
        while(rset.next()) {   // Move the cursor to the next row, return false if no more row
            int muID = rset.getInt("muID");
            System.out.println(muID);
            int advisorID = rset.getInt("advisorID");
            System.out.println(advisorID);
            String fName = rset.getString("fName");
            System.out.println(fName);
            String lName = rset.getString("lName");
            System.out.println(lName);
            String phoneNum = rset.getString("phoneNum");
            System.out.println(phoneNum);
            String email = rset.getString("email");
            System.out.println(email);
            String doB = rset.getString("doB");
            System.out.println(doB);
            String gender = rset.getString("gender");
            System.out.println(gender);
            String year = rset.getString("year");
            System.out.println(year);
            String nationality = rset.getString("nationality");
            System.out.println(nationality);
            String specialNeeds = rset.getString("specialNeeds");
            System.out.println(specialNeeds);
            String major = rset.getString("major");
            System.out.println(major);
            String minor = rset.getString("minor");
            System.out.println(minor);

            ++rowCount;
        }
        System.out.println("Total number of records = " + rowCount);

    }

    //Display the details of apartment inspections where the property was found to be in an unsatisfactory condition.
    public static void unSatsifatoryApartments(Statement stmt) throws SQLException{
        String select = "SELECT * FROM Inspection WHERE condtion = 'unsatisfactory'";

        ResultSet rset = stmt.executeQuery(select);

        // Step 4: Process the ResultSet by going forward via next().
        //  For each row, retrieve the contents of the cells with getXxx(columnName).
        System.out.println("The records selected are:");
        int rowCount = 0;
        while(rset.next()) {   // Move the cursor to the next row, return false if no more row
            int insepctionNum = rset.getInt("insepctionNum");
            System.out.println(insepctionNum);
            Date date = rset.getDate("date");
            System.out.println(date);
            String condtion = rset.getString("condition");
            System.out.println(condtion);
            String comments = rset.getString("comments");
            System.out.println(comments);
            ++rowCount;
        }
        System.out.println("Total number of records = " + rowCount);
    }

    //Present a report of the names and MU numbers of students with their room number and place number in a particular hall of residence
    public static void studentFromHall(Statement stmt, String hallName) throws SQLException{
        String select ="SELECT s.fName, s.lName, s.muID, r.placeNum, r.roomNum FROM HallResidence AS h WHERE h.name="+hallName+" JOIN Accommendation as A ON a.accmID = h.accmID JOIN Room AS r ON r.accmID = a.accmID JOIN Lease AS l ON l.placeNum = r.placeNum JOIN Student AS s ON s.muID = l.muID";

        ResultSet rset = stmt.executeQuery(select);

        // Step 4: Process the ResultSet by going forward via next().
        //  For each row, retrieve the contents of the cells with getXxx(columnName).
        System.out.println("The records selected for" + hallName + " are: ");
        int rowCount = 0;
        while(rset.next()) {   // Move the cursor to the next row, return false if no more row
            int muID = rset.getInt("muID");
            System.out.println(muID);
            String fName = rset.getString("fName");
            System.out.println(fName);
            String lName = rset.getString("lName");
            System.out.println(lName);
            int placeNum = rset.getInt("placeNum");
            System.out.println(placeNum);
            int roomNum = rset.getInt("roomNum");
            System.out.println(roomNum);
            ++rowCount;
        }
        System.out.println("Total number of records = " + rowCount);
    }

    //Present a report listing the details of all students currently on the waiting list for
    //accommodation
    public static void waitingList(Statement stmt) throws SQLException{
        String select = "SELECT * FROM Student WHERE status = 'true'";

        ResultSet rset = stmt.executeQuery(select);

        // Step 4: Process the ResultSet by going forward via next().
        //  For each row, retrieve the contents of the cells with getXxx(columnName).
        System.out.println("The records selected are:");
        int rowCount = 0;
        while(rset.next()) {   // Move the cursor to the next row, return false if no more row
            int muID = rset.getInt("muID");
            System.out.println(muID);
            String fName = rset.getString("fName");
            System.out.println(fName);
            String lName = rset.getString("lName");
            System.out.println(lName);
            String phoneNum = rset.getString("phoneNum");
            System.out.println(phoneNum);
            String email = rset.getString("email");
            System.out.println(email);
            String doB = rset.getString("doB");
            System.out.println(doB);
            String gender = rset.getString("gender");
            System.out.println(gender);
            String year = rset.getString("year");
            System.out.println(year);
            String nationality = rset.getString("nationality");
            System.out.println(nationality);
            String specialNeeds = rset.getString("specialNeeds");
            System.out.println(specialNeeds);
            String major = rset.getString("major");
            System.out.println(major);
            String minor = rset.getString("minor");
            System.out.println(minor);
            int advisorID = rset.getInt("advisorID");
            System.out.println(advisorID);


            ++rowCount;
        }
        System.out.println("Total number of records = " + rowCount);
    }

    //Display the total number of students in each student category
    public static void totalStudentYear(Statement stmt) throws SQLException{
        String select = "SELECT COUNT(year) AS myCount FROM Student GROUP BY year";

        ResultSet rset = stmt.executeQuery(select);

        // Step 4: Process the ResultSet by going forward via next().
        //  For each row, retrieve the contents of the cells with getXxx(columnName).
        System.out.println("The records selected are:");
        int rowCount = 0;
        while(rset.next()) {   // Move the cursor to the next row, return false if no more row
            String myCount = rset.getString("myCount");
            System.out.println(myCount);
            ++rowCount;
        }
        System.out.println("Total number of records = " + rowCount);
    }

    //Present a report of the names and MU numbers for all students who have not supplied details of their next-of-kin.
    public static void noNextofKin(Statement stmt) throws  SQLException{
        String select = "SELECT muID, fName, lName FROM Student WHERE muID NOT IN (SELECT muID FROM StudentNextOfKin)";

        ResultSet rset = stmt.executeQuery(select);

        // Step 4: Process the ResultSet by going forward via next().
        //  For each row, retrieve the contents of the cells with getXxx(columnName).
        System.out.println("The records selected are:");
        int rowCount = 0;
        while(rset.next()) {   // Move the cursor to the next row, return false if no more row
            int muID = rset.getInt("muID");
            System.out.println(muID);
            String fName = rset.getString("fName");
            System.out.println(fName);
            String lName = rset.getString("lName");
            System.out.println(lName);
            ++rowCount;
        }
        System.out.println("Total number of records = " + rowCount);
    }

    // Display the name and internal telephone number of the Advisor for a particular student.
    public static void advisorStudent(Statement stmt, int muID) throws SQLException{
        String select = "SELECT a.fullName. a.officePhone FROM Advisor AS a JOIN Student AS s ON s.advisorID = a.advisorID WHERE s.muID ="+muID;

        ResultSet rset = stmt.executeQuery(select);

        // Step 4: Process the ResultSet by going forward via next().
        //  For each row, retrieve the contents of the cells with getXxx(columnName).
        System.out.println("The records selected are:");
        int rowCount = 0;
        while(rset.next()) {   // Move the cursor to the next row, return false if no more row
            String fullName = rset.getString("fullName");
            System.out.println(fullName);
            String officePhone = rset.getString("officePhone");
            System.out.println(officePhone);
            ++rowCount;
        }
        System.out.println("Total number of records = " + rowCount);
    }

    //12. Display the minimum, maximum, and average monthly rent for rooms in residence halls
    public static void statsRent(Statement stmt) throws SQLException{
        String select ="SELECT AVG(rentRate) AS myAvg, MAX(rentRate) AS myMax, MIN(rentRate) AS myMin FROM Room AS r JOIN Accommodation AS a ON r.accmID = a.accmID JOIN HallResidence AS h ON a.accmID = h.accmID GROUP BY h.name";

        ResultSet rset = stmt.executeQuery(select);

        // Step 4: Process the ResultSet by going forward via next().
        //  For each row, retrieve the contents of the cells with getXxx(columnName).
        System.out.println("The records selected are:");
        int rowCount = 0;
        while(rset.next()) {   // Move the cursor to the next row, return false if no more row
            String myAvg = rset.getString("myAvg");
            System.out.println(myAvg);
            String myMax = rset.getString("myMax");
            System.out.println(myMax);
            String myMin = rset.getString("myMin");
            System.out.println(myMin);
            ++rowCount;
        }
        System.out.println("Total number of records = " + rowCount);
    }

    //Display the total number of places in each residence hall.
    public static void totalRooms(Statement stmt) throws SQLException{
        String select = "SELECT COUNT (roomNum) AS myCount FROM Room AS r JOIN Accommodation AS a ON a.accmID = r.accmID JOIN HallResidence AS h ON a.accmID = h.accmID GROUP  BY h.name";

        ResultSet rset = stmt.executeQuery(select);

        // Step 4: Process the ResultSet by going forward via next().
        //  For each row, retrieve the contents of the cells with getXxx(columnName).
        System.out.println("The records selected are:");
        int rowCount = 0;
        while(rset.next()) {   // Move the cursor to the next row, return false if no more row
            String myCount = rset.getString("myCount");
            System.out.println(myCount);
            ++rowCount;
        }
        System.out.println("Total number of records = " + rowCount);
    }

    //Display the staff number, name, age, and current location of all members of the
    //residence staff who are over 60 years old today.
    //TODO finish
    public static void staffOverSixety(Statement stmt) throws SQLException{
        String select = "Select fName, lName, doB FROM Staff WHERE TIMESTAMPDIFF(YEAR,doB,CURDATE())>= 60";

        ResultSet rset = stmt.executeQuery(select);
        // Step 4: Process the ResultSet by going forward via next().
        //  For each row, retrieve the contents of the cells with getXxx(columnName).
        System.out.println("The records selected are:");
        int rowCount = 0;
        while(rset.next()) {   // Move the cursor to the next row, return false if no more row
            String myCount = rset.getString("myCount");
            System.out.println(myCount);
            ++rowCount;
        }
        System.out.println("Total number of records = " + rowCount);
    }

    //Display the total number of registered vehicles in the particular parking lot.
    public static void totalVehicals(Statement stmt, String lotName) throws SQLException{
        String select = "SELECT COUNT(vin) AS totalVehicle FROM Vechicle AS v LEFT JOIN ParkingLot AS p ON v.lotNum=p.lotNum WHERE p.lotName= " + lotName;

        ResultSet rset = stmt.executeQuery(select);

        // Step 4: Process the ResultSet by going forward via next().
        //  For each row, retrieve the contents of the cells with getXxx(columnName).
        System.out.println("The records selected for " + lotName + " are: ");
        int rowCount = 0;
        while(rset.next()) {   // Move the cursor to the next row, return false if no more row
            String total = rset.getString("totalVehicle");
            System.out.println(total);
            ++rowCount;
        }
        System.out.println("Total number of records = " + rowCount);
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        
        try (
        		
                // Step 1: Allocate a database 'Connection' object
                Connection conn = DriverManager.getConnection(
                		"jdbc:mysql://localhost:3306/Housing", "root", "1234");
                // MySQL: "jdbc:mysql://hostname:port/databaseName", "username", "password"

                // Step 2: Allocate a 'Statement' object in the Connection
                Statement stmt = conn.createStatement();
        ) {
           String choice = "";

           while(!choice.equalsIgnoreCase("quit")){
               System.out.println("Enter a SQl Query choice for the Marshall Hall Residence:" +
                       "\n1. Present a report listing the Manager’s name and telephone number for each hall of residence." +
                       "\n2. Present a report listing the names and MU numbers of students with the details of their lease agreements." +
                       "\n3. Display the details of lease agreements that include the summer semester." +
                       "\n4. Display the details of the total rent paid by a given student." +
                       "\n5. Present a report on students who have not paid their invoices by a given date." +
                       "\n6. Display the details of apartment inspections where the property was found to be in an unsatisfactory condition" +
                       "\n7. Present a report of the names and MU numbers of students with their room number and place number in a particular hall of residence." +
                       "\n8. Present a report listing the details of all students currently on the waiting list for accommodation; that is; who were not places." +
                       "\n9. Display the total number of students in each student category." +
                       "\n10. Present a report of the names and MU numbers for all students who have not supplied details of their next-of-kin." +
                       "\n11. Display the name and internal telephone number of the Advisor for a particular student." +
                       "\n12. Display the minimum, maximum, and average monthly rent for rooms in residence halls." +
                       "\n13. Display the total number of places in each residence hall." +
                       "\n14. Display the staff number, name, age, and current location of all members of the residence staff who are over 60 years old today." +
                       "\n15. Display the total number of registered vehicles in the particular parking lot" +
                       "\n Enter quit to exit program!");
               choice = in.nextLine();

               if(choice.equals("1")){
                   managerNamePhone(stmt);
               }
               else if(choice.equals("2")){
                   studentLeaseInfo(stmt);
               }
               else if(choice.equals("3")){
                   summerLeases(stmt);
               }
               else if(choice.equals("4")){
                   System.out.println("Enter muID of Student: ");
                   int muID = in.nextInt();
                   totalRent(stmt, muID);
               }
               else if(choice.equals("5")){
                   nonpaymentDueDate(stmt);
               }
               else if(choice.equals("6")){
                   unSatsifatoryApartments(stmt);
               }
               else if(choice.equals("7")){
                   System.out.println("Enter a residence hall name: ");
                   String name = in.nextLine();
                   in.next();
                   studentFromHall(stmt, name);
               }
               else if(choice.equals("8")){
                   waitingList(stmt);
               }
               else if(choice.equals("9")){
                   totalStudentYear(stmt);
               }
               else if(choice.equals("10")){
                   noNextofKin(stmt);
               }
               else if(choice.equals("11")){
                   System.out.println("Enter muID of Student: ");
                   int muID = in.nextInt();
                   advisorStudent(stmt, muID);
               }
               else if(choice.equals("12")){
                   statsRent(stmt);
               }
               else if(choice.equals("13")){
                   totalRooms(stmt);
               }
               else if(choice.equals("14")){
                   staffOverSixety(stmt);
               }
               else if(choice.equals("15")){
                   System.out.println("Enter a parking lot name: ");
                   String name = in.nextLine();
                   in.next();
                   totalVehicals(stmt, name);
               }
               else if(choice.equalsIgnoreCase("quit")){
                   System.out.println("Goodbye!");
               }
               else
               {
                   System.out.println("Enter a valid choice");
               }
           }

        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
}

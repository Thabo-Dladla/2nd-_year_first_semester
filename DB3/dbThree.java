import java.sql.*;

public class dbThree {
    public static void main(String [] args){
        String url = "********";
        String loginName = "*******";
        String passwd = "*******";

        Connection conn =null;
        Statement state = null;
        try{
            System.out.println("Establishing a connection to the database");

            conn = DriverManager.getConnection(url ,loginName,passwd);

            System.out.println("Creating a relation");

            state = conn.createStatement();

            String definition = "CREATE TABLE IF NOT EXISTS course ("
                    + "Course_Code VARCHAR(10), "
                    + "Year YEAR, "
                    + "Course_Name VARCHAR(50), "
                    + "Module VARCHAR(50), "
                    + "PRIMARY KEY (Course_Code, Year))";


            state.executeUpdate(definition);//creating table

            //inserting
            String insertQuery = "INSERT INTO course VALUES " +
                    "('CSC2001F', 2022, 'Computer Science 2001','Analysis of algorithms'), " +
                    "('CSC2001F', 2025, 'Computer Science 2001','AVL Trees'), " +
                    "('MAM1000W', 2022, 'Mathematics 1000W','Complex Numbers')";
            state.executeUpdate(insertQuery);

            //QUERYING ONE TUPLE
            String query = "SELECT * FROM course WHERE Course_Code = 'MAM1000W'";

            ResultSet result = state.executeQuery(query);
            //printing out my queried line
            while (result.next()){
                String code = result.getString("Course_Code");
                int year = result.getInt("Year");
                String course = result.getString("Course_Name");
                String content = result.getString("Module");


                System.out.println("Lwazi did the course : "+course + " in "+year +" back then it had the course code "+code+" his favourite module was "+content);
            }
            result.close();
            state.close();
            conn.close();
        }
        catch(SQLException se){se.printStackTrace();}
        finally {
            try {if (conn != null) conn.close();}
            catch (SQLException se){ se.printStackTrace();}
        }
    }
}
     


	
	
	
     
	
	
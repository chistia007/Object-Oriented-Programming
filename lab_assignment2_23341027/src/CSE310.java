import java.sql.*;
import java.util.Scanner;

public class CSE310 {
    static Scanner sc;
    static int sid;
   static  String email;
   static PreparedStatement preparedStatement;
   static Connection con;
   static int section1SeatLimit;
   static int section2SeatLimit;
    public static void main(String[] args) {
        sc = new Scanner(System.in);

        authenticationActivity();


    }

    private static void studentRegActivity(String name, int sid, String email, String password) {
                try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse310_assignment2", "root", "root");
//            Statement statement = con.createStatement();
//            printDatabase(con, statement);

            String query = "Insert into students (sid, name,email,password) values(?, ?,?,?)";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, sid);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);
            preparedStatement.executeUpdate();
            // printDatabase(con, preparedStatement);
                    System.out.println("Registration successful!");
                    authenticationActivity();
            con.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }


    private static void loginActivity() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse310_assignment2", "root", "root");

            Statement statement = connection.createStatement();

            System.out.println("Email:");
            email = sc.nextLine();
            System.out.println("Password:");
            String password = sc.nextLine();

            //faculty login
            String facultyLogin = "select * from faculty where email = '" + email + "'" + " and password= '" +password + "'";
            ResultSet resultSet0=statement.executeQuery(facultyLogin);
            if(resultSet0.next()){
                System.out.println("1. Section-1 students\n"+ "2. Section-2 students");
                int choice=sc.nextInt();
                String sectionDetails="select * from students where cse310_section =  '" +choice +"'" ;
                ResultSet query=statement.executeQuery(sectionDetails);
                while(query.next()){
                    System.out.println("Name                                   SID");
                    System.out.println(query.getString("name")+ "                    "+ query.getInt("sid"));
                }
                authenticationActivity();
            }


            else{         //else student login check
                String studentLoginQuery = "select * from students where email = '" + email + "'" + " and password= '" +password + "'";

                ResultSet resultSet = statement.executeQuery(studentLoginQuery);
                if (resultSet.next()) {
                    int studentID = resultSet.getInt("sid");
                    String studentEmail = resultSet.getString("email");

                    String section1Query = "select * from sections where section_number = 1";
                    ResultSet resultSet2 = statement.executeQuery(section1Query);
                    while (resultSet2.next()) {
                        String time = resultSet2.getString("time");
                        section1SeatLimit= resultSet2.getInt("seat_remaining");
                        System.out.println("1   Section-01  " + time+ "       "+ section1SeatLimit+ " Seats Remaining");
                    }
                    resultSet2.close();
                    String section2Query = "select * from sections where section_number = 2";
                    ResultSet resultSet3 = statement.executeQuery(section2Query);
                    while (resultSet3.next()) {
                        String time = resultSet3.getString("time");
                        section2SeatLimit= resultSet3.getInt("seat_remaining");
                        System.out.println("2   Section-02  " + time+ "       "+ section2SeatLimit+ " Seats Remaining");
                    }
                    resultSet3.close();

                    sectionChoosing(section1SeatLimit,section2SeatLimit);
                }
                else {
                    System.out.println("This student does not exist");
                    authenticationActivity();
                }
                resultSet.close();
            }
            resultSet0.close();



            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void sectionChoosing(int section1SeatLimit, int section2SeatLimit) {
        int choice = sc.nextInt();
        if (choice == 1) {
            if (section1SeatLimit == 0) {
                System.out.println("Section already full. choose another section");
                sectionChoosing(section1SeatLimit, section2SeatLimit);
            }
            try{Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse310_assignment2", "root", "root");
                String updateSectionQuery = "update students set cse310_section = 1 WHERE email = ?";
                PreparedStatement statement = connection.prepareStatement(updateSectionQuery);
                statement.setString(1, email);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    --section1SeatLimit;
                    String updateSectionSeatQuery = "update  sections set seat_remaining = ? WHERE section_number = ?";
                    PreparedStatement statement1 = connection.prepareStatement(updateSectionSeatQuery);
                    statement1.setInt(1, section1SeatLimit);
                    statement1.setInt(2, 1);
                    statement1.executeUpdate();
                    System.out.println("Added to the section-1");
                } else {
                    System.out.println("section adding failed");
                }
                authenticationActivity();
            }catch (Exception e){
                System.out.println(e);
            }
        }
        else if (choice == 2) {
            if (section2SeatLimit == 0) {
                System.out.println("Section already full. choose another section");
                sectionChoosing(section1SeatLimit, section2SeatLimit);
            }
            try{Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse310_assignment2", "root", "root");
                String updateSectionQuery = "UPDATE students SET cse310_section = 2 WHERE email = ?";
                PreparedStatement statement = connection.prepareStatement(updateSectionQuery);
                statement.setString(1, email);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    --section2SeatLimit;
                    String updateSectionSeatQuery = "update  sections set seat_remaining = ? WHERE section_number = ?";
                    PreparedStatement statement1 = connection.prepareStatement(updateSectionSeatQuery);
                    statement1.setInt(1, section2SeatLimit);
                    statement1.setInt(2, 2);
                    statement1.executeUpdate();
                    System.out.println("Added to the section-2");
                } else {
                    System.out.println("section adding failed");
                }
                authenticationActivity();
            }catch (Exception e){}
        }
        else{authenticationActivity();}
    }
    public static void authenticationActivity() {
        System.out.println("1. Register\n"
                + "2. Login\n"
                + "3. Exit");

        int choice= sc.nextInt();
        sc.nextLine();
        switch(choice){
            case 1:
                System.out.println("Name:");
                String name= sc.nextLine();
                System.out.println("SID:");
                sid= sc.nextInt(); //student id
                sc.nextLine();
                System.out.println("Email:");
                email= sc.nextLine();
                System.out.println("password");
                String password= sc.nextLine();
                System.out.println("1. Confirm registration\n"
                        +"2. Exit");
                int reg= sc.nextInt();
                if (reg==1){
                    studentRegActivity(name,sid,email,password);

                }
                else{
                    authenticationActivity();
                }
                break;
            case 2:
                loginActivity();
                break;
            default:
                break;
        }
      }
    }


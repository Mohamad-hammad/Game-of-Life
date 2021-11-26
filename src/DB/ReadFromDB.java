package DB;

import java.sql.*;


public class ReadFromDB implements DBInterface {
    public void Load(int Box_Row[], int Box_Column[], int Box[][], int save_id, int Counter[], String FilePath) throws SQLException {
        Connection connection = DriverManager.getConnection(FilePath);

        Integer ie = new Integer(save_id);
        String st1 = "SELECT Counter, Box_row, Box_Column FROM Save_State WHERE Save_id = " + ie.toString() + ";";
        Statement statement1 = connection.createStatement();
        ResultSet result = statement1.executeQuery(st1);
        while(result.next()){
            Counter[0] = result.getInt("Counter");
            Box_Row[0] = result.getInt("Box_row");
            Box_Column[0] = result.getInt("Box_Column");
        }
        for(int i=0;i<Box_Row[0];i++) {
            for (int j = 0; j < Box_Column[0]; j++) {
                int box_num = i * Box_Column[0] + j + 1;
                Integer iw = new Integer(box_num);
                Integer ie1 = new Integer(save_id);
                String st = "SELECT Box_id, Status FROM Box WHERE Box_num = " + iw.toString() + " AND Save_id = " + ie1.toString() + ";";
                Statement statement = connection.createStatement();
                ResultSet result1 = statement.executeQuery(st);
                while (result1.next()) {
                    Box[i][j] = result1.getInt("Box_id");
                    System.out.print(Box[i][j]);
                }
            }
        }
    }

    public void Save(int Box_Row[], int Box_Column[], int Box[][], int save_id, int Counter[], String FilePath) throws SQLException {
        Connection connection = DriverManager.getConnection(FilePath);
        String se = "";

        for(int i=0;i<Box_Row[0];i++){
            for(int j=0;j<Box_Column[0];j++){
                Integer xy = new Integer(Box[i][j]);
                se = se + xy.toString();
            }
        }
        System.out.println(se);
        String st = "EXEC getindb_Box @mystring = '"+ se +"';";
        Statement statement = connection.createStatement();
        statement.execute(st);

        Integer ie = new Integer(save_id);
        Integer i1 = new Integer(Counter[0]);
        Integer i2 = new Integer(Box_Column[0]);
        Integer i3 = new Integer(Box_Row[0]);
        String st1 = "EXEC getindb_Save_State @Save_id = "+ie+", @Counter= "+i1+", @Box_Column = "+i2+", @Box_row = "+i3+";";
        Statement statement1 = connection.createStatement();
        statement1.execute(st1);


    }

/*
    public static void main(String[] args) {
        ReadFromDB object= new ReadFromDB();
        String url = "jdbc:sqlserver://DESKTOP-Q2DT0VO;databaseName=abdullah123;integratedSecurity=true;";
        try {

            int[] Box_row = new int[1];
            int[] Box_Column = new int[1];
            int[][] Box= new int [6][3];
            int[] Counter= new int[1];
            int[] Grid_Size = new int[1];
            object.Load(Box_row, Box_Column, Box, 1,Counter,url);
            System.out.println(Counter[0]);
            System.out.println(Box_row[0]);
            System.out.println(Box_Column[0]);
            object.Save(Box_row, Box_Column,Box,3,Counter,url);
        } catch (SQLException e) {
            System.out.println("There was an error!");
            e.printStackTrace();
        }
    }
*/

}

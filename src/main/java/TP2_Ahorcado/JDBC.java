package TP2_Ahorcado;

import java.sql.*;

public class JDBC {
    // nombre de la base de la database "abecedario"

    //tablas "words" y "winners"

    //words tiene un id y un string "word" con una palabra ya guardada
    //winners tiene un id, un string "name", un date "date" y un string "guessedWord" para guardar la palabra con que se jugo

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/abecedario";

    static final String USER = "root";
    static final String PASS = "";


    public Word chooseWord (int random){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        Word chosedWord = new Word();

        try{
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //trae una palabra de la tabla word con un id entre 1 y 10
            stmt = conn.prepareStatement("SELECT word FROM words WHERE id= " + random);

            resultSet = stmt.executeQuery();


            while (resultSet.next()) {
                Word wordAux = new Word();
                wordAux.setId(resultSet.getInt("id"));
                wordAux.setWord(resultSet.getString("word"));
                chosedWord=wordAux;
            }

        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return chosedWord;
    }


    public void saveWinner (String playername, String guessedWord){
        Connection conn = null;
        PreparedStatement stmt = null;
        try{

            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.prepareStatement("INSERT INTO winners(name, date, guessedWord)"
                    + "VALUES(?,?,?)");

            stmt.setString(1, playername);
            stmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            // Since Java 8
            // java.sql.Date.valueOf(java.time.LocalDate.now())

            stmt.setString(3, guessedWord);

            stmt.executeUpdate();


        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
}
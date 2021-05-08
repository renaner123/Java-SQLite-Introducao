package poo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private Connection conect;

    public boolean conect(){
        try{
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/main/java/database/database.db";
            this.conect= DriverManager.getConnection(url);
        }catch(SQLException e ){
            System.err.println(e.getMessage());
            return false;
        }catch (ClassNotFoundException r){
            System.err.println(r.getMessage());
            return false;
        }
        System.out.printf("Conectou");
        return true;
    }

    public boolean deconect(){

        try{
            if(this.conect.isClosed() == false){
                this.conect.close();
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
        System.out.printf("Desconectou");
        return true;
    }
}

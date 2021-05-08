package poo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {

    private Connection conexao;

    public boolean conect(){
        try{
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/main/java/database/database.db";
            this.conexao = DriverManager.getConnection(url);
        }catch(SQLException e ){
            System.err.println(e.getMessage());
            return false;
        }catch (ClassNotFoundException r){
            System.err.println(r.getMessage());
            return false;
        }
        System.out.println("Conectou");
        return true;
    }

    public boolean desconect(){

        try{
            if(this.conexao.isClosed() == false){
                this.conexao.close();
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
        System.out.printf("Desconectou");
        return true;
    }

    public Statement criarStatement(){
        try {
            return this.conexao.createStatement();
        }catch(SQLException e){
            return null;
        }
    }

    public Connection getConexao(){
        return this.conexao;
    }

}

package poo;

import java.sql.*;

public class Conexao {

    private Connection conexao;

    /**
     * Método para se conectar ao banco de dados
     * @return
     */
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

    /**
     * Método para desconectar do banco de dados
     * @return
     */
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

    /**
     * usado para preparar o sql para inserir valores no banco
     * @param sql recebe um valor a ser inserido no sql
     * @return
     */
    public PreparedStatement criarPreparedStatement(String sql){
        try {
            return this.conexao.prepareStatement(sql);
        }catch(SQLException e){
            return null;
        }
    }


    /**
     * Cria os statments para os sqls serem execetutados
     * @return
     */
    public Statement criarStatement(){
        try {
            return this.conexao.createStatement();
        }catch(SQLException e){
            return null;
        }
    }

    /**
     * retonar objeto da conexao
     * @return
     */
    public Connection getConexao(){
        return this.conexao;
    }

}

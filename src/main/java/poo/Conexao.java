package poo;

import java.sql.*;

/**
 * Classe para estabelecer uma conexão com banco de dados
 */

public class Conexao {

    private Connection conexao;

    /**
     * Método para se conectar a um caminho contendo banco de dados SQLite
     * @return
     */
    public boolean conect(){
        try{
            //forName necessário para localizar o driver do jdbc
            Class.forName("org.sqlite.JDBC");
            //caminho onde se encontro o banco de dados(.db)
            String url = "jdbc:sqlite:src/main/java/database/database2.db";
            //faz a conexão com o banco de dados
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
     * Método para se desconectar um banco de dados SQLites
     * @return
     */
    public boolean desconect(){

        try{
            //se a conexão não estiver fechada, fecha a conexão.
            if(this.conexao.isClosed() == false){
                this.conexao.close();
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
        System.out.println("Desconectou");
        return true;
    }

    /**
     * usado para preparar o sql para inserir valores no banco
     * @param sql recebe os campos e ações a serem feitos no banco de dados
     * @return
     */
    public PreparedStatement criarPreparedStatement(String sql){
        //Necessário para fazer alterações no banco de dados.
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
        //Necessário para inserir dados no banco de dados
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

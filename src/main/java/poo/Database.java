package poo;

import java.beans.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {

    private int id;
    private String nome;
    private int idade;
    private Conexao conexaoSQLite = new Conexao();
    private PreparedStatement preparedStatement = null;
    private Statement stmt = null;
    private String sql;


    public int deletar(int id){
        this.id = id;
        this.conexaoSQLite.conect();

        sql = "DELETE FROM tbl_pessoa"
                + " WHERE id = ?;";

        try{
            this.preparedStatement = this.conexaoSQLite.criarPreparedStatement(this.sql);
            preparedStatement.setInt(1,this.id);

            int linhaDeletadas = this.preparedStatement.executeUpdate();
            System.out.println("FORAM DELETADOS " + linhaDeletadas + " REGISTROS");

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                this.preparedStatement.close();
                this.conexaoSQLite.desconect();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return 0;
    };

    public void inserir(Pessoa pessoa1){

        Pessoa pessoaObj = pessoa1;

        this.conexaoSQLite.conect();

        String sqlInsert = " INSERT INTO tbl_pessoa ("
                + "id,"
                + "nome,"
                + "idade"
                + ") VALUES(?,?,?)"
                + ";";

        this.preparedStatement = this.conexaoSQLite.criarPreparedStatement(sqlInsert);

        try{
            this.preparedStatement.setInt(1,pessoaObj.getId());
            this.preparedStatement.setString(2,pessoaObj.getNome());
            this.preparedStatement.setInt(3,pessoaObj.getIdade());
            int resultado = this.preparedStatement.executeUpdate();

            if(resultado == 1){
                System.out.println("Pessoa inserida");
            }else{
                System.out.println("Pessoa não inserida");
            }

        }catch (SQLException e){
            System.out.println("Pessoa não inserida");
        }finally {
            try{
                if(preparedStatement != null){
                    this.preparedStatement.close();
                }
                conexaoSQLite.desconect();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    public void updateNome(Pessoa pessoa1, String newName){

        String sql = "UPDATE tbl_pessoa"
                + " SET "
                + " nome = ?,"
                + " idade = ?"
                + " WHERE id = ?";

        this.conexaoSQLite.conect();

        this.preparedStatement = conexaoSQLite.criarPreparedStatement(sql);

        try{
            preparedStatement = conexaoSQLite.criarPreparedStatement(sql);
            preparedStatement.setString(1,newName);
            preparedStatement.setInt(2,pessoa1.getIdade());
            preparedStatement.setInt(3,pessoa1.getId());

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                preparedStatement.close();
                conexaoSQLite.desconect();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }



    public void updateIdade(Pessoa pessoa1, int newIdade) {
        String sql = "UPDATE tbl_pessoa"
                + " SET "
                + " nome = ?,"
                + " idade = ?"
                + " WHERE id = ?";

        this.conexaoSQLite.conect();

        this.preparedStatement = conexaoSQLite.criarPreparedStatement(sql);

        try {
            preparedStatement = conexaoSQLite.criarPreparedStatement(sql);
            preparedStatement.setString(1, pessoa1.getNome());
            preparedStatement.setInt(2, newIdade);
            preparedStatement.setInt(3, pessoa1.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                conexaoSQLite.desconect();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void updateId(Pessoa pessoa1, int newId) {
        String sql = "UPDATE tbl_pessoa"
                + " SET "
                + " nome = ?,"
                + " idade = ?"
                + " WHERE id = ?";

        this.conexaoSQLite.conect();

        this.preparedStatement = conexaoSQLite.criarPreparedStatement(sql);

        try {
            preparedStatement = conexaoSQLite.criarPreparedStatement(sql);
            preparedStatement.setString(1, pessoa1.getNome());
            preparedStatement.setInt(2, newId);
            preparedStatement.setInt(3, pessoa1.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                conexaoSQLite.desconect();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void deletePessoa(Pessoa pessoa1){

        this.conexaoSQLite.conect();

        String sql = "DELETE FROM tbl_pessoa"
                + " WHERE id = ?;";
        try{
            int id = pessoa1.getId();
            this.preparedStatement = this.conexaoSQLite.criarPreparedStatement(sql);
            this.preparedStatement.setInt(1,id);

            int linhaDeletadas = this.preparedStatement.executeUpdate();

            System.out.println("FORAM DELETADAS" + linhaDeletadas + " REGISTROS");

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                this.preparedStatement.close();
                this.conexaoSQLite.desconect();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }

    }

}

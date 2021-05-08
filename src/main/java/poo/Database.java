package poo;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Classe responsável por fazer alterações no banco de dados.
 */
public class Database {

    private int id;
    private String nome;
    private int idade;
    private Conexao conexaoSQLite = new Conexao();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private Statement stmt = null;
    private String sql;

    /**
     * Deleta uma pessoa de acordo com ID passado
     * @param id
     * @return
     */
    public int deletePessoa(int id){
        this.id = id;
        this.conexaoSQLite.conect();

        sql = "DELETE FROM tbl_pessoa"
                + " WHERE id = ?;";

        try{
            //cria um prepareStatement para poder fazer alteração no banco de dados
            this.preparedStatement = this.conexaoSQLite.criarPreparedStatement(this.sql);
            preparedStatement.setInt(1,this.id);
            //retorna quantidade de dados deletados no banco de daddos
            //Para inserir e deletar é necessário usar o executeUpdate()
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

    /**
     * Insere um novo objeto pessoa no banco de dados
     * @param pessoa1
     */
    public void inserir(Pessoa pessoa1){

        Pessoa pessoaObj = pessoa1;

        this.conexaoSQLite.conect();

        String sqlInsert = " INSERT INTO tbl_pessoa ("
                + "id,"
                + "nome,"
                + "idade"
                + ") VALUES(?,?,?)"
                + ";";
        //cria um prepareStatement para poder fazer alteração no banco de dados
        this.preparedStatement = this.conexaoSQLite.criarPreparedStatement(sqlInsert);

        try{
            this.preparedStatement.setInt(1,pessoaObj.getId());
            this.preparedStatement.setString(2,pessoaObj.getNome());
            this.preparedStatement.setInt(3,pessoaObj.getIdade());
            int resultado = this.preparedStatement.executeUpdate();
            //apenas para testar se foi possível inserir pessoa no banco de dados.
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

    /**
     * Acessa objeto pessoa do respectivo ID e altera o seu Nome
     * @param id
     * @param newName
     */
    public void updateNome(int id, String newName){

        String sql = "UPDATE tbl_pessoa"
                + " SET "
                + " nome = ?,"
                + " idade = ?"
                + " WHERE id = ?";

        Pessoa pessoa1 = this.getPessoa(id);

        this.conexaoSQLite.conect();
        //cria um prepareStatement para poder fazer alteração no banco de dados
        this.preparedStatement = conexaoSQLite.criarPreparedStatement(sql);

        try{
            preparedStatement = conexaoSQLite.criarPreparedStatement(sql);
            //Joga os valores que se deseja receber na instrução sql
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

    /**
     * Acessa objeto pessoa do respectivo ID e altera a sua idade
     * @param id
     * @param newIdade
     */
    public void updateIdade(int id, int newIdade) {
        String sql = "UPDATE tbl_pessoa"
                + " SET "
                + " nome = ?,"
                + " idade = ?"
                + " WHERE id = ?";

        Pessoa pessoa1 = this.getPessoa(id);

        this.conexaoSQLite.conect();
        //cria um prepareStatement para poder fazer alteração no banco de dados
        this.preparedStatement = conexaoSQLite.criarPreparedStatement(sql);

        try {
            preparedStatement = conexaoSQLite.criarPreparedStatement(sql);
            //Joga os valores que se deseja receber na instrução sql
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

    /**
     * Acessa objeto pessoa do respectivo ID e altera o seu ID.
     * @param id
     * @param newId
     */
    public void updateId(int id, int newId) {

         Pessoa pessoa1 = this.getPessoa(id);

         this.deletePessoa(pessoa1);

         pessoa1.setId(newId);

         this.inserir(pessoa1);
    }

    /**
     * Deleta uma pessoa do banco de dados.
     * @param pessoa1
     */
    public void deletePessoa(Pessoa pessoa1){

        this.conexaoSQLite.conect();

        String sql = "DELETE FROM tbl_pessoa"
                + " WHERE id = ?;";
        try{
            int id = pessoa1.getId();
            //cria um prepareStatement para poder fazer alteração no banco de dados
            //Joga os valores que se deseja receber na instrução sql
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

    public void listarBanco(){

        this.conexaoSQLite.conect();

        String query = "SELECT * FROM tbl_pessoa;";

        this.stmt = this.conexaoSQLite.criarStatement();

        try{
            this.resultSet = this.stmt.executeQuery(query);

            while (resultSet.next()){
                System.out.println("DADOS DAS PESSOAS");
                System.out.println("id = " + resultSet.getInt("id"));
                System.out.println("nome = " + resultSet.getString("nome"));
                System.out.println("idade = " + resultSet.getInt("idade"));
                System.out.println("--------");
            }

        }catch (SQLException e){

        }finally {
            try{
                this.resultSet.close();
                this.stmt.close();
                this.conexaoSQLite.desconect();
            }catch (SQLException ex){
                System.out.println("Erro ao fechar");
            }
        }
    }

    /**
     * retorar um objeto pessoa
     * @param id
     * @return
     */
    private Pessoa getPessoa(int id){
        this.conexaoSQLite.conect();
        Pessoa pessoaReturn = new Pessoa();

        String sql = "SELECT * "
                + " FROM tbl_pessoa"
                + " WHERE id = ?";

        try{
            //cria um prepareStatement para poder fazer alteração no banco de dados
            this.preparedStatement = this.conexaoSQLite.criarPreparedStatement(sql);
            this.preparedStatement.setInt(1,id);
            this.resultSet = this.preparedStatement.executeQuery();

            while(resultSet.next()){
                //Joga os valores que se deseja receber na instrução sql
                pessoaReturn.setId(this.resultSet.getInt("id"));
                pessoaReturn.setNome(this.resultSet.getString("nome"));
                pessoaReturn.setIdade(this.resultSet.getInt("idade"));
            }
            return pessoaReturn;

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                resultSet.close();
                conexaoSQLite.desconect();
            }catch(SQLException ex){
                ex.printStackTrace();
            }

        }

        return pessoaReturn;
    }

}

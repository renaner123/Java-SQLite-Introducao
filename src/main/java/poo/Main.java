package poo;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        Conexao conexaoSQLite = new Conexao();

        conexaoSQLite.conect();

        ResultSet resultSet = null;
        //Como vai ser passado parâmetros, precisa usar prepareStatment
        PreparedStatement preparedStatement = null;

        String sql = "SELECT * "
                + " FROM tbl_pessoa"
                + " WHERE id = ?";

        try{
            int idPessoa = 2;

            preparedStatement = conexaoSQLite.criarPreparedStatement(sql);
            preparedStatement.setInt(1,idPessoa);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                System.out.println("PESSOA SELECIONADA");
                System.out.println("ID = " + resultSet.getInt("id"));
                System.out.println("NOME = " + resultSet.getString("nome"));
                System.out.println("IDADE = " + resultSet.getInt("idade"));
            }

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













        //tabela de dados.
//        ResultSet resultSet = null;
//        //prepara o sql
//        Statement statement = null;
//
//        conexaoSQLite.conect();
//
//        String query = "SELECT * FROM tbl_pessoa;";
//
//        statement = conexaoSQLite.criarStatement();
//
//        try{
//            resultSet = statement.executeQuery(query);
//            while (resultSet.next()){
//                System.out.println("DADOS DAS PESSOAS");
//                System.out.println("id = " + resultSet.getInt("id"));
//                System.out.println("nome = " + resultSet.getString("nome"));
//                System.out.println("idade = " + resultSet.getInt("idade"));
//                System.out.println("--------");
//            }
//
//        }catch (SQLException e){
//
//        }finally {
//            try{
//                resultSet.close();
//                statement.close();
//                conexaoSQLite.desconect();
//            }catch (SQLException ex){
//                System.out.println("Erro ao fechar");
//            }
//        }



//        CriarBancoSQLite criarBancoSQLite = new CriarBancoSQLite(conexaoSQLite);
//        criarBancoSQLite.criarTabelaPessoa();
//
//        Pessoa pessoa1 = new Pessoa();
//        pessoa1.setId(1);
//        pessoa1.setIdade(26);
//        pessoa1.setNome("Renan");
//
//        conexaoSQLite.conect();
//
//        String sqlInsert = " INSERT INTO tbl_pessoa ("
//                + "id,"
//                + "nome,"
//                + "idade"
//                + ") VALUES(?,?,?)"
//                + ";";
//        PreparedStatement preparedStatement = conexaoSQLite.criarPreparedStatement(sqlInsert);
//
//        try{
//            preparedStatement.setInt(1,pessoa1.getId());
//            preparedStatement.setString(2,pessoa1.getNome());
//            preparedStatement.setInt(3,pessoa1.getIdade());
//
//            int resultado = preparedStatement.executeUpdate();
//
//            if(resultado == 1){
//                System.out.println("Pessoa inserida");
//            }else{
//                System.out.println("Pessoa não inserida");
//            }
//
//        }catch (SQLException e){
//            System.out.println("Pessoa não inserida");
//        }finally {
//            if(preparedStatement != null){
//                preparedStatement.close();
//            }
//            conexaoSQLite.desconect();
//        }
    }
}

package poo;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        Conexao conexaoSQLite = new Conexao();

        conexaoSQLite.conect();

        ResultSet resultSet = null;
        //Como vai ser passado parâmetros, precisa usar prepareStatment
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE tbl_pessoa"
                + " SET "
                + " nome = ?,"
                + " idade = ?"
                + " WHERE id = ?";

        try{
            String nome = "Jose";
            int idade = 40;
            int id = 2;

            preparedStatement = conexaoSQLite.criarPreparedStatement(sql);
            preparedStatement.setString(1,nome);
            preparedStatement.setInt(2,idade);
            preparedStatement.setInt(3,id);

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
}

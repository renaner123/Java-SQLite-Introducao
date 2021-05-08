package poo;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        Conexao conexaoSQLite = new Conexao();

        conexaoSQLite.conect();

        //Como vai ser passado parâmetros, precisa usar prepareStatment
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM tbl_pessoa"
                + " WHERE id = ?;";

        try{
            int id = 2;
            preparedStatement = conexaoSQLite.criarPreparedStatement(sql);
            preparedStatement.setInt(1,id);

            int linhaDeletadas = preparedStatement.executeUpdate();

            System.out.println("FORAM DELETADAS" + linhaDeletadas + " REGISTROS");

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

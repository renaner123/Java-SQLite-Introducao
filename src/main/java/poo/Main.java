package poo;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        Conexao conexaoSQLite = new Conexao();
        CriarBancoSQLite criarBancoSQLite = new CriarBancoSQLite(conexaoSQLite);
        criarBancoSQLite.criarTabelaPessoa();

        Pessoa pessoa1 = new Pessoa();
        pessoa1.setId(1);
        pessoa1.setIdade(26);
        pessoa1.setNome("Renan");

        conexaoSQLite.conect();

        String sqlInsert = " INSERT INTO tbl_pessoa ("
                + "id,"
                + "nome,"
                + "idade"
                + ") VALUES(?,?,?)"
                + ";";
        PreparedStatement preparedStatement = conexaoSQLite.criarPreparedStatement(sqlInsert);

        try{
            preparedStatement.setInt(1,pessoa1.getId());
            preparedStatement.setString(2,pessoa1.getNome());
            preparedStatement.setInt(3,pessoa1.getIdade());

            int resultado = preparedStatement.executeUpdate();

            if(resultado == 1){
                System.out.println("Pessoa inserida");
            }else{
                System.out.println("Pessoa não inserida");
            }

        }catch (SQLException e){
            System.out.println("Pessoa não inserida");
        }finally {
            if(preparedStatement != null){
                preparedStatement.close();
            }
            conexaoSQLite.desconect();
        }
    }
}

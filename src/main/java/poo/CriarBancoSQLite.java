package poo;

import java.sql.SQLException;
import java.sql.Statement;

public class CriarBancoSQLite {
    private final Conexao conexaoSQLite;

    public CriarBancoSQLite(Conexao pConexaoSQLite){
        conexaoSQLite = pConexaoSQLite;
    }

    /**
     * Cria uma tabela pessoa com campos fixos
     */
    public void criarTabelaPessoa(){
        String sql = "CREATE TABLE IF NOT EXISTS tbl_pessoa"
                + "("
                + "id integer PRIMARY KEY,"
                + "nome text NOT NULL,"
                + "idade integer"
                + ");";

        boolean conectou = false;

        try{
            conectou = conexaoSQLite.conect();
            Statement stmt = conexaoSQLite.criarStatement();
            stmt.execute(sql);
            System.out.println("Criou tabela pessoa");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }finally {
            // Sempre bom fechar a conexão.
            if(conectou){
                conexaoSQLite.desconect();
            }
        }
    }
}

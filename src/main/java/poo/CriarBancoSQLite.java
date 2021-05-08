package poo;

import java.sql.SQLException;
import java.sql.Statement;
/**
 * Classe responsável por criar uma nova tabela no banco de dados.
 * Nesse caso, cria uma Tabela Pessoa com ID, Nome e Idade
 */
public class CriarBancoSQLite {
    private final Conexao conexaoSQLite;

    public CriarBancoSQLite(Conexao pConexaoSQLite){
        conexaoSQLite = pConexaoSQLite;
    }

    /**
     * Cria uma tabela pessoa com campos fixos(ID, Nome e Idade)
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
            //conecta ao banco de dados
            conectou = conexaoSQLite.conect();
            //cria um statement para poder inserir informações no banco de dados
            Statement stmt = conexaoSQLite.criarStatement();
            //executa as intruções sql para criar a tabela
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

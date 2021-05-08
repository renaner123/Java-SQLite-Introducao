package poo;

public class Main {

    public static void main(String[] args){

        Conexao conexaoSQLite = new Conexao();
        CriarBancoSQLite criarBancoSQLite = new CriarBancoSQLite(conexaoSQLite);
        criarBancoSQLite.criarTabelaPessoa();
    }
}

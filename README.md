[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/renaner123/Java-SQLite-Introducao/blob/master/LICENSE)

# Exemplos de Java com SQLite 

Exemplos de utilização do SQLite usando jdbc em JAVA usando o software IntelliJ IDEA em um projeto Gradle.

### Features

- [x] Criar tabela
- [x] Inserir dado na tabela
- [x] Alterarar dado da tabela
- [x] Deletar dado da tabela


## 🚀 Execução
Para executar esta aplicação, bata executar a classe Main. 

Será apresentado as seguintes opções:

```shell
Bem vindo ao exemplo de uso de Java & SQLite usando jdbc
######################
1. Inserir pessoa no banco de dados
2. Alterar Pessoa
3. Deletar Pessoa
```

### 📋 Instruções SQL utilizadas

+ Criar Tabela com nome tbl_pessoa contendo id, nome e idade

```SQLite
"CREATE TABLE IF NOT EXISTS tbl_pessoa"
        + "("
        + "id integer PRIMARY KEY,"
        + "nome text NOT NULL,"
        + "idade integer"
        + ");";
```
+ Inserir dados da Pessoa na Tabela

```
"INSERT INTO tbl_pessoa ("
        + "id,"
        + "nome,"
        + "idade"
        + ") VALUES(?,?,?)"
        + ";";
```

+ Alterar algum dado de Pessoa na tabela
```
"UPDATE tbl_pessoa"
        + " SET "
        + " nome = ?,"
        + " idade = ?"
        + " WHERE id = ?";
```

+ Deletar uma pessoa da tabela
```
"DELETE FROM tbl_pessoa"
        + " WHERE id = ?;";
```

## 🛠️ Construído com

Ferramentas utilizadas.

* [IntelliJ](https://www.jetbrains.com/pt-br/idea/) - O IDE utilizado
* [DB Browser(SQLite)](https://sqlitebrowser.org/) - Visualizador de bando de dados
* [sqlite-jdbc-3.7.2](http://www.java2s.com/Code/Jar/s/Downloadsqlitejdbc372jar.htm) - JAR do SQLite utilzado 








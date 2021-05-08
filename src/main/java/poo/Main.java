package poo;

import java.sql.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws SQLException {

        Scanner teclado_int = new Scanner(System.in);
        Scanner teclado_string = new Scanner(System.in);
        Database database = new Database();
        int idade = 0;
        int id = 0;
        String nome = "";

        int op=0;
        String input = "";

        System.out.println("Bem vindo ao exemplo de uso de Java & SQLite usando jdbc");
        System.out.println("######################");

        try{
        while(true){
            System.out.println("1. Inserir pessoa no banco de dados");
            System.out.println("2. Alterar Pessoa");
            System.out.println("3. Deletar Pessoa");

            op=teclado_int.nextInt();

            if(op==1){
                Pessoa pessoa = new Pessoa();
                System.out.println("Digite o id da pessoa");
                op = teclado_int.nextInt();
                pessoa.setId(op);
                System.out.println("Digite o nome da pessoa");
                input = teclado_string.nextLine();
                pessoa.setNome(input);
                System.out.println("Digite a idade da pessoa");
                op = teclado_int.nextInt();
                pessoa.setIdade(op);
                database.inserir(pessoa);

            }else if(op==2){
                Pessoa pessoa = new Pessoa();

                System.out.println("Digite o ID a ser alterado");
                op = teclado_int.nextInt();
                pessoa.setId(op);

                System.out.println("1. Alterar id");
                System.out.println("2. Alterar idade");
                System.out.println("3. Alterar nome");
                op = teclado_int.nextInt();

                if(op==1){
                    System.out.println("Digite o novo ID");
                    op = teclado_int.nextInt();
                    database.updateId(pessoa.getId(),op);
                }else if(op==2){
                    System.out.println("Digite a nova idade");
                    op = teclado_int.nextInt();
                    database.updateIdade(pessoa.getId(),op);

                }else if(op==3){
                    System.out.println("Digite o novo nome");
                    input = teclado_string.next();
                    database.updateNome(pessoa.getId(),input);
                }

            }else if(op==3){
                System.out.println("Digite o ID da pessoa a ser deletada");
                op = teclado_int.nextInt();
                database.deletePessoa(op);
            }
        }

        }catch (java.util.InputMismatchException e){
            System.out.printf("Erro " + e.toString() + " ### " + "Digite um número interno \n");
        }





    }
}

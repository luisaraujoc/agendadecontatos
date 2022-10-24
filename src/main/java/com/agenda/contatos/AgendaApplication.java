package com.agenda.contatos;

import java.sql.SQLException;
import java.util.Scanner;

import com.agenda.contatos.DAO.ContatosDAO;
import com.agenda.contatos.Model.Contatos;

public class AgendaApplication {
    
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Bem vindo a sua agenda de contatos!");
        System.out.println("Escolha uma das opções abaixo:");
        System.out.println("1 - Cadastrar contato");
        System.out.println("2 - Listar contatos");

        int opcao = sc.nextInt();

        if(opcao ==1){
            cadastrarContatos();
        }else{
            listarContatos();
        }


        sc.close();
    }


    public static void cadastrarContatos() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Contatos contato = new Contatos();

        System.out.println("Digite o nome do contato: ");
        contato.setNome(sc.nextLine());

        System.out.println("Digite o telefone do contato: ");
        contato.setTelefone(sc.nextLine());

        System.out.println("Digite o email do contato: ");
        contato.setEmail(sc.nextLine());

        ContatosDAO.salvarContato(contato);
        sc.close();
    }

    public static void listarContatos(){
        System.out.println("|----------------------------------------|");
        System.out.println("|------------ Lista de contatos ----------|");
        for(Contatos contato : ContatosDAO.getContatos()){
            System.out.println("|----------------------------------------|");
            System.out.println("| ID: " + contato.getId());
            System.out.println("| Nome: " + contato.getNome());
            System.out.println("| Telefone: " + contato.getTelefone());
            System.out.println("| Email: " + contato.getEmail());
            System.out.println("|----------------------------------------|");
        }
    }
}

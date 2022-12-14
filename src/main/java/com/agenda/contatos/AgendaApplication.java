package com.agenda.contatos;

import java.sql.SQLException;
import java.util.Scanner;

import com.agenda.contatos.DAO.ContatosDAO;
import com.agenda.contatos.Model.Contatos;

public class AgendaApplication {
    
    /**
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Bem vindo a sua agenda de contatos!");
        System.out.println("Escolha uma das opções abaixo:");
        System.out.println("1 - Cadastrar contato");
        System.out.println("2 - Listar contatos");
        System.out.println("3 - Atualizar contato");
        System.out.println("4 - Deletar contato");  

        int opcao = sc.nextInt();

        if(opcao==1){
            cadastrarContatos();
            System.out.println("|----------------------------------------|");
            System.out.println("| Contato cadastrado com sucesso! |");
            System.out.println("|----------------------------------------|\n\n");
            listarContatos();
        }
        else if(opcao==2){
            listarContatos();
        }else if(opcao==3){
            atualizarContato();
        }else if(opcao==4){
            deletarContato();
        }else {
            System.out.println("Opção inválida!");
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
        System.out.println("|-----------------------------------------|");
        System.out.println("|------------ Lista de contatos ----------|");
        System.out.println("|-----------------------------------------|");
        // aqui ele vai ler cada contato da lista de contatos e com base nisso, vai puxar a informação de cada contato e exibir na tela
        for(Contatos contato : ContatosDAO.getContatos()){
            System.out.println("| ID: " + contato.getId());
            System.out.println("| Nome: " + contato.getNome());
            System.out.println("| Telefone: " + contato.getTelefone());
            System.out.println("| Email: " + contato.getEmail());
            System.out.println("|-----------------------------------------|");
        }
    }

    public static void atualizarContato() throws SQLException{
        listarContatos();
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o ID do contato que deseja atualizar: ");
        int id = sc.nextInt();
        sc.nextLine();

        Contatos contato = ContatosDAO.getContatoById(id);

        System.out.println("Digite o novo nome do contato: ");
        contato.setNome(sc.nextLine());

        System.out.println("Digite o novo telefone do contato: ");
        contato.setTelefone(sc.nextLine());

        System.out.println("Digite o novo email do contato: ");
        contato.setEmail(sc.nextLine());

        ContatosDAO.atualizarContato(contato);
        listarContatos();
        sc.close();
    }

    public static void deletarContato() throws SQLException{
        listarContatos();
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o ID do contato que deseja deletar: ");
        int id = sc.nextInt();
        sc.nextLine();

        ContatosDAO.deletarContato(id);
        listarContatos();
        sc.close();
    }
}

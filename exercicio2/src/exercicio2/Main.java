package app;

import dao.ProdutoDAO;
import model.Produto;

import java.util.List;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ProdutoDAO dao = new ProdutoDAO();

        int opcao = 0;

        while (opcao != 5) {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Listar");
            System.out.println("2 - Inserir");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("5 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1: // LISTAR
                    List<Produto> lista = dao.listar();
                    System.out.println("\n--- Produtos ---");
                    for (Produto p : lista) {
                        System.out.println(p);
                    }
                    break;

                case 2: // INSERIR
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Preço: ");
                    double preco = sc.nextDouble();

                    dao.inserir(new Produto(nome, preco));
                    break;

                case 3: // ATUALIZAR
                    System.out.print("ID do produto: ");
                    int idA = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Novo nome: ");
                    String nomeA = sc.nextLine();

                    System.out.print("Novo preço: ");
                    double precoA = sc.nextDouble();

                    dao.atualizar(new Produto(idA, nomeA, precoA));
                    break;

                case 4: // EXCLUIR
                    System.out.print("ID para excluir: ");
                    int idE = sc.nextInt();
                    dao.excluir(idE);
                    break;

                case 5:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        sc.close();
    }
}

package app;

import static spark.Spark.*;

import dao.ProdutoDAO;
import model.Produto;

public class App {

    public static void main(String[] args) {

        ProdutoDAO dao = new ProdutoDAO();

        port(4567);
        staticFiles.location("/public"); // aponta para /resources/public

        // rota da home → formulário
        get("/", (req, res) -> {
            res.redirect("/form.html");
            return null;
        });

        // inserir produto
        post("/inserir", (req, res) -> {
            String nome = req.queryParams("nome");
            double preco = Double.parseDouble(req.queryParams("preco"));

            dao.inserir(new Produto(nome, preco));

            return "<h3>Produto cadastrado com sucesso!</h3><a href='/'>Voltar</a>";
        });

        // listar produtos
        get("/listar", (req, res) -> {
            StringBuilder sb = new StringBuilder("<h2>Produtos cadastrados</h2>");

            for (Produto p : dao.listar()) {
                sb.append(p.getId())
                  .append(" - ")
                  .append(p.getNome())
                  .append(" - R$ ")
                  .append(p.getPreco())
                  .append("<br>");
            }

            sb.append("<br><br><a href='/'>Voltar</a>");

            return sb.toString();
        });
    }
}

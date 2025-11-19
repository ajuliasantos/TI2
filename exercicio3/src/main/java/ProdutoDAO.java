package dao;

import model.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private final String URL = "jdbc:postgresql://localhost:5432/seubanco";
    private final String USER = "postgres";
    private final String PASS = "senha";

    public void inserir(Produto p) {
        String sql = "INSERT INTO produto (nome, preco) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Erro ao inserir produto: " + e.getMessage());
        }
    }

    public List<Produto> listar() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto ORDER BY id";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto p = new Produto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDouble("preco")
                );
                lista.add(p);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }

        return lista;
    }
}

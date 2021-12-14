package DAO;

import Factory.ConnectionFactory;
import model.Produto;
import model.TipoProduto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO() {this.connection = new ConnectionFactory().getConnection();}

    //TABLE[]
    public void criarTabelaProduto(){
        String sql = "CREATE TABLE IF NOT EXISTS produto(" +
                "idProduto INT PRIMARY KEY AUTO_INCREMENT, " +
                "nome VARCHAR(50) NOT NULL, " +
                "preco DECIMAL(10,2), " +
                "idTipoProduto INT, " +
                "CONSTRAINT fk_idTipoProduto FOREIGN KEY (idTipoProduto) " +
                "REFERENCES tipoproduto(idTipoProduto));";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            statement.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    //CRETE[]
    public Produto cadastrarProduto(Produto produtoModel){
        String sql = "INSERT INTO produto(nome, preco, idTipoProduto) VALUES (?,?,?)";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,produtoModel.getNome());
            statement.setDouble(2,produtoModel.getPreco());
            statement.setInt(3,produtoModel.getTipoProduto().getIdTipoProduto());
            statement.execute();
            statement.close();
            return produtoModel;

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    //READ[]
    public List<Produto> listarProdutos(){
        String sql = "SELECT * FROM produto";
        try{
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            ArrayList produtos = new ArrayList();

            while(resultSet.next()){
                Produto produto = new Produto();
                TipoProdutoDAO tipoProdutoDAO = new TipoProdutoDAO();

                produto.setIdProduto(resultSet.getInt("idProduto"));
                produto.setNome(resultSet.getString("nome"));
                produto.setPreco(resultSet.getDouble("preco"));


                TipoProduto tipoProduto = tipoProdutoDAO.buscaTipoProdutoPorId(resultSet.getInt("idTipoProduto"));  //Entender está lógica
                produto.setTipoProduto(tipoProduto);

                produtos.add(produto);
            }
            return produtos;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    //UPDATE[]
    public void updateProduto(Produto produtoModel){
        String sql = "UPDATE produto SET nome = ?, preco = ? WHERE idProduto = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1,produtoModel.getNome());
            statement.setDouble(2,produtoModel.getPreco());
            statement.setInt(3,produtoModel.getIdProduto());

            statement.execute();
            statement.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
    //DELETE[]
    public void deletaProduto(Produto produtoModel) {

        String sql = "DELETE FROM produto WHERE idProduto = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, produtoModel.getIdProduto());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //SEARCH[]
    public Produto buscaProdutoPorId(int idProduto){
        String sql = "SELECT * FROM produto WHERE idProduto = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idProduto);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                Produto produto = new Produto();
                produto.setIdProduto(resultSet.getInt("idProduto"));
                produto.setNome(resultSet.getString("nome"));
                produto.setPreco(resultSet.getDouble("preco"));

                //resultSet.getInt("idTipoProduto")
                TipoProdutoDAO tpDAO = new TipoProdutoDAO();
                TipoProduto tipoProduto = tpDAO.buscaTipoProdutoPorId(resultSet.getInt("idTipoProduto"));
                produto.setTipoProduto(tipoProduto);

                return produto;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

}

package DAO;

import Factory.ConnectionFactory;
import model.TipoProduto;

import java.sql.*;
import java.util.List;

public class TipoProdutoDAO {

    private Connection connection;
    public TipoProdutoDAO() {this.connection = new ConnectionFactory().getConnection();}

    //TABLE[]
    public void criaTabelaTipoProduto() {
        String sql = "CREATE TABLE IF NOT EXISTS tipoproduto(" +
                "idTipoProduto INT primary key auto_increment," +
                "nome VARCHAR(50) NOT NULL)";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            statement.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    //CREATE[]
    public void cadastraTipoProduto(TipoProduto tipoProduto) {    //Cadastrar o tipo do produto primeiro do que o produto
        String sql = "INSERT INTO tipoproduto(nome) VALUE (?)";
        try{
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,tipoProduto.getNome());
            statement.execute();


            ResultSet resultSet = statement.getGeneratedKeys();

            while (resultSet.next()){
                tipoProduto.setIdTipoProduto(resultSet.getInt(1));
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    //READ[]
    //public List<TipoProduto> listaTiposProduto() {
    //    String sql = "SELECT * FROM tipoproduto";
    //}
    //UPDATE[]
    public void updateTipoProduto(){
        String sql = "UPDATE tipoproduto SET nome = ? WHERE idTipoProduto = ?";
    }
    //DELETE[]
    public void deletaTipoProduto(TipoProduto tipoProduto) {
        String sql = "DELETE FROM tipoproduto WHERE idTipoProduto = ?";
    }
    //SEARCH[]
    public TipoProduto buscaTipoProdutoPorId(int idTipoProduto) {
        String sql = "SELECT * FROM tipoproduto WHERE idTipoProduto = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idTipoProduto);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                TipoProduto tipoProduto = new TipoProduto();
                tipoProduto.setIdTipoProduto(resultSet.getInt("idTipoProduto"));
                tipoProduto.setNome(resultSet.getString("nome"));

                return tipoProduto;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

}

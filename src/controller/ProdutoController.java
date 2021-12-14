package controller;

import DAO.ProdutoDAO;
import model.Produto;
import java.util.List;

//Pesquisar o que é a classe "Response"
//O que é @PathParam

public class ProdutoController {

    ProdutoDAO produtoDAO = new ProdutoDAO();

    public void CreateTableProdutos() {produtoDAO.criarTabelaProduto();}
    public void CreateProduto(Produto produto) {produtoDAO.cadastrarProduto(produto);}
    public List<Produto> ReadProdutos() {return this.produtoDAO.listarProdutos();}
    public void UpdateProduto(Produto produto){produtoDAO.updateProduto(produto);}
    public void DeletaProduto(int idProduto){
        Produto produto = produtoDAO.buscaProdutoPorId(idProduto);
        produtoDAO.deletaProduto(produto);}

}

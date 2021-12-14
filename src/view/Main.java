package view;

import DAO.ProdutoDAO;
import DAO.TipoProdutoDAO;

public class Main {
    public static void main(String[] args) {

        ProdutoDAO produtoDAO = new ProdutoDAO();
        TipoProdutoDAO tipoProdutoDAO = new TipoProdutoDAO();

        tipoProdutoDAO.criaTabelaTipoProduto();
        produtoDAO.criarTabelaProduto();

        MenuView MenuView = new MenuView();
        MenuView.MenuPrincipal();

    }
}

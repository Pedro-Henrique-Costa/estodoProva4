package view;

import javax.swing.*;

public class MenuView {

    ProdutoView produtoView = new ProdutoView();

    public void MenuPrincipal(){
        String leitura = JOptionPane.showInputDialog(
                " [1] - Cadastrar Produto e Tipo do Produto\n"+
                " [2] - Escolher um Tipo de Produto já existente e Cadastrar Novo Produto\n"+
                " [3] - Ver lista de Produtos\n"+
                " [4] - Editar Produto\n" +
                " [5] - Editar Tipo de Produtos\n"+
                " [5] - Deletar Produto\n" +
                " [6] - Deletar Tipo Produto");

        switch (Integer.parseInt(leitura)){
            case 1 -> produtoView.CreateProdutoView();
            case 2 -> produtoView.ChooseTipoProdutoView();
            case 3 -> produtoView.ReadProdutoView();
            case 4 -> produtoView.UpdateProdutoView();
            case 5 -> produtoView.DeleteProdutoView();
        }
    }
}



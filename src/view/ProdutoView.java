package view;

import controller.ProdutoController;
import controller.TipoProdutoController;
import model.Produto;
import model.TipoProduto;

import javax.swing.*;
import java.util.List;

public class ProdutoView {

    Produto produtoModel = new Produto();
    TipoProduto tipoProdutoModel = new TipoProduto();
    ProdutoController produtoController = new ProdutoController();
    TipoProdutoController tipoprodutoController = new TipoProdutoController();


    public void CreateProdutoView(){

        String leituraNome = JOptionPane.showInputDialog("Nome do Produto: ");
        produtoModel.setNome(leituraNome);

        String leituraPreco = JOptionPane.showInputDialog("Preço do Produto: ");
        produtoModel.setPreco(Double.parseDouble(leituraPreco));

        String leituraTipo = JOptionPane.showInputDialog("Tipo do Produto: ");
        tipoProdutoModel.setNome(leituraTipo);
        produtoModel.setTipoProduto(tipoProdutoModel);

        tipoprodutoController.cadastraTipoProduto(tipoProdutoModel);
        produtoController.CreateProduto(produtoModel);

        System.out.println("|---------------------------------------------|");
        System.out.println("|Produto] Nome: "+ produtoModel.getNome());
        System.out.println("|Produto] Preço: "+produtoModel.getPreco());
        System.out.println("|Produto] Tipo Produto: "+produtoModel.getTipoProduto());
        System.out.println("|Produto] ID Produto: "+produtoModel.getIdProduto());
        System.out.println("|Produto] ID Tipo Produto: "+ produtoModel.getTipoProduto().getIdTipoProduto());
        System.out.println("|---------------------------------------------|");
        System.out.println("|Tipo Produto] Nome: "+ tipoProdutoModel.getNome());
        System.out.println("|Tipo Produto] ID: "+ tipoProdutoModel.getIdTipoProduto());
    }

    public List<Produto> ReadProdutoView(){

        List<Produto> produtos =  produtoController.ReadProdutos();

        for (Produto produto : produtos) {
            System.out.println("|Id Tipo Produto: " + produto.getTipoProduto().getIdTipoProduto() + "| Nome Tipo Produto: " + produto.getTipoProduto().getNome() + "|Id Produto: " + produto.getIdProduto() + "| Nome Produto: " + produto.getNome() + "|Preço Produto: " + produto.getPreco());
        }
        return produtos;
    }

    public void UpdateProdutoView(){
        List<Produto> produtos = this.ReadProdutoView();
        String leituraIdProduto = JOptionPane.showInputDialog("Digite o Id do Produto a ser Editado: ");
        int idProduto = Integer.parseInt(leituraIdProduto);
        for(int i = 0; i< produtos.size(); i++){
            if(produtos.get(i).getIdProduto() == idProduto){
                Produto produto = produtos.get(i);
                String leituraNome = JOptionPane.showInputDialog("Digite o Nome do Produto a ser Editado: ");
                produto.setNome(leituraNome);
                String leituraPreco = JOptionPane.showInputDialog("Digite o Preco do Produto a ser Editado: ");
                produto.setPreco(Double.parseDouble(leituraPreco));
                produtoController.UpdateProduto(produto);
            }
        }
    }
    public void DeleteProdutoView(){
        List<Produto> produtos = this.ReadProdutoView();
        String leituraIdProduto = JOptionPane.showInputDialog("Digite o Id do Produto a ser Deletado: ");
        int idProduto = Integer.parseInt(leituraIdProduto);
        for(int i = 0; i< produtos.size(); i++){
            if(produtos.get(i).getIdProduto() == idProduto){
                produtoController.DeletaProduto(produtos.get(i).getIdProduto());
            }
        }
    }

    public void ChooseTipoProdutoView(){
        int idTipoProduto;
        List<Produto> produtos = this.ReadProdutoView();

        String leituraIdTipoProduto = JOptionPane.showInputDialog("  Informe ID do Tipo do Produto escolhido: ");
        idTipoProduto = Integer.parseInt(leituraIdTipoProduto);

        for (int i = 0; i< produtos.size(); i++) {
            if (produtos.get(i).getTipoProduto().getIdTipoProduto() == idTipoProduto) {
                tipoProdutoModel = produtos.get(i).getTipoProduto();
                produtoModel.setTipoProduto(tipoProdutoModel);
                String leituraProdutoNome = JOptionPane.showInputDialog("  Informe Nome do Produto escolhido: ");
                produtoModel.setNome(leituraProdutoNome);
                String leituraProdutoPreco = JOptionPane.showInputDialog("  Informe Preço do Produto escolhido: ");
                produtoModel.setPreco(Double.parseDouble(leituraProdutoPreco));

                produtoController.CreateProduto(produtoModel);
            }
        }
    }

}

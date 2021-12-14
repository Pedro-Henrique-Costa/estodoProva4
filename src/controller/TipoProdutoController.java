package controller;

import DAO.TipoProdutoDAO;
import model.TipoProduto;

import java.util.List;

public class TipoProdutoController {

    TipoProdutoDAO tipoProdutoDAO = new TipoProdutoDAO();

    public void criaTabelaTipoProduto() {tipoProdutoDAO.criaTabelaTipoProduto();}
    public void cadastraTipoProduto(TipoProduto tipoProduto) {tipoProdutoDAO.cadastraTipoProduto(tipoProduto);}
    //public void listaTipoProduto() {List<TipoProduto> tiposProduto = tipoProdutoDAO.listaTiposProduto();}  //[VER METODO DEPOIS]
    public void editarTipoProduto(){}

}

package br.com.nonnapizza.serlvet;

import br.com.nonnapizza.dao.ProdutosDao;
import br.com.nonnapizza.model.Produtos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create-produto")
public class CreateProdutoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String tipo = request.getParameter("tipo");
        float valor = Float.parseFloat(request.getParameter("valor"));
        String descricao = request.getParameter("descricao");

        Produtos produto = new Produtos();

        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setValor(valor);
        produto.setTipo(tipo);

        ProdutosDao produtoDao = new ProdutosDao();

        produtoDao.inserirProduto(produto);

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}

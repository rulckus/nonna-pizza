package br.com.nonnapizza.serlvet;

import br.com.nonnapizza.dao.ProdutosDao;
import br.com.nonnapizza.model.Produtos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/find-all-products")
public class ListProdutosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Produtos> produtos = new ProdutosDao().listarProdutos();

        req.setAttribute("produtos", produtos);

        req.getRequestDispatcher("produtosdashboard.jsp").forward(req, resp);
    }
}

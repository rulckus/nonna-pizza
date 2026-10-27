package br.com.nonnapizza.serlvet;

import br.com.nonnapizza.dao.PizzaDao;
import br.com.nonnapizza.dao.ProdutosDao;
import br.com.nonnapizza.model.Carrinho;
import br.com.nonnapizza.model.CarrinhoItem;
import br.com.nonnapizza.model.Pizza;
import br.com.nonnapizza.model.Produtos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/carrinho")
public class CarrinhoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("carrinho.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
        if (carrinho == null) {
            carrinho = new Carrinho();
            session.setAttribute("carrinho", carrinho);
        }

        String acao = req.getParameter("acao");
        String tipo = req.getParameter("tipo");
        String idParam = req.getParameter("id");

        try {
            Long id = Long.valueOf(idParam);
            String chave = tipo + "-" + id;

            switch (acao) {
                case "adicionar":
                    CarrinhoItem item = montarItem(tipo, id);   // preço vem do banco
                    if (item != null) carrinho.adicionar(item);
                    break;
                case "mais":    carrinho.alterar(chave, 1);  break;
                case "menos":   carrinho.alterar(chave, -1); break;
                case "remover": carrinho.remover(chave);     break;
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("carrinho");   // evita reenviar o form ao dar F5
    }

    private CarrinhoItem montarItem(String tipo, Long id) throws SQLException {
        CarrinhoItem item = new CarrinhoItem();
        item.setTipo(tipo);
        item.setId(id);
        item.setQuantidade(1);

        if ("pizza".equals(tipo)) {
            Pizza p = new PizzaDao().acharPizzaId(id);
            if (p == null) return null;
            item.setNome(p.getSabor());
            item.setPreco(p.getValor());
        } else if ("produto".equals(tipo)) {
            Produtos p = new ProdutosDao().acharProdutoId(id);
            if (p == null) return null;
            item.setNome(p.getNome());
            item.setPreco(p.getValor());
        } else {
            return null;
        }
        return item;
    }
}

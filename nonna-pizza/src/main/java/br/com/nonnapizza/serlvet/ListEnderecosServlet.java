package br.com.nonnapizza.serlvet;

import br.com.nonnapizza.dao.EnderecoDao;
import br.com.nonnapizza.model.Cliente;
import br.com.nonnapizza.model.Endereco;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/enderecos")
public class ListEnderecosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cliente clienteLogado = (Cliente) req.getSession().getAttribute("loggeduser");

        if (clienteLogado == null) {
            req.getRequestDispatcher("login.html").forward(req, resp);
        }else {
            List<Endereco> enderecos = new EnderecoDao().listarEnderecos(clienteLogado.getId());
       
            req.setAttribute("enderecos", enderecos);

            req.getRequestDispatcher("endereco.jsp").forward(req, resp);
        }
    }
}

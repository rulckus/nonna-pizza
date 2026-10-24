package br.com.nonnapizza.serlvet;

import br.com.nonnapizza.dao.EnderecoDao;
import br.com.nonnapizza.model.Endereco;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/find-all-address")
public class ListEnderecosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Endereco> enderecos = new EnderecoDao().listarEnderecos();

        req.setAttribute("enderecos", enderecos);

        req.getRequestDispatcher("pizzadashboard.jsp").forward(req, resp);
    }
}

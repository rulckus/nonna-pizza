package br.com.nonnapizza.serlvet;

import br.com.nonnapizza.dao.EnderecoDao;
import br.com.nonnapizza.dao.PizzaDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-endereco")
public class DeleteEnderecoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        EnderecoDao endereco = new EnderecoDao();

        endereco.deleteEnderecoById(id);

        resp.sendRedirect("/find-all-address");
    }
}

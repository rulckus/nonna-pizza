package br.com.nonnapizza.serlvet;

import br.com.nonnapizza.dao.ClienteDao;
import br.com.nonnapizza.model.Cliente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create-cliente")
public class CreateClienteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String telefone = req.getParameter("telefone");
        String senha = req.getParameter("senha");

        Cliente cliente = new Cliente();

        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setSenha(senha);

        ClienteDao clienteDao = new ClienteDao();

        clienteDao.inserirCliente(cliente);

        req.getRequestDispatcher("/login.html").forward(req, resp);
    }
}

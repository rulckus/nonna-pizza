package br.com.nonnapizza.serlvet;

import br.com.nonnapizza.dao.ClienteDao;
import br.com.nonnapizza.model.Cliente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        Cliente cliente = new Cliente(email, senha);

        ClienteDao clienteDao = new ClienteDao();

        boolean isValidUser = clienteDao.verificarCredenciais(cliente);

        if (isValidUser) {
                Cliente clienteLogado = clienteDao.logar(email, senha);

                req.getSession().setAttribute("loggeduser", clienteLogado);

                resp.sendRedirect("landing.html");
            } else{
                req.setAttribute("message", "Credenciais invalida");

                req.getRequestDispatcher("login.html").forward(req,resp);
            }


    }
}

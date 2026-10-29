package br.com.nonnapizza.serlvet;


import br.com.nonnapizza.dao.EnderecoDao;
import br.com.nonnapizza.model.Cliente;
import br.com.nonnapizza.model.Endereco;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create-endereco")
public class CreateEnderecoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        String ruaNome = request.getParameter("rua");
        String numero = request.getParameter("numero");
        String complemento = request.getParameter("complemento");
        String cep = request.getParameter("cep");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");

        Endereco endereco = new Endereco();

        endereco.setId(id);
        endereco.setRua(ruaNome);
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);
        endereco.setCep(cep);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        Cliente cliente = (Cliente) request.getSession().getAttribute("loggeduser");

        if (cliente == null) {
            response.sendRedirect("/login.html");
        }else {
            EnderecoDao enderecoDao = new EnderecoDao();

            if (id.isBlank()){
                enderecoDao.criarEndereco(endereco, cliente);
            }else {
                enderecoDao.atualizarEndereco(endereco);
            }


            response.sendRedirect("/enderecos");
        }

    }
}

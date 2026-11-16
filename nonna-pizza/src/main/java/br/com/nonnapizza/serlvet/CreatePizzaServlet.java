package br.com.nonnapizza.serlvet;


import br.com.nonnapizza.dao.PizzaDao;
import br.com.nonnapizza.model.Pizza;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.fileupload.servlet.ServletFileUpload.isMultipartContent;

@WebServlet("/create-pizza")
public class CreatePizzaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String> parameters = uploadImage(request);

//        String idPizza = request.getParameter("id");
//        String sabor = request.getParameter("sabor");
//        String tipo = request.getParameter("tipo");
//        String descricao = request.getParameter("descricao");
//        BigDecimal valor = new BigDecimal(request.getParameter("valor"));

        String idPizza = parameters.get("id");
        String sabor = parameters.get("sabor");
        String tipo = parameters.get("tipo");
        String descricao = parameters.get("descricao");
        BigDecimal valor = new BigDecimal(parameters.get("valor"));
        String image = parameters.get("image");


        Pizza pizza = new Pizza();

        boolean pizzaNova = idPizza == null || idPizza.isBlank();
        if (image == null && pizzaNova) {
            image = "default-pizza.jpg";
        }

        pizza.setId(idPizza);
        pizza.setSabor(sabor);
        pizza.setTipo(tipo);
        pizza.setValor(valor);
        pizza.setDescricao(descricao);
        pizza.setImage(image);

        PizzaDao pizzaDao = new PizzaDao();

        if (idPizza.isBlank()) {
            pizzaDao.inserirPizza(pizza);
        }
        else {
            pizzaDao.atualizarPizza(pizza);
        }

        response.sendRedirect("/find-all-pizzas");
    }

    private Map<String, String> uploadImage(HttpServletRequest request) {

        HashMap<String, String> parameters = new HashMap<>();

        if (isMultipartContent(request)){

            try {
                DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();

                List<FileItem> fileItems = new ServletFileUpload(diskFileItemFactory).parseRequest(request);

                for (FileItem item : fileItems) {
                    checkFieldType(item, parameters);
                }

            } catch (Exception e) {
                parameters.put("image", "img/default-pizza.jpg");
            }

            return parameters;

        } else {
            return parameters;
        }

    }

    private void checkFieldType(FileItem fileItem, Map requestParameters) throws Exception{

        if (fileItem.isFormField()) {
            requestParameters.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
        } else if (fileItem.getSize() > 0 && fileItem.getName() != null && !fileItem.getName().isEmpty()) {

            String fileName = processUploadFile(fileItem);

            requestParameters.put("image", fileName);

        }
    }

    private String processUploadFile(FileItem fileItem) throws Exception {
        Long currentTime = new Date().getTime();
        String fileName = currentTime.toString().concat("_").concat(fileItem.getName().replace(" ", ""));
        String filePath = this.getServletContext().getRealPath("img").concat(File.separator).concat(fileName);
        fileItem.write(new File(filePath));
        return fileName;
    }


}

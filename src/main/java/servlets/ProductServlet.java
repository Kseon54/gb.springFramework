package servlets;

import models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ProductServlet", urlPatterns = "/product")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products", getProducts());
        getServletContext().getRequestDispatcher("/product.jsp").forward(req, resp);
//        ArrayList<Product> products = getProducts();
//
//        for (Product product : products) {
//            resp.getWriter().printf("%s\t%s\t%s\n", product.getId(), product.getTitle(), product.getCost());
//        }
    }

    private ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            products.add(new Product(i, String.format("Product%d", i), Math.round(Math.random() * 1000)));
        }
        return products;
    }
}

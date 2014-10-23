/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.MedicoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kinds.Medico;
import sistema.GsonFactory;

/**
 *
 * @author reddo
 */
@WebServlet(name = "Medicos", urlPatterns = {"/Medicos"})
public class Medicos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        // Ação não definida? Bad request
        if (action == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        
        // Get?
        if (action.equals("get")) {
            getMedico(request, response);
            return;
        }
        
        
        // Nenhuma ação válida encontrada, não implementado.
        response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void getMedico(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String crm = request.getParameter("crm");
        if (crm == null) crm = "";
        String especialidade = request.getParameter("especialidade");
        if (especialidade == null) especialidade = "";
        
        ArrayList<Medico> medicos = new ArrayList<Medico> ();
        Gson gson = GsonFactory.getFactory().getGsonExposed();
        Medico medico = new Medico();
        medico.setNome("João");
        medico.setJson(gson.toJson(medico));
        medicos.add(medico);
        medico = new Medico();
        medico.setNome("Pedro");
        medico.setJson(gson.toJson(medico));
        medicos.add(medico);
        if (medicos == null) {
            // deu erro
        } else {
            try {
                // redirecionar para o JSP
                request.setAttribute("medicos", medicos);
                RequestDispatcher nextView = request.getRequestDispatcher("/Medicos.jsp");
                nextView.forward(request,response);
            } catch (ServletException ex) {
                Logger.getLogger(Medicos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Medicos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

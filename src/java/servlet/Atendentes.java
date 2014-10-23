/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import kinds.Agendamento;
import kinds.Atendente;

/**
 *
 * @author jonasrg
 */
@WebServlet(name = "Atendentes", urlPatterns = {"/Atendentes"})
public class Atendentes extends HttpServlet {

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
            getAtendente(request, response);
            return;
        }
        
        Agendamento exemplo = new Agendamento();
        exemplo.setEstado(Agendamento.ESTADO_CRITICO);
        
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

    private void getAtendente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cpf = request.getParameter("cpf");
        if (cpf == null) cpf = "";
        String nome = request.getParameter("nome");
        if (nome == null) nome = "";
        
        ArrayList<Atendente> atendentes = new ArrayList<Atendente> ();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Atendente atendente = new Atendente();
        atendente.setNome("Atendente 1");
        atendente.setJson(gson.toJson(atendente));
        atendentes.add(atendente);
        atendente = new Atendente();
        atendente.setNome("Atendente 2");
        atendente.setJson(gson.toJson(atendente));
        atendentes.add(atendente);
        if (atendentes == null) {
            // deu erro
        } else {
            try {
                // redirecionar para o JSP
                request.setAttribute("atendentes", atendente);
                RequestDispatcher nextView = request.getRequestDispatcher("/Atendentes.jsp");
                nextView.forward(request,response);
            } catch (ServletException ex) {
                Logger.getLogger(Atendentes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Atendentes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

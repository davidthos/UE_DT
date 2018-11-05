/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.ue.ui;

import be.isl.ue.dao.PersonDAO;
import be.isl.ue.entity.Person;
import be.isl.ue.viewmodel.PersonSearchVM;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author davetones
 */

// Ajax fait appel à celui-si via les Actions demandés.
// La servlet Appel les DAO et les Entity
@WebServlet(name = "PersonCtrl", urlPatterns = {"/PersonCtrl"})
public class PersonCtrl extends HttpServlet {

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
        
        PersonDAO dao;
        HttpSession session = request.getSession();
        
        //Pourquoi personDAO, le p en minuscule ?
        if (session.getAttribute("personDAO") == null) {
            dao = new PersonDAO();
            session.setAttribute("personDAO", dao);
        } else {
            dao = (PersonDAO) session.getAttribute("personDAO");
        }
        
        String Action = request.getParameter("Action");
        
        //Affichage des données de la personnes selectionnée via le clieck de l'id de la personne en question
        if (Action.equals("getOne")) {
            
                String id = request.getParameter("PersonId");
                writeResponse(response, new Gson().toJson(dao.getById(Integer.parseInt(id))));
            
                // Cette action me donne la liste de la personne que je recherche
            } else if (Action.equals("getList")) {
                
                PersonSearchVM s = new PersonSearchVM();
                
                s.setLastName(request.getParameter("lastName"));    
                s.setFirstName(request.getParameter("firstName"));
                
                String json = new Gson().toJson(dao.load(s));
                writeResponse(response, json);
                
                /*PersonSearchVM s = new PersonSearchVM();
                
                s.setLastName(request.getParameter("lastName"));    
                s.setFirstName(request.getParameter("firstName")); 
                ArrayList<Person> persons = dao.load(s);
                String json = new Gson().toJson(persons);
                writeResponse(response, json);*/
                
                //Ce procédé fonctionne tandis que celui du dessus, KO !
                /*Person s = new Person();
                s.setLastName(request.getParameter("lastName"));
                ArrayList<Person> persons = dao.load(s);
                String json = new Gson().toJson(persons);
                writeResponse(response, json);*/
                
                
            } /*else if (Action.equals("doSave")) {
                
                String id = request.getParameter("id");
                String categoryId = request.getParameter("categoryId");
                Person a = null;
                if (id != null && id.length() > 0) {
                    a = dao.getById(Integer.parseInt(id));
                } 
                if (a == null) {
                    a = new Person();
                }
                PersonDAO cDAO = new PersonDAO();
                
                a.setLastName(request.getParameter("lastName_search_JS"));
                a.setFirstName(request.getParameter("firstName_search_JS"));
   
                dao.save(a);
                writeResponse(response, new Gson().toJson(dao.getList()));
            } else if (Action.equals("doDelete")) {
                dao.delete(Integer.parseInt(request.getParameter("id")));
                writeResponse(response, new Gson().toJson(dao.getList()));
            }*/
        
        
    }

    
     public void writeResponse(HttpServletResponse response, String output) throws IOException {
        response.setContentType("text/json");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write(output);
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

}

package ma.ac.esi.referentielCompetences.controleur;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.ac.esi.referentielCompetences.model.Skill;
import ma.ac.esi.referentielCompetences.model.SkillDAO;

import java.io.IOException;
import java.util.List;


public class SkillServlete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SkillServlete() {
        super();
       
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String domain = request.getParameter("domain");
		String level = request.getParameter("level");

		Skill skill = new Skill(name, description, domain, level);
		SkillDAO skillDAO = new SkillDAO();
		boolean succes =  skillDAO.addSkill(skill); 

		if (succes) {
	        List<Skill> skills = skillDAO.getAllSkills();
	        request.setAttribute("skills", skills);
	        request.setAttribute("message", "Liste de compétences mise à jour avec succès.");
	    } else {
	        request.setAttribute("erreur", "Erreur lors de l'ajout de la compétence.");
	    }
        // Rediriger vers la page SkillCrud.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/SkillCrud.jsp");
        dispatcher.forward(request, response);
    }
	}
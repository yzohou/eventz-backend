package rest;

import DAO.UtilisateurDAO;
import model.Participant;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@SuppressWarnings("serial")
@Path("/utilisateurs")
public class UtilisateursService extends HttpServlet {

    UtilisateurDAO udao = new UtilisateurDAO();

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Participant> getAllUtilisateur() {
        List<Participant> resultList = udao.findAll();
        return resultList;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Participant getUtilisateurById(@PathParam("id") int id) {
        return udao.findById(id);
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Participant addUtilisateur(JSONObject user) {
    	Participant utilisateur = new Participant();
        try {
            utilisateur = udao.add(user.getString("nom"),user.getString("prenom"),user.getString("email"), user.getString("password") );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return utilisateur;
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Participant getLogin(JSONObject user) {
    	Participant utilisateur = new Participant();
        try {
            utilisateur = udao.getLogin(user.getString("email"), user.getString("password") );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return utilisateur;
    }

    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public Participant updateUtilisateur(JSONObject user) {
    	Participant utilisateur = new Participant();
        try {
            utilisateur = udao.update(user.getInt("id"), user.getString("nom"),user.getString("prenom"),user.getString("email") ,user.getString("password") );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return utilisateur;
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteUtilisateurById(@PathParam("id") int id) {
        udao.deleteById(id);
    }
}

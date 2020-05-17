package rest;

import DAO.ReunionDAO;
import model.Reunion;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@SuppressWarnings("serial")
@Path("/reunion")
public class ReunionService extends HttpServlet {

	ReunionDAO reuDao = new ReunionDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reunion> getAllReunions() {
        return reuDao.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Reunion getSondageById(@PathParam("id") int id) {
        return reuDao.findById(id);
    }

    @GET
    @Path("/mes-reunions/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reunion> getMesSondage(@PathParam("id") int id) {
        return reuDao.findByMesReunions(id);
    }

    @POST
    @Path("/add-reunion")
    @Produces(MediaType.APPLICATION_JSON)
    public Reunion addreunion(JSONObject reunionJson) {
        System.out.println(reunionJson);
        Reunion reunion = new Reunion();
        try {
			reunion = reuDao.addReunion(
					reunion,
					reunionJson.getString("intitule"),
			        reunionJson.getString("resume"),
			        reunionJson.getInt("idCreateur"), 
			        reunionJson.getJSONArray("datesChoisies"),
			        reunionJson.getJSONArray("participants") 
			        );
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
        
        return reunion;
    }

}


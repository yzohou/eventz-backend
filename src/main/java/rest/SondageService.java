package rest;

import DAO.SondageDAO;
import model.Reunion;
import model.Sondage;
import model.Sondage_Participant;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.List;

@SuppressWarnings("serial")
@Path("/sondage")
public class SondageService extends HttpServlet {

    SondageDAO sdao = new SondageDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sondage> getAllSondage() {
        List<Sondage> resultList = sdao.findAll();
        return resultList;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Reunion getSondageById(@PathParam("id") int id) {
        return sdao.findById(id);
    }

    @GET
    @Path("/mes-sondages/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reunion> getMesSondages(@PathParam("id") int id) {
        return sdao.findByMesSondages(id);
    }
    
    @POST
    @Path("/selected-date")
    @Produces(MediaType.APPLICATION_JSON)
    public Sondage_Participant updateSelectedDate(JSONObject sondageParticipantJson) {
    	Sondage_Participant sondage =  new Sondage_Participant();
    	try {
    		System.out.println( sondageParticipantJson.getInt("idParticipant") );
    		System.out.println( sondageParticipantJson.getInt("idReunion") );
    		System.out.println( sondageParticipantJson.getInt("idSelectedDate") );
    		
    		sondage = sdao.updateDateChoisie(sondageParticipantJson.getInt("idParticipant"),
					sondageParticipantJson.getInt("idSelectedDate"),
					sondageParticipantJson.getInt("idReunion"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	return sondage;
    }

}

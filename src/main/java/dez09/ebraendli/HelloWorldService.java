package dez09.ebraendli;
 
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
 
@Path("/")
public class HelloWorldService {
 	//public static UserDB udb = new UserDB();

	@GET
	@Path("hello/{param}")
	public Response getMsg(@PathParam("param") String msg) {
 
		String output = "Jersey say : " + msg;
 
		return Response.status(200).entity(output).build();
 
	}

	@GET
	@Path("register")
	public Response putRegScreen(){
		String output = "Bitte noch html erzeugen!";

		return Response.status(200).entity(output).build();
	}

	@POST
	@Path("register/{email}/{user}/{pwd}")
	public Response getReg(@PathParam("email") String email, @PathParam("user") String usr, @PathParam("pwd") String pwd){
		String out= usr + "\n"+ pwd;
		return Response.status(200).entity(UserDB.putUser(email, usr, pwd)).build();
	}
	@GET
	@Path("login")
	public Response putLoginScreen(){
		String output = "Bitte noch html erzeugen!";

		return Response.status(200).entity(output).build();
	}

	@POST
	@Path("login/{user}/{pwd}")
	public Response getLogin(@PathParam("user") String usr, @PathParam("pwd") String pwd){
		String out= usr + "\n"+ pwd;

		return Response.status(200).entity(UserDB.getUser(usr,pwd)).build();
	}

}
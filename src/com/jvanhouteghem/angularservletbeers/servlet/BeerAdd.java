package com.jvanhouteghem.angularservletbeers.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.Buffer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jvanhouteghem.angularservletbeers.Beer;

import sun.java2d.pipe.BufferedBufImgOps;

/**
 * Servlet implementation class BeerAdd
 */
@WebServlet("/BeerAdd")
public class BeerAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BeerAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("BeerAdd");
	       if(null==request.getParameter("id")|| ""==request.getParameter("id")){
	           response.setStatus(418);
	           response.getWriter().append(" Missing id parameter");
	           return;
	       }
	       
	       String id = request.getParameter("id");
	       //Beer beer = BeerMongoDAO.getBeerMongoDAOInstance().getBeer(id);
	       
	       //ObjectMapper mapper = new ObjectMapper();
	       //String dataBeer = mapper.writeValueAsString(beer);
	       //response.getWriter().println(dataBeer);
	       response.getWriter().println(id);
	       
	       // 1 - Faire que le submit appelle BeerAdd 
	       // 2 - Faire que BeerAdd recupère les param
	       // 3 - BeerAdd envoit une requête à la db
	       
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// reader
		
		System.out.println("dopostBeerAdd");
		
		// Lecture du contenu de request, retourne un String Json
		BufferedReader beerReader = request.getReader();
		StringBuilder beerBuffer = new StringBuilder();
		String line;
		while ((line = beerReader.readLine()) != null){
			beerBuffer.append(line);
		}
		String beerJSON = beerBuffer.toString();
		System.out.println("I got a beer! - " + beerJSON);

		ObjectMapper mapper = new ObjectMapper();
		
		Beer beer = mapper.readValue(beerJSON, Beer.class);
		
		BeerMongoDAO.getBeerMongoDAOInstance().insertBeer(beer);
		
		
		//doGet(request, response);
	}

}

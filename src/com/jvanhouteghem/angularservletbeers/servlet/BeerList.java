package com.jvanhouteghem.angularservletbeers.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jvanhouteghem.angularservletbeers.Beer;

/**
 * Servlet implementation class BeerList
 */
@WebServlet("/BeerList")
public class BeerList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BeerList() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("BeerList");
		
		List<Beer> beers = Beer.getBeers();
		ObjectMapper mapper = new ObjectMapper();
		String jsonList = mapper.writeValueAsString(beers);
		
		response.getWriter().append(jsonList);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

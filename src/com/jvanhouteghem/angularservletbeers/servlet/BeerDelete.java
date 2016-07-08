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
@WebServlet("/BeerDelete")
public class BeerDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BeerDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BeerDeleteGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// reader
		System.out.println("BeerDeletePost");
		
		BufferedReader beerReader = request.getReader();
		String inputName = beerReader.readLine();
		System.out.println(inputName);
		
		String beerJSON = inputName;
		System.out.println("I got a beer! - " + beerJSON);
		
		ObjectMapper mapper = new ObjectMapper();
		
		Beer beer = mapper.readValue(beerJSON, Beer.class);
		BeerMongoDAO.getBeerMongoDAOInstance().deleteBeer(beer);
	}

}

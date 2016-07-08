package com.jvanhouteghem.angularservletbeers.servlet;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.jvanhouteghem.angularservletbeers.Beer;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class BeerMongoDAO {

    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    private static BeerMongoDAO beerMongoDAO;
     
    private BeerMongoDAO(){
    	this.client = new MongoClient("localhost");
    	this.database = this.client.getDatabase("beers");
    	this.collection = this.database.getCollection("beer");
    }
    
    public static BeerMongoDAO getBeerMongoDAOInstance(){
		if (null == beerMongoDAO){
			beerMongoDAO = new BeerMongoDAO();
		}
    	return beerMongoDAO;
    }
    
    public Beer getBeer(String id){
    	//Document doc = collection.find(Filters.eq("id", id)).first();
    	Beer beer = new Beer();
		/*beer.setId(doc.getString("id"));
		beer.setImg(doc.getString("img"));
		beer.setAlcohol(doc.getDouble("alcohol"));
		beer.setName(doc.getString("name"));
		beer.setDescription(doc.getString("description"));
		beer.setAvailability(doc.getString("availability"));
		beer.setBewery(doc.getString("bewery"));
		beer.setLabel(doc.getString("label"));
		beer.setServing(doc.getString("serving"));
		beer.setStyle(doc.getString("style"));*/
    	beer = getBeerFromDocument(this.collection.find(Filters.eq("id", id)).first());
    	return beer;
    }
    
    public List<Beer> getBeerList(){
    	
    	List<Beer> beers = new ArrayList<>();
    	// Recherche dans la bdd 
    	MongoCursor<Document> cursor = this.collection.find().iterator(); // Document correspond à une bière
    	
    	while (cursor.hasNext()){
    		Document doc = cursor.next();
    		/*Beer beer = new Beer();
    		beer.setId(doc.getString("id"));
    		beer.setImg(doc.getString("img"));
    		//beer.setAlcohol(doc.getDouble("alcohol"));
    		beer.setName(doc.getString("name"));
    		beer.setDescription(doc.getString("description"));*
    		
    		Object alcohol = doc.get("alcohol");
    		if (alcohol instanceof Double){
    			beer.setAlcohol((Double)alcohol);
    		} else {
    			beer.setAlcohol((int) alcohol);
    		}*/
    		Beer beer = getBeerFromDocument(doc);
    		
    		beers.add(beer);
    	}
    	
		return beers;
    	
    }
    
    public Document generateBeerDocument(Beer beer){
    	Document doc = new Document("name", beer.getName())
    		.append("id", beer.getId())
    		.append("alcohol", beer.getAlcohol())
    		.append("description", beer.getDescription())
    		.append("img", beer.getImg())
    		.append("availability", beer.getAvailability())
    		.append("bewery", beer.getBewery())
    		.append("label", beer.getLabel())
    		.append("serving", beer.getServing())
    		.append("style", beer.getStyle());
    	return doc;
    }
    
    public Beer getBeerFromDocument(Document doc){
    	Beer beer = new Beer();
		beer.setId(doc.getString("id"));
		beer.setImg(doc.getString("img"));
		//beer.setAlcohol(doc.getDouble("alcohol"));
		beer.setName(doc.getString("name"));
		beer.setDescription(doc.getString("description"));
		
		Object alcohol = doc.get("alcohol");
		if (alcohol instanceof Double){
			beer.setAlcohol((Double)alcohol);
		} else {
			beer.setAlcohol((int) alcohol);
		}
    	return beer;
    	
    }
    
    public void insertBeer(Beer beer){
    	Document doc = generateBeerDocument(beer);
    	collection.insertOne(doc);   
    }
    
    public void deleteBeer(Beer beer){
    	System.out.println("dao deleteBeer " + beer + " - name : " + beer.getName());
    	Document doc = collection.find(Filters.eq("name", beer.getName())).first();
    	//Document doc = generateBeerDocument(beer);
    	collection.deleteOne(doc);   
    }
    
    
    

    
}

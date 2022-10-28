package com.esprit.examen.services;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.StockRepository;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProduitServiceImpltTest {
	@Autowired
	IProduitService produitservice;
	@Test
	public void  testretrieveAllProduits() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date Date1 = dateFormat.parse("25/09/2000");
		Date Date2 = dateFormat.parse("26/10/1919");
		List<Produit> produits = (List<Produit>) produitservice.retrieveAllProduits();
		int expected = produits.size();
		Produit c = new Produit("produitI", "produitII", (float) 2.5, Date1,Date2);
		Produit produit = produitservice.addProduit(c);
		assertEquals(expected + 1, produitservice.retrieveAllProduits().size());
		produitservice.deleteProduit(produit.getIdProduit());
 	}
	@Test
	public void testAddProduit() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date Date1 = dateFormat.parse("25/09/2000");
		Date Date2 = dateFormat.parse("26/10/1919");
		Produit c = new Produit("produitI", "produitII", (float) 2.5, Date1,Date2);
		Produit savedProduit= produitservice.addProduit(c);
		assertNotNull(savedProduit.getDateCreation());
		assertNotNull(savedProduit.getDateDerniereModification());
		assertNotNull(savedProduit.getPrix());
		assertNotNull(savedProduit.getCodeProduit());
		assertNotNull(savedProduit.getLibelleProduit());
		produitservice.deleteProduit(savedProduit.getIdProduit());	
	}
	@Test
	public void testDeleteProduit() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date Date1 = dateFormat.parse("25/09/2000");
		Date Date2 = dateFormat.parse("26/10/1919");
		Produit c = new Produit("produitI", "produitII", (float) 2.5, Date1,Date2);
		Produit savedproduit= produitservice.addProduit(c);
		produitservice.deleteProduit(savedproduit.getIdProduit());
		assertNull(produitservice.retrieveProduit(savedproduit.getIdProduit()));
	}
	@Test
	public void testretrieveProduit() throws ParseException
	{

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date Date1 = dateFormat.parse("25/09/2000");
			Date Date2 = dateFormat.parse("26/10/1919");
			Long id =(long)0;
			Produit c = new Produit("produitI", "produitII", (float) 2.5, Date1,Date2);
			produitservice.addProduit(c);
			assertNull(produitservice.retrieveProduit(id));				
	}	
	@Test
	public void testupdateProduit( )  throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date Date1 = dateFormat.parse("25/09/2000");
		Date Date2 = dateFormat.parse("26/10/1919");
		Produit c = new Produit("produitI", "produitII", (float) 2.5, Date1,Date2);	
		c.setLibelleProduit("produit3");
		assertThat(c.getLibelleProduit()).isEqualTo("produit3");
			}
	@Test
	@Transactional

	public void testassignProduitToStock()  throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date Date1 = dateFormat.parse("25/09/2000");
		Date Date2 = dateFormat.parse("26/10/1919");
		Produit c = new Produit("produitI", "produitII", (float) 2.5, Date1,Date2);	
		Stock s = new Stock("stock test",10,100);	
		c.setStock(s);
		Produit produit = produitservice.addProduit(c);
 		assertThat(produit.getStock().getIdStock()).isEqualTo(s.getIdStock());

	}
}
package source;

import java.util.ArrayList;

import generated.*;

public class Main 
{

	public static void main(String[] args) 
	{
		ParseAndGenerate.doIt("resources/schema.xsd");
		
		/*ParcAutomobile parc = new ParcAutomobile();
		parc.setPersonne(new ArrayList<Personne>());
		parc.setVehicule(new ArrayList<Vehicule>());
		Personne p1 = new Personne();
		p1.setNom("Mbaye"); p1.setPrenom("Papi"); p1.setCin("1234");
		p1.setTelephones(new ArrayList<Telephones>());
		Telephones t = new Telephones();
		t.setTelephone("779391561"); p1.getTelephones().add(t);
		Telephones t1 = new Telephones();
		t1.setTelephone("761377239"); p1.getTelephones().add(t1);
		Vehicule gamos = new Vehicule();
		gamos.setImmatriculation("8294"); gamos.setMarque("Mercedes");
		gamos.setModele("AMG 63"); gamos.setProprietaire("1234");
		parc.getPersonne().add(p1); parc.getVehicule().add(gamos);
		
		try 
		{
			parc.save();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}*/
		
		/*Livre livre = new Livre();
		livre.setTitre("livre1");
		livre.setAuteur("auteur1");
		livre.setEditeur("editeur1");
		Bibliotheque biblio = new Bibliotheque();
		biblio.setLivre(new ArrayList<Livre>());
		biblio.getLivre().add(livre);
		Livre l = new Livre();
		l.setTitre("livre2");
		l.setAuteur("auteur2");
		l.setEditeur("editeur2");
		biblio.getLivre().add(l);
		Livre livr = new Livre();
		livr.setTitre("livre3");
		livr.setAuteur("auteur3");
		livr.setEditeur("editeur3");
		biblio.getLivre().add(livr);
		try 
		{
			biblio.save();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}*/
	}

}

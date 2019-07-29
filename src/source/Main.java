package source;

public class Main 
{

	public static void main(String[] args) 
	{
		ParseAndGenerate.doIt("resources/schema.xsd");
		

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

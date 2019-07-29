# ProjetEval  

Ce projet consistait à concevoir un programme `Java` prenant en entrée un `schéma` et d'en généré les classes correspondantes.  
Pour ce, faire nous avons opté comme démarche le `parsing` du schéma avant de passer à l'extraction des informations dont on a besoin et générer ainsi les classes.  

## Architecture du projet  

Le projet est découpé trois (3) couches ou packages que sont :  

- generated  
Contient les classes que va généré le programme après exécution  

- generator  
Contient le code métier de la génération de classe  

- source  
Contient notre `parser` ainsi que notre classe de test.  

## Fonctionnement  

Dans votre programme principale ou votre `main`, appelez la méthode `doIt()` de la classe `ParseAndGenerate` en lui donnant en paramètre le chemin vers le fichier correspondant à votre schéma.

`ParseAndGenerate.doIt('chemin/vers/votre/schema.xsd')`  

Puis, si la génération se passe bien, vous pourrez alors utiliser les classes ainsi générées.
A noté que la classe correspondant à l'élément `racine` ou élément `root` possède une méthode `save()` permettant de générer une `instance XML` à partir du code.  

### Exemple  

Dans notre méthode `main` de la classe principale on a ceci :  

`ParseAndGenerate.doIt("resources/schema.xsd");` 

Parsing et génération des classes effectuée, on peut utiliser ces dernières (ne pas oublié de les importées depuis le package `generated`) comme ceci :  

		Livre livre = new Livre();
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
			biblio.save(); //On génére ici l'instance XML
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

Résultat de l'exemple (document XML généré) :  

	<?xml version="1.0" encoding="UTF-8" standalone="no"?>
	<bibliotheque>
	    <livre>
	        <titre>livre1</titre>
	        <auteur>auteur1</auteur>
	        <editeur>editeur1</editeur>
	    </livre>
	    <livre>
	        <titre>livre2</titre>
	        <auteur>auteur2</auteur>
	        <editeur>editeur2</editeur>
	    </livre>
	    <livre>
	        <titre>livre3</titre>
	        <auteur>auteur3</auteur>
	        <editeur>editeur3</editeur>
	    </livre>
	</bibliotheque>

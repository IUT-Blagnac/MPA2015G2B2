import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Sorény
 *
 */
public class MakeOPTIweb {
	
	private static final String SEPARATOR = ";";
	private static ArrayList<String[]> sujets;
	private static ArrayList<String[]> etudiants;
	private static ArrayList<String[]> intervs;
	private static ArrayList<String[]> projets;
	
	/**
	 * Lit et convertit un fichier CSV en ArrayList<String[]>
	 * 
	 * @param csvPath
	 * @return dataTab
	 * @throws Exception 
	 */
	private static ArrayList<String[]> CSV_Read(String csvPath) throws Exception {
		ArrayList<String[]> dataTab = new ArrayList<String[]>();
		BufferedReader csvFile = new BufferedReader(new FileReader(csvPath));
		String line;
		while ((line = csvFile.readLine()) != null) {
			if(line.substring(0, 0) != "#")
				dataTab.add(new String(line.getBytes(), Charset.forName("UTF-8")).split(SEPARATOR));
		}
		csvFile.close();
		return dataTab;
	}
	
	private static String htmlSujets(ArrayList<String[]> sujets){
		String s ="";
		
		for(int i=1; i<sujets.size(); i++){
			s+="<li data-find=\"[" + sujets.get(i)[1] + "]\"><a href=\"#projets\">[" + sujets.get(i)[1]
					+ "]<br/><div style =\"white-space:normal;\"><span><b>" + sujets.get(i)[2]
					+ "</b></span><span class=\"ui-li-count\">" + getIdGroupeSuj(sujets.get(i)[0]) +"</span></div></a></li>";
		}
		
		return s;
	}
	
	private static String getIdGroupeSuj(String idSujet){
		String result = "";
		for(int i=0; i<projets.size(); i++)
			if(projets.get(i)[2].equals(idSujet))
				if(result.isEmpty())
					result += projets.get(i)[1];
				else
					result += " " + projets.get(i)[1];
		
		return result;
	}
	
	private static String htmlProjets(ArrayList<String[]> projs){
		String s ="";
		
		for(int i=1; i<projs.size(); i++){
			String[] suj = getSujet(projs.get(i)[2]);
			s += "<li><p><b>[" + suj[1] + "]</b> "+ suj[2] +"</p><p><b>Client :</b> "+ getInterv(projs.get(i)[3]) +"</p><p><b>Superviseur :</b> "+ getInterv(projs.get(i)[4]);
			if(projs.get(i).length==6)
				s += "<p><b>Support Technique :</b> "+ getInterv(projs.get(i)[5]) +"</p>";
			
			s += "</p><p><b>Groupe "+ projs.get(i)[1] +" :</b> "+ getEtuDuG(projs.get(i)[1]) +"</p></li>";
		}
		return s;
	}
	
	private static String getEtuDuG(String id){
		String s = "";
		for(int i=0; i<etudiants.size(); i++)
			if(etudiants.get(i)[0].equals(id))
				s += etudiants.get(i)[3] + " " + etudiants.get(i)[2] + " - "; 
		return s;
	}
	
	private static String[] getSujet(String id){
		
		for(int i=0; i<sujets.size(); i++){
			if(sujets.get(i)[0].equals(id))
				return sujets.get(i);
		}
		
		return null;
	}
	
	private static String getInterv(String id){
		
		for(int i=0; i<intervs.size(); i++){
			if(intervs.get(i)[0].equals(id))
				return intervs.get(i)[2] + " " + intervs.get(i)[1];
		}
		
		return "aucun";
	}
	
	private static String htmlIntervs(ArrayList<String[]> ints){
		String s ="";
		
		for(int i=1; i<ints.size(); i++){
			String p = "\"#projets\"";
			s+="<li data-find=\""+ ints.get(i)[2] + " " + ints.get(i)[1] +"\"><a href="+p+">"+ ints.get(i)[2]+ " " + ints.get(i)[1] +"<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\">" + clientDeNB(ints.get(i)[0]) + "</span><span class=\"ui-li-count\" title=\"Superviseur\">" + supervisDeNB(ints.get(i)[0]) + "</span></a></li>";
		}
		
		return s;
	}
	
	private static String clientDeNB(String idInt){
		int nb = 0;
		
		for(int i=1; i<projets.size(); i++)
			if(projets.get(i)[3].equals(idInt))
				nb++;
		
		return ""+nb;
	}
	
	private static String supervisDeNB(String idInt){
		int nb = 0;
		
		for(int i=1; i<projets.size(); i++)
			if(projets.get(i)[4].equals(idInt))
				nb++;
		
		return ""+nb;
	}
	
	private static String htmlEtudiants(ArrayList<String[]> etus){
		String s ="";
		
		ArrayList<String[]> etusNon = new ArrayList<>(MakeOPTIweb.etudiants);
		ArrayList<String[]> etudiants = new ArrayList<>();
		
		for(int j=1; j<MakeOPTIweb.etudiants.size(); j++){
			String[] etu = null;
			if(!etusNon.isEmpty())
				etu = etusNon.get(0);
			for(int i=0; i<etusNon.size(); i++){
				if(etusNon.get(i)[2].compareTo(etu[2])<0)
					etu = etusNon.get(i);
			}
			etusNon.remove(etu);
			etudiants.add(etu);
		}
		
		for(int i=0; i<etudiants.size(); i++){
			s+="<li data-find=\"" + etudiants.get(i)[3] + " " + etudiants.get(i)[2] +"\"><a href=\"#projets\">" + etudiants.get(i)[2] + " " + etudiants.get(i)[3] +"<span class=\"ui-li-count\" title=\"Groupe\">Groupe "+ etudiants.get(i)[0] +"</span></a></li>";
		}
		
		return s;
	}
	
	private static void make(String sujetsCsv, String etudiantsCsv, String intervsCsv, String projetsCsv) throws Exception{
		sujets = CSV_Read(sujetsCsv);
		etudiants = CSV_Read(etudiantsCsv);
		intervs = CSV_Read(intervsCsv);
		projets = CSV_Read(projetsCsv);
		
		Writer html = null;
		try {
			html = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("OPTIweb.html"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		html.write(
				"<!DOCTYPE html>\n"
				+ "<html>\n"
				+ "<head>\n"
				+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
				+ "<meta name=\"generator\" content=\"OPTIweb VOPTIweb\" />\n"
				+ "<title>0.1 - V0.1</title>\n"
				+ "<link rel=\"stylesheet\" href=\"http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css\" />\n"
				+ "<link rel=\"stylesheet\" href=\"http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.css\" />\n"
				+ "<script src=\"http://code.jquery.com/jquery-1.9.1.min.js\"></script>\n"
				+ "<script src=\"http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.js\"></script>\n"
				+ "<style type='text/css'>\n"
				+ "@media all and (orientation:portrait) { .landscape {display: none;} }\n"
				+ "@media all and (orientation:landscape) { .landscape {display: inline;} }\n"
				+ "</style>\n"
				+ "</head><body>\n"
				+ "\n"
				+ "<!-- DEBUT page accueil -->\n"
				+ "<div data-role=\"page\" id=\"accueil\" data-title=\"OPTIweb - V0.1\">\n"
				+ "<div data-role=\"header\" data-add-back-btn=\"true\">\n"
				+ "<h1>P<span class=\"landscape\">rojets </span>tut<span class=\"landscape\">orés</span> 2014-2015<br/>Département INFO<span class=\"landscape\">RMATIQUE</span><br/>IUT de Blagnac</h1>\n"
				+ "<a href=\"#credits\" data-theme=\"b\" class=\"ui-btn-right\">Crédits</a>\n"
				+ "</div>\n"
				+ "<div data-role=\"content\">\n"
				+ "<ul data-role=\"listview\" data-inset=\"true\" id=\"listeSources\">\n"
				+ "  <li><a href=\"#projets\"><i class=\"fa fa-tasks\"></i> Projets</a></li>\n"
				+ "  <li><a href=\"#sujets\"><i class=\"fa fa-copy\"></i> Sujets</a></li>\n"
				+ "  <li><a href=\"#etudiants\"><i class=\"fa fa-group\"></i> Etudiants</a></li>\n"
				+ "  <li><a href=\"#intervenants\"><i class=\"fa fa-group\"></i> Intervenants</a></li>\n"
				+ "\n"
				+ "</ul>\n"
				+ "</div>\n"
				+ "<div data-role=\"footer\">\n"
				+ " <h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa- fa-2x\"></i></h4>\n"
				+ "</div>\n"
				+ "</div>\n"
				+ "<!-- FIN page accueil -->\n"
				+ "\n"
				+ "<!-- DEBUT page credits -->\n"
				+ "<div data-role=\"page\" id=\"credits\" data-title=\"OPTIweb - V0.1 - Crédits\">\n"
				+ "<div data-role=\"header\" data-add-back-btn=\"true\">\n"
				+ "<h1>Crédits</h1>\n"
				+ "</div>\n"
				+ "<div data-role=\"content\">\n"
				+ "    <p>Cette application a été réalisée dans le cadre du module M3301/MPA du DUT Informatique à l'IUT de Blagnac.</p>\n"
				+ "<ul data-role=\"listview\" data-inset=\"true\" id=\"contacts\" data-theme=\"a\" data-divider-theme=\"b\">\n"
				+ "    <li data-role=\"list-divider\">Product Owner</li>\n"
				+ "    <li>André PÉNINOU</li>\n"
				+ "    <li>Université Toulouse 2 - IUT de Blagnac\n"
				+ "    <br/>Département INFORMATIQUE</li>\n"
				+ "</ul>\n"
				+ "<ul data-role=\"listview\" data-inset=\"true\" id=\"listecredits\" data-theme=\"a\" data-divider-theme=\"b\">\n"
				+ "    <li data-role=\"list-divider\">Membres de l'équipe enseignante</li>\n"
				+ "<li>Jean-Michel BRUEL</li><li>Jean-Michel INGLEBERT</li><li>André PÉNINOU</li><li>Olivier ROQUES</li>\n"
				+ "</ul>\n"
				+ "<ul data-role=\"listview\" data-inset=\"true\" id=\"listepowered\" data-theme=\"a\" data-divider-theme=\"b\">\n"
				+ "    <li data-role=\"list-divider\">Propulsé par</li>\n"
				+ "    <li><a href=\"http://jquerymobile.com/\" target=\"autrePage\">http://jquerymobile.com/</a></li>\n"
				+ "    <li><a href=\"http://fortawesome.github.io/Font-Awesome/\" target=\"autrePage\">http://fortawesome.github.io/Font-Awesome/</a></li>\n"
				+ "</ul>\n"
				+ "</div>\n"
				+ "<div data-role=\"footer\">\n"
				+ " <h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa- fa-2x\"></i></h4>\n"
				+ "</div>\n"
				+ "</div>\n"
				+ "<!-- FIN page credits -->\n"
				+ "\n"
				+ "<!-- DEBUT page projets -->\n"
				+ "<div data-role=\"page\" id=\"projets\" data-title=\"OPTIweb - V0.1\">\n"
				+ "<div data-role=\"header\" data-add-back-btn=\"true\">\n"
				+ "<h1>Projets 2014-2015</h1>\n"
				+ "\n"
				+ "</div>\n"
				+ "<div data-role=\"content\">\n"
				+ "  \n"
				+ "  <form class=\"ui-filterable\"><input id=\"autocomplete-input-projet\" name=\"projet\" data-type=\"search\" placeholder=\"Vous cherchez ?...\"></form><ol id=\"listeprojets\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-projet\">"
				+ htmlProjets(projets) + "</ol>\n"
				+ "</div>\n"
				+ "<div data-role=\"footer\">\n"
				+ " <h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-tasks fa-2x\"></i></h4>\n"
				+ "</div>\n"
				+ "</div>\n"
				+ "<!-- FIN page projets -->\n"
				+ "\n"
				+ "<!-- DEBUT page sujets -->\n"
				+ "<div data-role=\"page\" id=\"sujets\" data-title=\"OPTIweb - V0.1\">\n"
				+ "<div data-role=\"header\" data-add-back-btn=\"true\">\n"
				+ "<h1>Sujets 2014-2015</h1>\n"
				+ "\n"
				+ "</div>\n"
				+ "<div data-role=\"content\">\n"
				+ "  \n"
				+ "  <form class=\"ui-filterable\"><input id=\"autocomplete-input-sujet\" name=\"sujet\" data-type=\"search\" placeholder=\"Vous cherchez ?\"></form><ol id=\"listesujets\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-sujet\" data-divider-theme=\"b\" data-count-theme=\"a\"><li data-role=\"list-divider\">Sujet<span class=\"ui-li-count\" title=\"Groupe\" style=\"right: 40px !important;\">Groupe</span></li>"
				+ htmlSujets(sujets) + "</ol>\n"
				+ "</div>\n"
				+ "<div data-role=\"footer\">\n"
				+ " <h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-copy fa-2x\"></i></h4>\n"
				+ "</div>\n"
				+ "</div>\n"
				+ "<!-- FIN page sujets -->\n"
				+ "\n"
				+ "<!-- DEBUT page etudiants -->\n"
				+ "<div data-role=\"page\" id=\"etudiants\" data-title=\"OPTIweb - V0.1\">\n"
				+ "<div data-role=\"header\" data-add-back-btn=\"true\">\n"
				+ "<h1>Etudiants 2014-2015</h1>\n"
				+ "\n"
				+ "</div>\n"
				+ "<div data-role=\"content\">\n"
				+ "  \n"
				+ "  <form class=\"ui-filterable\"><input id=\"autocomplete-input-etudiant\" name=\"etudiant\" data-type=\"search\" placeholder=\"Etudiant ou Groupe X\"></form><ol id=\"listeetudiants\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-etudiant\" data-divider-theme=\"b\"><li data-role=\"list-divider\">Etudiant<span class=\"ui-li-count\" title=\"Groupe\" style=\"right: 40px !important;\">Groupe</span></li>"
				+ htmlEtudiants(etudiants) + "</ol>\n"
				+ "</div>\n"
				+ "<div data-role=\"footer\">\n"
				+ " <h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-group fa-2x\"></i></h4>\n"
				+ "</div>\n"
				+ "</div>\n"
				+ "<!-- FIN page etudiants -->\n"
				+ "\n"
				+ "<!-- DEBUT page intervenants -->\n"
				+ "<div data-role=\"page\" id=\"intervenants\" data-title=\"OPTIweb - V0.1\">\n"
				+ "<div data-role=\"header\" data-add-back-btn=\"true\">\n"
				+ "<h1>Intervenants 2014-2015</h1>\n"
				+ "\n"
				+ "</div>\n"
				+ "<div data-role=\"content\">\n"
				+ "  \n"
				+ "  <form class=\"ui-filterable\"><input id=\"autocomplete-input-intervenant\" name=\"intervenant\" data-type=\"search\" placeholder=\"Intervenant\"></form><ul id=\"listeintervenants\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-intervenant\" data-divider-theme=\"b\"><li data-role=\"list-divider\">Intervenant<span class=\"ui-li-count\" style=\"right: 110px !important;\" title=\"Client\">Client</span><span class=\"ui-li-count\" title=\"Superviseur\">Superviseur</span></li>"
				+ htmlIntervs(intervs) + "</ul>\n"
				+ "</div>\n"
				+ "<div data-role=\"footer\">\n"
				+ " <h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-group fa-2x\"></i></h4>\n"
				+ "</div>\n"
				+ "</div>\n"
				+ "<!-- FIN page intervenants -->\n"
				+ "\n"
				+ "<script>\n"
				+ " // li click handler which fills the projects search bar\n"
				+ " // with the value of the current data-find attribute\n"
				+ " $( 'li[data-find]' ).on( 'click',function(event){\n"
				+ "  $(\"#autocomplete-input-projet\").val($(this).attr('data-find')).trigger('change');\n"
				+ " });\n"
				+ "</script>\n"
				+ "</body>\n"
				+ "</html>"
					);
		
		html.close();
		
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		if(args.length == 0)
			make("sujets2014_2015.csv", "etudiants2014_2015.csv", "intervenants2014_2015.csv", "projets2014_2015.csv");
		else if(args.length == 4)
			make(args[0], args[1], args[2], args[3]);
	}

}

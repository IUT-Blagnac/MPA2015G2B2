import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.Buffer;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class CsvToJson {
	public static final String separator = ";";
	
	/**
	 * Lit et convertit un fichier CSV en ArrayList<String[]>
	 * 
	 * @param csvPath
	 * @return dataTab
	 * @throws Exception 
	 */
	public static ArrayList<String[]> CSV_Read(String csvPath) throws Exception {
		ArrayList<String[]> dataTab = new ArrayList<String[]>();
		BufferedReader csvFile = new BufferedReader(new FileReader(csvPath));
		String line;
		while ((line = csvFile.readLine()) != null) {
			if(line.substring(0, 0) != "#")
				dataTab.add(new String(line.getBytes(), Charset.forName("UTF-8")).split(separator));
		}
		csvFile.close();
		return dataTab;
	}
	
public static void main(String[] args) throws Exception {
	
		if(args.length == 1) {
		
			ArrayList<String[]> tab = CSV_Read(args[0]);
			File f = new File (args[0].split(".c")[0]+".json");
			FileWriter fw = new FileWriter (f);
			String[] titre = new String[4];
			for(int i=0; i<tab.get(0).length;i++) {
				titre[i]= tab.get(0)[i];
			}
	
			try{
				fw.write('[');
				for(int i=1; i<tab.size();i++) {
				fw.write("{");
				int j = 0;
				while (j!= tab.get(i).length) {
					if(j == tab.get(i).length-1){
						if(i == tab.size()-1){
							fw.write("\"" + titre[j] + "\": \""+ tab.get(i)[j] + "\"}");
						}else {
							fw.write("\"" + titre[j] + "\": \""+ tab.get(i)[j] + "\"},");
						}
					}else {
						fw.write("\"" + titre[j] + "\": \""+ tab.get(i)[j] + "\",");
					}
				j++;
				}
				//System.out.println("}");
				}
	
				
				fw.write(']');
				fw.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
	
			
	}else {
		System.out.println("Erreur : veuillez entrer un seul argument (fichier .csv)");
	}
		
}
	
	
}
	

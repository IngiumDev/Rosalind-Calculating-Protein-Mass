import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class CalculatingProteinMass {
    public static void main(String[] args) {
        String mapFile = "src/Monoisotopic-Mass-Table.txt";
        String proteinSequenceFilename = "src/rosalind_prtm.txt";
        System.out.println(calculateProteinMass(proteinSequenceFilename, createMassTable(mapFile)));
    }

    /**
     * Calculates the mass of a protein string with input of the protein string filename and a map of amino acid masses
     *
     * @param proteinStringFilename the name of the file containing the protein string
     * @param aminoAcidMasses       a map of amino acid masses
     * @return
     */
    public static double calculateProteinMass(String proteinStringFilename, HashMap<String, Double> aminoAcidMasses) {
        double proteinMass = 0;
        try (BufferedReader proteinStringReader = new BufferedReader(new FileReader(proteinStringFilename));) {
            String proteinString = proteinStringReader.readLine();
            proteinStringReader.close();
            // Iterate through the protein string and add the mass of each amino acid to the protein mass
            for (int i = 0; i < proteinString.length(); i++) {
                // Get the mass of the amino acid at the current index and adds it to the total protein mass
                proteinMass += aminoAcidMasses.get(proteinString.charAt(i) + "");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return proteinMass;
    }

    /**
     * Create a HashMap to store the mass of each amino acid based on the filename of the mass table
     *
     * @param filename The filename of the mass table
     * @return A HashMap of amino acid masses
     */
    public static HashMap<String, Double> createMassTable(String filename) {
        HashMap<String, Double> massTable = new HashMap<String, Double>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // process the line.
                String[] mass = line.split("   ");
                // Add the mass to the HashMap
                massTable.put(mass[0], Double.parseDouble(mass[1]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return massTable;
    }
}

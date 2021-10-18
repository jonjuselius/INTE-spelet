package Magic;

import java.io.*;
import java.util.ArrayList;

public class SpellLoader {

    private ArrayList<Spell> spells = new ArrayList<>();
    private boolean start;

    public SpellLoader() {

    }

    public ArrayList<Spell> loadSpells() {
        String fileName = "src/resources/SpellData.txt";

        try {
            FileReader rd = new FileReader(fileName);
            BufferedReader br = new BufferedReader(rd);

            String line;

            while ((line = br.readLine()) != null) {
                if (start){

                    String spellType = line.substring(0, line.indexOf(','));
                    String[] tokens = line.split(",");

                    switch (spellType) {
                        case "DamageDealingSpell" -> spells.add(new DamageDealingSpell(tokens[1], Integer.parseInt(tokens[2]), Element.valueOf(tokens[3].toUpperCase())));
                        case "HealingSpell" -> spells.add(new HealingSpell(tokens[1], Integer.parseInt(tokens[2]), Element.valueOf(tokens[3].toUpperCase())));
                        case "BuffSpell" -> spells.add(new BuffSpell(tokens[1], Integer.parseInt(tokens[2]), Element.valueOf(tokens[3].toUpperCase())));
                        case "DebuffSpell" -> spells.add(new DebuffSpell(tokens[1], Integer.parseInt(tokens[2]), Element.valueOf(tokens[3].toUpperCase())));
                    }

                }

                if (line.equalsIgnoreCase("spells:") && start == false)
                    start = true;
            }



        } catch (FileNotFoundException e){
            System.err.println("SpellLoader: Cannot find " + fileName);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        return spells;
    }



}

package Magic;

import java.io.*;
import java.util.ArrayList;

public class SpellLoader {

    private ArrayList<Spell> spells = new ArrayList<>();
    private boolean start;

    public SpellLoader() {

    }


    //loads spells from textfile in the following format:
    //<spellType>,<mana cost>,<Element>,<baseDamage/Heal>(for damage or healing spells)
    public ArrayList<Spell> loadSpells(String spellDataPathName) {
        ArrayList<Spell> spells = new ArrayList<>();
        try {
            FileReader rd = new FileReader(spellDataPathName);
            BufferedReader br = new BufferedReader(rd);

            String line;

            while ((line = br.readLine()) != null) {
                if (start){

                    String[] tokens = line.split(",");

                    switch (tokens[0]) {
                        case "DamageDealingSpell" -> {
                            if (tokens.length != 5)
                                throw new IllegalStateException("Wrong number of arguments for " + tokens[0] + "at " + line);
                            else {
                                spells.add(new DamageDealingSpell(tokens[1], Integer.parseInt(tokens[2]), Element.valueOf(tokens[3].toUpperCase()), Integer.parseInt(tokens[4])));
                            }
                        }

                        case "HealingSpell" -> {
                            if (tokens.length != 5)
                                throw new IllegalStateException("Wrong number of arguments for " + tokens[0] + "at " + line);
                            else {
                            spells.add(new HealingSpell(tokens[1], Integer.parseInt(tokens[2]), Element.valueOf(tokens[3].toUpperCase()), Integer.parseInt(tokens[4])));
                            }
                        }
                        case "BuffSpell" -> spells.add(new BuffSpell(tokens[1], Integer.parseInt(tokens[2]), Element.valueOf(tokens[3].toUpperCase())));
                        case "DebuffSpell" -> spells.add(new DebuffSpell(tokens[1], Integer.parseInt(tokens[2]), Element.valueOf(tokens[3].toUpperCase())));
                        default -> throw new IllegalStateException("Unexpected value: " + tokens[0]);
                    }

                }

                if (line.equalsIgnoreCase("spells:"))
                    start = true;
            }



        } catch (FileNotFoundException e){
            System.err.println("SpellLoader: Cannot find " + spellDataPathName);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        return spells;
    }



}

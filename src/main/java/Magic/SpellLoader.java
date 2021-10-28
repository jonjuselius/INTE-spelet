package Magic;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SpellLoader {


    public SpellLoader() {

    }

    public String[] stringToSpellArguments(String string) {
        String[] spellArguments = string.split(",");
        switch (spellArguments[0]) {
            case "DamageDealingSpell" -> {
                if (spellArguments.length != 5) {
                    throw new IllegalStateException("wrong number of arguments for DamageDealingSpell");
                }
            }
            case "HealingSpell" -> {
                if (spellArguments.length != 5) {
                    throw new IllegalStateException("wrong number of arguments for HealingSpell");
                }
            }
            case "BuffSpell" -> {
                if (spellArguments.length != 4) {
                    throw new IllegalStateException("wrong number of arguments for BuffSpell");
                }
            }
            case "DebuffSpell" -> {
                if (spellArguments.length != 3) {
                    throw new IllegalStateException("wrong number of arguments for DebuffSpell");
                }
            }
        }
        return spellArguments;
    }

    public ArrayList<String> spellFileReader(String spellDataPathName) {
        boolean start = false;
        ArrayList<String> stringLines = new ArrayList<>();
        try {
            if (Files.notExists(Paths.get(spellDataPathName))) {
                throw new FileNotFoundException("could not locate file");
            }
            FileReader rd = new FileReader(spellDataPathName);
            BufferedReader br = new BufferedReader(rd);

            String line;

            while ((line = br.readLine()) != null) {
                if (start) {
                    stringLines.add(line);
                }
                if (line.equalsIgnoreCase("spells:"))
                    start = true;
            }

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return stringLines;
    }

    public Spell constructSpell(String spellDataLine) {
        Spell spell;
        String[] arguments = stringToSpellArguments(spellDataLine);
        switch (arguments[0]) {
            case "DamageDealingSpell":
                spell = new DamageDealingSpell(arguments[1], Integer.parseInt(arguments[2]), Element.valueOf(arguments[3].toUpperCase()), Integer.parseInt(arguments[4]));
                break;
            case "HealingSpell":
                spell = new HealingSpell(arguments[1], Integer.parseInt(arguments[2]), Element.valueOf(arguments[3].toUpperCase()), Integer.parseInt(arguments[4]));
                break;
            case "BuffSpell":
                spell = new BuffSpell(arguments[1], Integer.parseInt(arguments[2]), Element.valueOf(arguments[3].toUpperCase()), Integer.parseInt(arguments[4]));
                break;
            case "DebuffSpell":
                spell = new DebuffSpell(arguments[1], Integer.parseInt(arguments[2]), Element.valueOf(arguments[3].toUpperCase()));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + arguments[0]);
        }
        return spell;
    }

    //loads spells from textfile in the following format:
    //<spellType>,<mana cost>,<Element>,<baseDamage/Heal>(for damage or healing spells)
    public ArrayList<Spell> loadSpells(String spellDataPathName) {

        ArrayList<Spell> spells = new ArrayList<>();

        ArrayList<String> spellDataLines = spellFileReader(spellDataPathName);

        for (String spellDataLine : spellDataLines) {
            String[] tokens = stringToSpellArguments(spellDataLine);

            switch (tokens[0]) {
                case "DamageDealingSpell" -> {
                    spells.add(new DamageDealingSpell(tokens[1], Integer.parseInt(tokens[2]), Element.valueOf(tokens[3].toUpperCase()), Integer.parseInt(tokens[4])));
                }
                case "HealingSpell" -> {
                    spells.add(new HealingSpell(tokens[1], Integer.parseInt(tokens[2]), Element.valueOf(tokens[3].toUpperCase()), Integer.parseInt(tokens[4])));
                }
                case "BuffSpell" -> {
                    spells.add(new BuffSpell(tokens[1], Integer.parseInt(tokens[2]), Element.valueOf(tokens[3].toUpperCase()), Integer.parseInt(tokens[4])));
                }
                case "DebuffSpell" -> {
                    spells.add(new DebuffSpell(tokens[1], Integer.parseInt(tokens[2]), Element.valueOf(tokens[3].toUpperCase())));
                }
                default -> throw new IllegalStateException("Unexpected value: " + tokens[0]);
            }
        }
        return spells;
    }

}

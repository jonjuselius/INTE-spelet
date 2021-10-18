package Magic;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        SpellLoader sl = new SpellLoader();
        ArrayList<Spell> list = sl.loadSpells();

        System.out.println(list.get(0).getName());
        System.out.println(list.get(0).getManaCost());
        System.out.println(list.get(0).getElement());


    }
}


package Races;

import GameCharacters.*;

public abstract class Race {

	private int initialHealth;
	private boolean canWalkThroughTerraign;
	private boolean canSwim;
	private boolean canFly;
	protected int maxHealth;


	public Race() {


		setIfCanFly(false);
		setIfCanSwim(false);
		setIfCanWalkThroughTerraign(false);
	}
	
    public int getHealth(){
        return initialHealth;
    }
    
	public void setInitialHealth(int health) {
		this.initialHealth = health;
	}//

	public boolean getIfCanFly() {
		return canFly;
	}

	protected void setIfCanFly(boolean canFly) {
		this.canFly = canFly;
	}

	public boolean getIfCanSwim() {
		return canSwim;
	}

	protected void setIfCanSwim(boolean canSwim) {
		this.canSwim = canSwim;
	}

	public boolean getIfCanWalkThroughTerraign() {
		return canWalkThroughTerraign;
	}

	protected void setIfCanWalkThroughTerraign(boolean canWalkThroughTerraign) {
		this.canWalkThroughTerraign = canWalkThroughTerraign;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	

    protected void setStrength(int strength) {
    }


    protected void setIntelligence(int intelligence) {
    }


}
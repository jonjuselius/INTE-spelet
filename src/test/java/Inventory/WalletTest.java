package Inventory;

import GameCharacters.Character;
import GameCharacters.Player;
import Item.*;
import Jobs.Job;
import Map.GameMap;
import Map.GameMapGenerator;
import Map.GameMapPosition;
import Races.Race;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WalletTest {
	private Wallet wallet = new Wallet();
	
	@Test
	void newWalletHasNoMoney() {
		assertThat(wallet.getAmount(), is(equalTo(0)));
	}
	
	@Test
	void adding100ToEmptyWalletIncreasesTheAmountOfMoneyInWalletBy100() {
		wallet.gain(100);
		assertThat(wallet.getAmount(), is(equalTo(100)));
	}
	
	@Test
	void removing100FromEmptyWalletDecreasesTheAmountOfMoneyInWalletBy100() {
		wallet.lose(100);
		assertThat(wallet.getAmount(), is(equalTo(-100)));
	}
	
	@Test
	void addingNegativeAmountToWalletThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			wallet.gain(-100);
		});
	}
	
	@Test
	void removingNegativeAmountToWalletThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			wallet.lose(-100);
		});
	}
}
package sig.plugin.TwosideKeeper.HelperStructures;

import org.bukkit.ChatColor;

public class Pronouns {


	public static String ChoosePronoun(int type) {
		String[] pronouns = null;
		switch (type) {
			case 0:{
				pronouns = new String[]{
						"evaporated",
						"vaporized",
						"disappeared",
						"melted",
						"vanished",
						"dematerialized",
						"faded away",
					};
			}break;
			case 1:{
				pronouns = new String[]{
						"getting poked by",
						"getting jabbed by",
						"hitting the",
						"sitting on",
						"hugging",
						"the charming good looks of",
					};
			}break;
			case 2:{
				pronouns = new String[]{
						"fell",
						"stumbled",
						"tumbled the wrong way",
						"nose dived headfirst into the ground",
						"plunged into the earth",
						"tried to fly",
						"thought they were Superman",
					};
			}break;
			case 3:{
				pronouns = new String[]{
						"scorching heat of the",
						"burn from the",
						"heat of the",
						"hot",
						"burning",
						"intense",
						"last tick of",
						ChatColor.YELLOW+"fire and",
					};
			}break;
			case 4:{
				pronouns = new String[]{
						"scorched",
						"burned",
						"incinerated",
						"destroyed",
						"owned",
						"melted",
						"roasted",
						"wrecked",
						"boiled",
						"rekt",
					};
			}break;
			case 5:{
				pronouns = new String[]{
						"exploded.",
						"decided there was a better life worth living.",
						"exploded by the after shock.",
						"was shredded by the secondary explsion.",
						"exploded into pieces.",
						"could not handle the after shock.",
						"was feeling a little greedy, blindly walking into the demolition zone.",
						"was feeling brave, but didn't seem all that tough after all!",
					};
			}break;
			case 6:{
				pronouns = new String[]{
						"was roasted in a fiery oven.",
						"was cooked by the vicious flames of lava.",
						"tried to find the Underworld.",
						"tried to swim in lava.",
						"died to lava. Learn2P1ay skrub.",
						"died in a pool of lava.",
						"probably fell off a ledge into lava again.",
						"fell into lava.",
						"was playing with fire.",
						"could not handle the intense heat.",
						"turned to ashes.",
						"instantly vaporized in a fiery hell.",
						"tried out a fiery bath. It was too hot.",
						"did not live to the fiery duress that is lava.",
					};
			}break;
			case 7:{
				pronouns = new String[]{
						"got stomped on viciously.",
						"got smooshed on by the Leap attack.",
						"got squished on by the Leap attack.",
						"did not survive the Leap attack.",
						"got obliterated by the Leap attack.",
						"got obliterated by the Leap attack.",
						"got mashed into the ground from the Leap attack.",
						"got smashed into the ground from the Leap attack..",
						"did not see the clearly imprinted Yellow Glass on the ground, dying to the Leap attack.",
					};
			}break;
			case 8:{
				pronouns = new String[]{
						"took on too much from the Stored Energy attack.",
						"got steamrolled by the Stored Energy attack.",
						"did not pay attention to the Stored Energy attack.",
						"was put into the grave by the Stored Energy attack.",
						"was killed by the Stored Energy attack.",
					};
			}break;
			case 9:{
				pronouns = new String[]{
						"drowned.",
						"ran out of air.",
						"inhaled too much water.",
						"could not find any oxygen.",
						"could not reach the surface in time.",
						"was not paying attention to their air meter.",
						"could not breathe underwater.",
						"is now swimming with the fishes.",
						"took a last gasp of water before perishing in the sea.",
						"could not make it to the surface.",
					};
			}break;
			case 10:{
				pronouns = new String[]{
						"smashed",
						"squished",
						"smothered",
						"flattened",
						"destroyed",
						"blasted",
						"owned",
					};
			}break;
			case 11:{
				pronouns = new String[]{
						"was electrocuted by Lightning.",
						"was deleted by Lightning.",
						"was blasted by Lightning.",
						"received a painful electric shock from Lightning.",
						"died to Lightning. Stop flying those kites in this weather!",
						"was struck by Lightning. The Gods did not deem them worthy of living.",
						"got struck by Lightning.",
						"got killed by Lightning.",
					};
			}break;
			case 12:{
				pronouns = new String[]{
						"was wracked by",
						"was killed by the disasterous effects of",
						"took too much",
						"could not handle",
						"died to",
						"killed by vile ",
						"killed by deadly ",
					};
			}break;
			case 13:{
				pronouns = new String[]{
						"withered away.",
						"withered away...Never to be seen again/",
						"did not respect the Wither effect damage.",
						"could not handle the Wither effect.",
						"slowly withered into oblivion.",
						"withered.",
						"died to the Wither effect.",
						"was under the effects of Wither for just too long.",
						"could not shake off the last few ticks of the Wither effect in time.",
						"almost lived through the Wither effect.",
						"slowly withered away.",
					};
			}break;
			case 14:{
				pronouns = new String[]{
						"starved.",
						"died of starvation.",
						"starved to death. They should have ate that last piece "+Pronouns.ChoosePronoun(15),
						"thought they could live without eating that piece of "+Pronouns.ChoosePronoun(15),
					};
			}break;
			case 15:{
				pronouns = new String[]{
						"beef",
						"chicken",
						"pork",
						"rabbit",
						"bread",
						"beetroot",
						"fish",
						"cake",
						"salmon",
						"rotten flesh",
						"potato",
						"melon",
						"pie",
					};
			}
			case 16:{
				pronouns = new String[]{
						"thought living inside a block was a good idea.",
						"suffocated.",
						"died to a block.",
						"got owned by a block.",
						"somehow ended up trapped inside a wall.",
						"somehow ended up trapped inside a block.",
						"ended up suffocating.",
					};
			}
		}
		
		return pronouns[(int)(Math.random()*pronouns.length)];
	}
}

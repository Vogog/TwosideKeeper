package sig.plugin.TwosideKeeper.HelperStructures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import sig.plugin.TwosideKeeper.TwosideKeeper;
import sig.plugin.TwosideKeeper.HelperStructures.Common.GenericFunctions;

public enum ItemSet {
	PANROS(1,1, 6,4, 10,10, 20,10),
	SONGSTEEL(4,2, 6,2, 8,8, 20,10),
	DAWNTRACKER(2,1, 20,10, 20,10, 10,5),
	LORASYS(2,2, 0,0, 0,0, 0,0),
	JAMDAK(3,3, 5,1, 10,1, 10,2), //Graceful Dodge is in ticks.
	DARNYS(2,1, 10,5, 20,5, 1,1),
	ALIKAHN(3,1, 15,6, 30,10, 12,6),
	LORASAADI(4,1, 4,2, 8,6, 8,3),
	MOONSHADOW(4,2, 1,1, 8,8, 15,7),
	GLADOMAIN(1,1, 12,10, 8,8, 1,1),
	WOLFSBANE(2,1, 15,10, 10,5, 15,10),
	ALUSTINE(3,2, 300,-30, 50,-5, 6,2);
	
	int baseval;
	int increase_val;
	int baseval_bonus2;
	int increase_val_bonus2;
	int baseval_bonus3;
	int increase_val_bonus3;
	int baseval_bonus4;
	int increase_val_bonus4;
	
	ItemSet(int baseval,int increase_val,
			int baseval2,int increase_val2,
			int baseval3,int increase_val3,
			int baseval4,int increase_val4) {
		this.baseval=baseval;
		this.increase_val=increase_val;
		this.baseval_bonus2=baseval2;
		this.increase_val_bonus2=increase_val2;
		this.baseval_bonus3=baseval3;
		this.increase_val_bonus3=increase_val3;
		this.baseval_bonus4=baseval4;
		this.increase_val_bonus4=increase_val4;
	} 
	
	public static boolean isSetItem(ItemStack item) {
		return GetSet(item)!=null;
	}

	public static ItemSet GetSet(ItemStack item) {
		if ((GenericFunctions.isEquip(item) || GenericFunctions.isSkullItem(item)) &&
				item.getItemMeta().hasLore()) {
			List<String> lore = item.getItemMeta().getLore();
			for (int i=0;i<lore.size();i++) {
				if (lore.get(i).contains(ChatColor.GOLD+""+ChatColor.BOLD+"T") && !lore.get(i).contains("Recipe")) {
					//This is the tier line.
					return ItemSet.valueOf(lore.get(i).replace(ChatColor.GOLD+""+ChatColor.BOLD+"T", "").split(" ")[1].toUpperCase());
				}
			}
		} 
		return null;
	}
	
	public static int GetTier(ItemStack item) {
		if (isSetItem(item) &&
				item.getItemMeta().hasLore()) {
			List<String> lore = item.getItemMeta().getLore();
			for (int i=0;i<lore.size();i++) {
				if (lore.get(i).contains(ChatColor.GOLD+""+ChatColor.BOLD+"T")) {
					//This is the tier line.
					return Integer.parseInt(lore.get(i).replace(ChatColor.GOLD+""+ChatColor.BOLD+"T", "").split(" ")[0]);
				}
			}
			TwosideKeeper.log(ChatColor.RED+"[ERROR] Could not detect proper tier of "+item.toString()+"!", 1);
		} 
		return 0;
	}
	
	public static void SetTier(ItemStack item, int tier) {
		boolean found=false;
		if (isSetItem(item) &&
				item.getItemMeta().hasLore()) {
			List<String> lore = item.getItemMeta().getLore();
			for (int i=0;i<lore.size();i++) {
				if (lore.get(i).contains(ChatColor.GOLD+""+ChatColor.BOLD+"T")) {
					//This is the tier line.
					int oldtier=GetTier(item);
					//TwosideKeeper.log("In lore: "+lore.get(i)+". Old tier: "+oldtier,2);
					lore.set(i, lore.get(i).replace("T"+oldtier, "T"+tier));
					found=true;
					break;
				}
			}
			ItemMeta m = item.getItemMeta();
			m.setLore(lore);
			item.setItemMeta(m);
			GenericFunctions.UpdateItemLore(item); //Update this item now that we upgraded the tier.
			GenericFunctions.ConvertSetColor(item, GetSet(item));
			if (!found) {
				TwosideKeeper.log(ChatColor.RED+"[ERROR] Could not detect proper tier of "+item.toString()+"!", 1);
			}
		} 
	}

	public static int GetBaseAmount(ItemSet set, int tier, int stat) {
		//stat will be 1 for the base value, 2 for the 2-piece set bonus, 3 for the 3-piece set bonus, and 4 for the 4-piece set bonus.
		switch (stat) {
			case 1:{
				return set.baseval+((tier-1)*set.increase_val);
			}
			case 2:{
				return set.baseval_bonus2+((tier-1)*set.increase_val_bonus2);
			}
			case 3:{
				return set.baseval_bonus3+((tier-1)*set.increase_val_bonus3);
			}
			case 4:{
				return set.baseval_bonus4+((tier-1)*set.increase_val_bonus4);
			}
		}
		TwosideKeeper.log(ChatColor.RED+"Error occurred while attempting to grab the Base Amount!!!", 1);
		return -1;
	}
	
	public int GetBaseAmount(ItemStack item) {
		return baseval+((GetTier(item)-1)*increase_val);
	}
	
	public static int GetSetCount(ItemStack[] equips, ItemSet set, LivingEntity ent) {
		int count = 0;
		for (ItemStack item : equips) {
			ItemSet temp = ItemSet.GetSet(item);
			if (temp!=null) {
				if (temp.equals(set)) {
					count++;
				}
			}
		}
		TwosideKeeper.log("Currently have "+count+" pieces from the "+set.name()+" set.", 5);
		return count;
	}
	
	public static int GetTierSetCount(ItemStack[] equips, ItemSet set, int tier, LivingEntity ent) {
		int count = 0;
		for (ItemStack item : equips) {
			ItemSet temp = ItemSet.GetSet(item);
			if (temp!=null) {
				if (temp.equals(set) && GetTier(item)==tier) {
					count++;
				}
			}
		}
		TwosideKeeper.log("Currently have "+count+" pieces from the "+set.name()+" set of Tier +"+tier+".", 5);
		return count;
	}
	
	public static int GetTotalBaseAmount(ItemStack[] equips, LivingEntity ent, ItemSet set) {
		int count = 0;
		for (ItemStack item : equips) {
			ItemSet temp = ItemSet.GetSet(item);
			if (temp!=null) {
				if (temp.equals(set)) {
					count += set.GetBaseAmount(item);
				}
			}
		}
		TwosideKeeper.log("Base Total of all equipment from this set is "+count, 5);
		return count;
	}
	
	public static boolean hasFullSet(ItemStack[] equips, LivingEntity ent, ItemSet set) {
		//Return a mapping of all tier values that meet the count requirement for that set.
		for (ItemStack item : equips) {
			ItemSet temp = ItemSet.GetSet(item);
			if (temp!=null) {
				int tier = ItemSet.GetTier(item);
				int detectedsets = ItemSet.GetTierSetCount(equips, set, tier, ent);
				TwosideKeeper.log("Sets: "+detectedsets, 5);
				if (detectedsets>=5) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static List<Integer> GetSetBonusCount(ItemStack[] equips, LivingEntity ent, ItemSet set, int count) {
		//Return a mapping of all tier values that meet the count requirement for that set.
		List<Integer> mapping = new ArrayList<Integer>();
		for (ItemStack item : equips) {
			ItemSet temp = ItemSet.GetSet(item);
			if (temp!=null) {
				int tier = ItemSet.GetTier(item);
				if (ItemSet.GetTierSetCount(equips, set, tier, ent)>=count) {
					if (!mapping.contains(tier)) {
						mapping.add(tier);
					}
				}
			}
		}
		return mapping;
	}

	public static boolean HasSetBonusBasedOnSetBonusCount(ItemStack[] equips, Player p, ItemSet set, int count) {
		//Similar to HasFullSet, but lets you decide how many pieces to check for from that particular set and matching tiers.
		return ItemSet.GetSetBonusCount(equips, p, set, count).size()>0;
	}

	public static double TotalBaseAmountBasedOnSetBonusCount(ItemStack[] equips, Player p, ItemSet set, int count, int set_bonus) {
		double amt = 0.0;
		List<Integer> mapping = ItemSet.GetSetBonusCount(equips, p, set, count);
		for (Integer tier : mapping) {
			amt+=ItemSet.GetBaseAmount(set, tier, set_bonus);
		}
		return amt;
	}

	public static Collection<? extends String> GenerateLore(ItemSet set, int tier) {
		List<String> lore = new ArrayList<String>();
		switch (set) {
			case PANROS:{
				lore.add(ChatColor.LIGHT_PURPLE+"Striker Gear");
				lore.add(ChatColor.GOLD+""+ChatColor.BOLD+"T"+tier+" Panros Set");
				lore.add(ChatColor.YELLOW+"+"+ItemSet.GetBaseAmount(set, tier, 1)+" Damage");
			}break;
			case SONGSTEEL:{
				lore.add(ChatColor.LIGHT_PURPLE+"Defender Gear");
				lore.add(ChatColor.GOLD+""+ChatColor.BOLD+"T"+tier+" Songsteel Set");
				lore.add(ChatColor.YELLOW+"+"+ItemSet.GetBaseAmount(set, tier, 1)+"% Block Chance");
			}break;
			case DAWNTRACKER:{
				lore.add(ChatColor.LIGHT_PURPLE+"Barbarian Gear");
				lore.add(ChatColor.GOLD+""+ChatColor.BOLD+"T"+tier+" Dawntracker Set");
				lore.add(ChatColor.YELLOW+"-"+(ItemSet.GetBaseAmount(set, tier, 1)/3)+" Damage taken per hit");
			}break;
			case LORASYS:{
				lore.add(ChatColor.LIGHT_PURPLE+"Slayer Gear");
				lore.add(ChatColor.GOLD+""+ChatColor.BOLD+"T"+tier+" Lorasys Set");
				lore.add(ChatColor.YELLOW+"+"+ItemSet.GetBaseAmount(set, tier, 1)+" Damage");
			}break;
			case JAMDAK:{
				lore.add(ChatColor.LIGHT_PURPLE+"Ranger Gear");
				lore.add(ChatColor.GOLD+""+ChatColor.BOLD+"T"+tier+" Jamdak Set");
				lore.add(ChatColor.YELLOW+"+"+ItemSet.GetBaseAmount(set, tier, 1)+"% Dodge Chance");
			}break;
			case DARNYS:{
				lore.add(ChatColor.LIGHT_PURPLE+"Ranger Gear");
				lore.add(ChatColor.GOLD+""+ChatColor.BOLD+"T"+tier+" Darnys Set");
				lore.add(ChatColor.YELLOW+"+"+ItemSet.GetBaseAmount(set, tier, 1)+"% Dodge Chance");
			}break;
			case ALIKAHN:{
				lore.add(ChatColor.LIGHT_PURPLE+"Ranger Gear");
				lore.add(ChatColor.GOLD+""+ChatColor.BOLD+"T"+tier+" Alikahn Set");
				lore.add(ChatColor.YELLOW+"+"+ItemSet.GetBaseAmount(set, tier, 1)+"% Dodge Chance");
			}break;
			case LORASAADI:{
				lore.add(ChatColor.LIGHT_PURPLE+"Ranger Gear");
				lore.add(ChatColor.GOLD+""+ChatColor.BOLD+"T"+tier+" Lorasaadi Set");
				lore.add(ChatColor.YELLOW+"+"+ItemSet.GetBaseAmount(set, tier, 1)+"% Dodge Chance");
			}break;
			case GLADOMAIN:{
				lore.add(ChatColor.LIGHT_PURPLE+"Slayer Amulet");
				lore.add(ChatColor.GOLD+""+ChatColor.BOLD+"T"+tier+" Gladomain Set");
				lore.add(ChatColor.YELLOW+"+"+ItemSet.GetBaseAmount(set, tier, 1)+" HP");
			}break;
			case MOONSHADOW:{
				lore.add(ChatColor.LIGHT_PURPLE+"Slayer Trinket");
				lore.add(ChatColor.GOLD+""+ChatColor.BOLD+"T"+tier+" Moonshadow Set");
				lore.add(ChatColor.YELLOW+"+"+ItemSet.GetBaseAmount(set, tier, 1)+"% Crit Damage");
			}break;
			case WOLFSBANE:{
				lore.add(ChatColor.LIGHT_PURPLE+"Slayer Ornament");
				lore.add(ChatColor.GOLD+""+ChatColor.BOLD+"T"+tier+" Wolfsbane Set");
				lore.add(ChatColor.YELLOW+"+"+ItemSet.GetBaseAmount(set, tier, 1)+"% Critical Chance");
			}break;
			case ALUSTINE:{
				lore.add(ChatColor.LIGHT_PURPLE+"Slayer Charm");
				lore.add(ChatColor.GOLD+""+ChatColor.BOLD+"T"+tier+" Alustine Set");
				lore.add(ChatColor.YELLOW+"+"+ItemSet.GetBaseAmount(set, tier, 1)+"% EXP Gain");
			}break;
			}
		
		lore.add("");
		
		switch (set) {
			case PANROS:{
				lore.add(ChatColor.GOLD+""+ChatColor.ITALIC+"Set Bonus:");
				lore.add(ChatColor.DARK_AQUA+" 2 - "+ChatColor.WHITE+" +"+ItemSet.GetBaseAmount(set, tier, 2)+" Damage");
				lore.add(ChatColor.DARK_AQUA+" 3 - "+ChatColor.WHITE+" +"+ItemSet.GetBaseAmount(set, tier, 3)+"% Dodge Chance");
				lore.add(ChatColor.DARK_AQUA+" 4 - "+ChatColor.WHITE+" +"+ItemSet.GetBaseAmount(set, tier, 4)+"% Critical Chance");
				lore.add(ChatColor.DARK_AQUA+" 5 - "+ChatColor.WHITE+" Powered Line Drive"); 
				lore.add(ChatColor.GRAY+"    Press the drop key while performing the");
				lore.add(ChatColor.GRAY+"    first line drive to line drive a second");
				lore.add(ChatColor.GRAY+"    time in another direction.");
			}break;
			case SONGSTEEL:{
				lore.add(ChatColor.GOLD+""+ChatColor.ITALIC+"Set Bonus:");
				lore.add(ChatColor.DARK_AQUA+" 2 - "+ChatColor.WHITE+" +"+ItemSet.GetBaseAmount(set, tier, 2)+" Max Health");
				lore.add(ChatColor.DARK_AQUA+" 3 - "+ChatColor.WHITE+" +"+ItemSet.GetBaseAmount(set, tier, 3)+" Absorption Health (30 seconds)");
				lore.add(ChatColor.DARK_AQUA+" 4 - "+ChatColor.WHITE+" +"+ItemSet.GetBaseAmount(set, tier, 4)+"% Damage Reduction");
				lore.add(ChatColor.DARK_AQUA+" 5 - "+ChatColor.WHITE+" Vendetta");
				lore.add(ChatColor.GRAY+"    Blocking stores 25% of mitigation damage.");
				lore.add(ChatColor.GRAY+"    1% of damage is stored as thorns true damage.");
				lore.add(ChatColor.GRAY+"    Shift+Left-Click with a shield to unleash");
				lore.add(ChatColor.GRAY+"    all of your stored mitigation damage.");
			}break;
			case DAWNTRACKER:{
				lore.add(ChatColor.GOLD+""+ChatColor.ITALIC+"Set Bonus:");
				lore.add(ChatColor.DARK_AQUA+" 2 - "+ChatColor.WHITE+" +"+ItemSet.GetBaseAmount(set, tier, 2)+"% Debuff Resistance");
				lore.add(ChatColor.DARK_AQUA+" 3 - "+ChatColor.WHITE+" +"+ItemSet.GetBaseAmount(set, tier, 3)+"% Lifesteal");
				lore.add(ChatColor.DARK_AQUA+" 4 - "+ChatColor.WHITE+" +"+ItemSet.GetBaseAmount(set, tier, 4)+" Max Health");
				lore.add(ChatColor.DARK_AQUA+" 5 - "+ChatColor.WHITE+" Powered Mock");
				lore.add(ChatColor.GRAY+"    Mock debuff duration increases from");
				lore.add(ChatColor.GRAY+"    10 -> 20 seconds, making it stackable.");
				lore.add(ChatColor.GRAY+"    All Lifesteal Stacks and Weapon Charges");
				lore.add(ChatColor.GRAY+"    gained are doubled.");
			}break;
			case LORASYS:{
				lore.add(ChatColor.GOLD+""+ChatColor.ITALIC+"Bonus Effects");
				lore.add(ChatColor.WHITE+"    Stealth does not cause durability to decrease.");
				lore.add(ChatColor.WHITE+"    Hitting enemies with Thorns does not damage you.");
				lore.add(ChatColor.WHITE+"    Each kill restores 2 Hearts (4 HP) instead of 1.");
			}break;
			case JAMDAK: {
				lore.add(ChatColor.GOLD+""+ChatColor.ITALIC+"Set Bonus:");
				lore.add(ChatColor.DARK_AQUA+" 2 - "+ChatColor.WHITE+" +"+ItemSet.GetBaseAmount(set, tier, 2)+"% Dodge Chance");
				lore.add(ChatColor.DARK_AQUA+" 3 - "+ChatColor.WHITE+" +"+ItemSet.GetBaseAmount(set, tier, 3)+"% Dodge Chance");
				lore.add(ChatColor.DARK_AQUA+" 4 - "+ChatColor.WHITE+" +"+(ItemSet.GetBaseAmount(set, tier, 4)/20d)+"s Graceful Dodge");
				lore.add(ChatColor.GRAY+"      Gives you invulnerability and "+(ItemSet.GetBaseAmount(set, tier, 4)/4)+" absorption");
				lore.add(ChatColor.GRAY+"      health for each successful dodge.");
				lore.add(ChatColor.DARK_AQUA+" 5 - "+ChatColor.WHITE+" Boosts All Modes of Ranger");
				lore.add(ChatColor.GRAY+"    "+ChatColor.WHITE+"Close Range Mode:");
				lore.add(ChatColor.GRAY+"      Increases Tumble Invincibility from");
				lore.add(ChatColor.GRAY+"      1 -> 3 seconds");
				lore.add(ChatColor.GRAY+"    "+ChatColor.WHITE+"Sniper Mode:");
				lore.add(ChatColor.GRAY+"      Increases Critical Damage by +100%");
				lore.add(ChatColor.GRAY+"    "+ChatColor.WHITE+"Debilitation Mode:");
				lore.add(ChatColor.GRAY+"      Increases Armor Penetration by +50%.");
			}break;
			case DARNYS: {
				lore.add(ChatColor.GOLD+""+ChatColor.ITALIC+"Set Bonus:");
				lore.add(ChatColor.DARK_AQUA+" 2 - "+ChatColor.WHITE+" +"+ItemSet.GetBaseAmount(set, tier, 2)+"% Damage Reduction");
				lore.add(ChatColor.DARK_AQUA+" 3 - "+ChatColor.WHITE+" +"+ItemSet.GetBaseAmount(set, tier, 3)+"% Damage Reduction");
				lore.add(ChatColor.DARK_AQUA+" 4 - "+ChatColor.WHITE+" Swift Aegis "+WorldShop.toRomanNumeral(ItemSet.GetBaseAmount(set, tier, 4)));
				lore.add(ChatColor.GRAY+"      Builds "+ItemSet.GetBaseAmount(set, tier, 4)+" stack"+((ItemSet.GetBaseAmount(set, tier, 4))!=1?"s":"")+" of Resist");
				lore.add(ChatColor.GRAY+"      (20% Damage Reduction) every 5 seconds of sprinting,");
				lore.add(ChatColor.GRAY+"      and with every Tumble. Each hit taken removes one");
				lore.add(ChatColor.GRAY+"      stack of Resist. Caps at Resist 10. Lasts 20 seconds.");
				lore.add(ChatColor.DARK_AQUA+" 5 - "+ChatColor.WHITE+" Boosts All Modes of Ranger");
				lore.add(ChatColor.GRAY+"    "+ChatColor.WHITE+"Close Range Mode:");
				lore.add(ChatColor.GRAY+"      Increases Tumble Invincibility from");
				lore.add(ChatColor.GRAY+"      1 -> 3 seconds");
				lore.add(ChatColor.GRAY+"    "+ChatColor.WHITE+"Sniper Mode:");
				lore.add(ChatColor.GRAY+"      Increases Critical Damage by +100%");
				lore.add(ChatColor.GRAY+"    "+ChatColor.WHITE+"Debilitation Mode:");
				lore.add(ChatColor.GRAY+"      Increases Armor Penetration by +50%.");
			}break;
			case ALIKAHN: {
				lore.add(ChatColor.GOLD+""+ChatColor.ITALIC+"Set Bonus:");
				lore.add(ChatColor.DARK_AQUA+" 2 - "+ChatColor.WHITE+" +"+ItemSet.GetBaseAmount(set, tier, 2)+" Max Health");
				lore.add(ChatColor.DARK_AQUA+" 3 - "+ChatColor.WHITE+" +"+ItemSet.GetBaseAmount(set, tier, 3)+" Max Health");
				lore.add(ChatColor.DARK_AQUA+" 4 - "+ChatColor.WHITE+" +"+ItemSet.GetBaseAmount(set, tier, 4)+" Health Regen / 5 seconds");
				lore.add(ChatColor.DARK_AQUA+" 5 - "+ChatColor.WHITE+" Boosts All Modes of Ranger");
				lore.add(ChatColor.GRAY+"    "+ChatColor.WHITE+"Close Range Mode:");
				lore.add(ChatColor.GRAY+"      Increases Tumble Invincibility from");
				lore.add(ChatColor.GRAY+"      1 -> 3 seconds");
				lore.add(ChatColor.GRAY+"    "+ChatColor.WHITE+"Sniper Mode:");
				lore.add(ChatColor.GRAY+"      Increases Critical Damage by +100%");
				lore.add(ChatColor.GRAY+"    "+ChatColor.WHITE+"Debilitation Mode:");
				lore.add(ChatColor.GRAY+"      Increases Armor Penetration by +50%.");
			}break;
			case LORASAADI:{
				lore.add(ChatColor.GOLD+""+ChatColor.ITALIC+"Set Bonus:");
				lore.add(ChatColor.DARK_AQUA+" 2 - "+ChatColor.WHITE+" +"+ItemSet.GetBaseAmount(set, tier, 2)+" Damage");
				lore.add(ChatColor.DARK_AQUA+" 3 - "+ChatColor.WHITE+" +"+ItemSet.GetBaseAmount(set, tier, 3)+" Damage");
				lore.add(ChatColor.DARK_AQUA+" 4 - "+ChatColor.WHITE+" +"+ItemSet.GetBaseAmount(set, tier, 4)+" Execution Damage");
				lore.add(ChatColor.DARK_AQUA+"         per 20% Missing Health");
				lore.add(ChatColor.DARK_AQUA+" 5 - "+ChatColor.WHITE+" Boosts All Modes of Ranger");
				lore.add(ChatColor.GRAY+"    "+ChatColor.WHITE+"Close Range Mode:");
				lore.add(ChatColor.GRAY+"      Increases Tumble Invincibility from");
				lore.add(ChatColor.GRAY+"      1 -> 3 seconds");
				lore.add(ChatColor.GRAY+"    "+ChatColor.WHITE+"Sniper Mode:");
				lore.add(ChatColor.GRAY+"      Increases Critical Damage by +100%");
				lore.add(ChatColor.GRAY+"    "+ChatColor.WHITE+"Debilitation Mode:");
				lore.add(ChatColor.GRAY+"      Increases Armor Penetration by +50%.");
			}break;
			case GLADOMAIN:{
				lore.add(ChatColor.GOLD+""+ChatColor.ITALIC+"Set Bonus:");
				lore.add(ChatColor.DARK_AQUA+" 2 - "+ChatColor.WHITE+" +"+ItemSet.GetBaseAmount(set, tier, 2)+"% Cooldown Reduction");
				lore.add(ChatColor.DARK_AQUA+" 3 - "+ChatColor.WHITE+" +"+ItemSet.GetBaseAmount(set, tier, 3)+"% Dodge Chance");
				lore.add(ChatColor.DARK_AQUA+" 5 - "+ChatColor.WHITE+" Life Saver ");
				lore.add(ChatColor.GRAY+"      When about to be killed, puts you into");
				lore.add(ChatColor.GRAY+"      stealth, applies Speed IV for 10 seconds, adds");
				lore.add(ChatColor.GRAY+"      invulnerability, and de-aggros all current");
				lore.add(ChatColor.GRAY+"      targets.");
				lore.add(ChatColor.WHITE+"        5 Minute Cooldown");
				lore.add(ChatColor.DARK_AQUA+" 7 - "+ChatColor.WHITE+" Provides the Following Bonuses:");
				lore.add(ChatColor.GRAY+"    "+ChatColor.WHITE+"A successful Assassination grants 100%");
				lore.add(ChatColor.GRAY+"    "+ChatColor.WHITE+"Critical Strike Chance and 100% Dodge");
				lore.add(ChatColor.GRAY+"    "+ChatColor.WHITE+"chance for the next hit. Dodge Chance");
				lore.add(ChatColor.GRAY+"    "+ChatColor.WHITE+"increases by +"+(5+ItemSet.GetBaseAmount(set, tier, 4))+"% per 1m/sec of movement");
				lore.add(ChatColor.GRAY+"    "+ChatColor.WHITE+"speed.");
			}break;
			case MOONSHADOW:{
				lore.add(ChatColor.GOLD+""+ChatColor.ITALIC+"Set Bonus:");
				lore.add(ChatColor.DARK_AQUA+" 2 - "+ChatColor.WHITE+" Applies Poison "+WorldShop.toRomanNumeral(ItemSet.GetBaseAmount(set, tier, 2))+ChatColor.GRAY+" (0:15)");
				lore.add(ChatColor.DARK_AQUA+" 3 - "+ChatColor.WHITE+" +"+ItemSet.GetBaseAmount(set, tier, 3)+"% Damage");
				lore.add(ChatColor.DARK_AQUA+" 5 - "+ChatColor.WHITE+" +"+ItemSet.GetBaseAmount(set, tier, 4)+"% Critical Chance");
				lore.add(ChatColor.DARK_AQUA+" 7 - "+ChatColor.WHITE+" Provides the Following Bonuses:");
				lore.add(ChatColor.GRAY+"    "+ChatColor.WHITE+"Strength Cap Increases to 40. 2 Stacks per kill.");
				lore.add(ChatColor.GRAY+"    "+ChatColor.WHITE+"Successful Assassinations apply damage");
				lore.add(ChatColor.GRAY+"      "+ChatColor.WHITE+"in an AoE Range.");
				lore.add(ChatColor.GRAY+"    "+ChatColor.WHITE+"Slayers can drop aggro by sneaking");
				lore.add(ChatColor.GRAY+"      "+ChatColor.WHITE+"for Three seconds.");
				lore.add(ChatColor.GRAY+"    "+ChatColor.WHITE+"While in Stealth Mode you gain 40%");
				lore.add(ChatColor.GRAY+"      "+ChatColor.WHITE+"Dodge Chance");
			}break;
			case WOLFSBANE:{
				lore.add(ChatColor.GOLD+""+ChatColor.ITALIC+"Set Bonus:");
				lore.add(ChatColor.DARK_AQUA+" 2 - "+ChatColor.WHITE+" Recovers "+ItemSet.GetBaseAmount(set, tier, 2)+"% Cooldown on Assassination per kill");
				lore.add(ChatColor.DARK_AQUA+" 3 - "+ChatColor.WHITE+" Applies Speed V when Assassination is casted. Suppresses");
				lore.add(ChatColor.DARK_AQUA+"     "+ChatColor.WHITE+"      the target for "+(ItemSet.GetBaseAmount(set, tier, 3)/20d)+"s");
				lore.add(ChatColor.DARK_AQUA+" 5 - "+ChatColor.WHITE+" Gain "+(ItemSet.GetBaseAmount(set, tier, 4)/20d)+" seconds of invulnerability after");
				lore.add(ChatColor.DARK_AQUA+"     "+ChatColor.WHITE+"      Assassination is casted.");
				lore.add(ChatColor.DARK_AQUA+" 7 - "+ChatColor.WHITE+" Provides the Following Bonuses:");
				lore.add(ChatColor.GRAY+"    "+ChatColor.WHITE+"Backstabs heal 2 HP (1 Heart). Assassination cooldown reduced");
				lore.add(ChatColor.GRAY+"    "+ChatColor.WHITE+"to 2 seconds when used on a target closer than 5 meters.");
			}break;
			case ALUSTINE:{
				lore.add(ChatColor.GOLD+""+ChatColor.ITALIC+"Set Bonus:");
				lore.add(ChatColor.DARK_AQUA+" 2 - "+ChatColor.WHITE+" Gain immunity to Explosions.");
				lore.add(ChatColor.DARK_AQUA+"     "+ChatColor.WHITE+"      Consumes "+ChatColor.YELLOW+ItemSet.GetBaseAmount(set, tier, 2)+" XP"+ChatColor.WHITE+" per absorbed hit.");
				lore.add(ChatColor.DARK_AQUA+"       "+ChatColor.GRAY+ChatColor.ITALIC+"Must have at least "+ChatColor.YELLOW+ChatColor.ITALIC+ItemSet.GetBaseAmount(set, tier, 2)+" XP"+ChatColor.GRAY+ChatColor.ITALIC+" to trigger.");
				lore.add(ChatColor.DARK_AQUA+" 3 - "+ChatColor.WHITE+" Resists all fire, poison, and wither damage.");
				lore.add(ChatColor.DARK_AQUA+"     "+ChatColor.WHITE+"      Consumes "+ChatColor.YELLOW+ItemSet.GetBaseAmount(set, tier, 3)+" XP"+ChatColor.WHITE+" per absorbed hit.");
				lore.add(ChatColor.DARK_AQUA+"       "+ChatColor.GRAY+ChatColor.ITALIC+"Must have at least "+ChatColor.YELLOW+ChatColor.ITALIC+ItemSet.GetBaseAmount(set, tier, 3)+" XP"+ChatColor.GRAY+ChatColor.ITALIC+" to trigger.");
				lore.add(ChatColor.DARK_AQUA+" 5 - "+ChatColor.WHITE+" Backstabs spill "+ChatColor.YELLOW+ItemSet.GetBaseAmount(set, tier, 4)+" XP"+ChatColor.WHITE+" out from the target hit.");
				lore.add(ChatColor.DARK_AQUA+"     "+ChatColor.WHITE+"      Collecting experience has a "+Math.min((ItemSet.GetBaseAmount(set, tier, 4)/20d)*100d,100)+"% chance");
				lore.add(ChatColor.DARK_AQUA+"     "+ChatColor.WHITE+"      to restore 2 HP (1 Heart).");
				lore.add(ChatColor.DARK_AQUA+" 7 - "+ChatColor.WHITE+" Provides the Following Bonuses:");
				lore.add(ChatColor.GRAY+"    "+ChatColor.WHITE+"Deal additional base damage equal to the");
				lore.add(ChatColor.GRAY+"    "+ChatColor.WHITE+"number of levels you have. Drains XP equal");
				lore.add(ChatColor.GRAY+"    "+ChatColor.WHITE+"to the number of levels you have per hit.");
			}break;
		}
		return lore;
	}

	public static void SetItemSet(ItemStack item, ItemSet set) {
		//Convert this item to a different set.
		boolean found=false;
		if (isSetItem(item) &&
				item.getItemMeta().hasLore()) {
			List<String> lore = item.getItemMeta().getLore();
			for (int i=0;i<lore.size();i++) {
				if (lore.get(i).contains(ChatColor.GOLD+""+ChatColor.BOLD+"T")) {
					//This is the tier line.
					ItemSet oldset=GetSet(item);
					//TwosideKeeper.log("In lore: "+lore.get(i)+". Old tier: "+oldtier,2);
					//lore.set(i, lore.get(i).replace("T"+oldtier, "T"+tier));
					lore.set(i, lore.get(i).replace(GenericFunctions.CapitalizeFirstLetters(oldset.name()), GenericFunctions.CapitalizeFirstLetters(set.name())));
					found=true;
					break;
				}
			}
			ItemMeta m = item.getItemMeta();
			m.setLore(lore);
			item.setItemMeta(m);
			GenericFunctions.UpdateItemLore(item); //Update this item now that we upgraded the tier.
			if (!found) {
				TwosideKeeper.log(ChatColor.RED+"[ERROR] Could not detect proper tier of "+item.toString()+"!", 1);
			}
		} 
	}
}

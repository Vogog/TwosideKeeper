package sig.plugin.TwosideKeeper;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import sig.plugin.TwosideKeeper.HelperStructures.ArtifactAbility;
import sig.plugin.TwosideKeeper.HelperStructures.ArtifactItem;
import sig.plugin.TwosideKeeper.HelperStructures.ItemSet;
import sig.plugin.TwosideKeeper.HelperStructures.Loot;
import sig.plugin.TwosideKeeper.HelperStructures.MonsterDifficulty;
import sig.plugin.TwosideKeeper.HelperStructures.MonsterType;
import sig.plugin.TwosideKeeper.HelperStructures.ServerType;
import sig.plugin.TwosideKeeper.HelperStructures.WorldShop;
import sig.plugin.TwosideKeeper.HelperStructures.Common.GenericFunctions;

public final class TwosideKeeperAPI {
	//MONEY COMMANDS.
	public static void givePlayerMoney(Player p, double amt) {
		TwosideKeeper.givePlayerMoney(p, amt);
	}
	public static void givePlayerMoney(String p, double amt) {
		TwosideKeeper.givePlayerMoney(p, amt);
	}
	public static double getPlayerMoney(Player p) {
		return TwosideKeeper.getPlayerMoney(p);
	}
	public static double getPlayerMoney(String p) {
		return TwosideKeeper.getPlayerMoney(p);
	}

	//BANK COMMANDS.
	public static void givePlayerBankMoney(Player p, double amt) {
		TwosideKeeper.givePlayerBankMoney(p, amt);
	}
	public static void givePlayerBankMoney(String p, double amt) {
		TwosideKeeper.givePlayerBankMoney(p, amt);
	}
	public static double getPlayerBankMoney(Player p) {
		return TwosideKeeper.getPlayerBankMoney(p);
	}
	public static double getPlayerBankMoney(String p) {
		return TwosideKeeper.getPlayerBankMoney(p);
	}
	
	//MONSTER COMMANDS.
	public static Monster spawnAdjustedMonster(MonsterType mt,Location loc) {
		return MonsterController.spawnAdjustedMonster(mt,loc);
	}
	public static Monster autoAdjustMonster(Monster m) {
		return MonsterController.convertMonster(m);
	}
	public static Monster adjustMonsterDifficulty(Monster m, MonsterDifficulty newdiff) {
		return MonsterController.convertMonster(m,newdiff);
	}
	public static MonsterDifficulty getMonsterDifficulty(Monster m) {
		return MonsterController.getMonsterDifficulty(m);
	}

	//Artifact Commands.
	public static boolean isArtifactItem(ItemStack item) {
		return Artifact.isArtifact(item);
	}
	public static boolean isArtifactEquip(ItemStack item) {
		return GenericFunctions.isArtifactEquip(item);
	}
	public static ItemStack dropArtifactItem(ArtifactItem type) {
		return Artifact.createArtifactItem(type);
	}
	public static ItemStack dropArtifactItem(ArtifactItem type,int amt) {
		return Artifact.createArtifactItem(type,amt);
	}
	public static ItemStack addArtifactEXP(ItemStack item, int amt, Player p) {
		return AwakenedArtifact.addPotentialEXP(item, amt, p);
	}
	public static boolean hasArtifactAbility(ArtifactAbility ability, ItemStack item) {
		return ArtifactAbility.containsEnchantment(ability, item);
	}
	public static int getArtifactAbilityLevel(ArtifactAbility ability, ItemStack item) {
		return ArtifactAbility.getEnchantmentLevel(ability, item);
	}
	public static double getArtifactAbilityValue(ArtifactAbility ability, ItemStack item) {
		return GenericFunctions.getAbilityValue(ability, item);
	}
	
	//Time Commands.
	public static long getServerTickTime() {
		return TwosideKeeper.getServerTickTime();
	}
	
	//Hardened Item Commands.
	public static boolean isHardenedItem(ItemStack i) {
		return GenericFunctions.isHardenedItem(i);
	}
	public static boolean isObscureHardenedItem(ItemStack i) {
		return GenericFunctions.isObscureHardenedItem(i);
	}
	public static int getHardenedItemBreaks(ItemStack i) {
		return GenericFunctions.getHardenedItemBreaks(i);
	}
	public static ItemStack addHardenedItemBreaks(ItemStack i, int breaks) {
		return GenericFunctions.addHardenedItemBreaks(i, breaks);
	}
	public static ItemStack addObscureHardenedItemBreaks(ItemStack i, int breaks) {
		return GenericFunctions.addObscureHardenedItemBreaks(i, breaks);
	}
	public static ItemStack breakHardenedItem(ItemStack i) {
		return GenericFunctions.breakHardenedItem(i,null);
	}
	public static ItemStack breakHardenedItem(ItemStack i, Player p) {
		return GenericFunctions.breakHardenedItem(i,p);
	}
	
	//Loot Commands.
	public static ItemStack generateMegaPiece(Material item, boolean hardened) {
		return Loot.GenerateMegaPiece(item, hardened);
	}
	public static ItemStack generateMegaPiece(Material item, boolean hardened, boolean isSetPiece) {
		return Loot.GenerateMegaPiece(item, hardened, isSetPiece);
	}
	public static ItemStack generateMegaPiece(Material item, boolean hardened, boolean isSetPiece, int basetier) {
		return Loot.GenerateMegaPiece(item, hardened, isSetPiece, basetier);
	}
	
	//Server COMMANDS.
	public static ServerType getServerType() {
		return TwosideKeeper.getServerType();
	}
	public static void announcePluginVersions() {
		TwosideKeeper.announcePluginVersions();
	}
	
	//Party COMMANDS.
	public static List<Player> getPartyMembers(Player p) {
		return PartyManager.getPartyMembers(p); //LEGACY CODE.
	}
	public static boolean IsInSameParty(Player p, Player p2) {
		return PartyManager.IsInSameParty(p, p2);
	}

	//Combat COMMANDS.
	public static double getModifiedDamage(double dmg_amt, LivingEntity p) {
		return TwosideKeeper.CalculateDamageReduction(dmg_amt, p, p);
	}
	@Deprecated
	public static void DealModifiedDamageToEntity(int dmg, LivingEntity damager, LivingEntity target) {
		GenericFunctions.DealDamageToMob(dmg, target, damager, false);
	}
	@Deprecated
	public static void DealTrueDamageToEntity(int dmg, LivingEntity damager, LivingEntity target) {
		GenericFunctions.DealDamageToMob(dmg, target, damager, true);
	}
	@Deprecated
	public static void DealModifiedDamageToEntity(ItemStack weapon, LivingEntity damager, LivingEntity target) {
		TwosideKeeper.DealDamageToMob(weapon, damager, target);
	}
	public static void DealDamageToEntity(double dmg, LivingEntity target, Entity damager) {
		GenericFunctions.DealDamageToMob(dmg, target, damager);
	}
	public static void DealDamageToEntity(double dmg, LivingEntity target, Entity damager, String reason) {
		GenericFunctions.DealDamageToMob(dmg, target, damager, null, reason);
	}
	/**
	 * Gets the final calculated damage with all offensive and defensive multipliers applied. This is a comprehensive
	 * damage calculation with the entire game's formula packed in.
	 * @param dmg The amount of base damage to provide. Using 0 uses the weapon the damager is carrying as the source damage.
	 * @param damager The damager entity. This can include projectiles with a valid shooter, which is later identified as the damager.
	 * @param target The targeted entity. This is the entity that all defensive calculations and on-hit effects will be applied to.
	 * @param isCriticalStrike Whether or not this is a forced critical strike.
	 * @return Returns the final calculated damage with all modifications applied, using the base damage, if provided. Unlike the
	 * version of this method with the "reason" argument, it will use a generic "Attack Base Damage" reason.
	 */
	public static double getFinalDamage(double dmg, Entity damager, LivingEntity target, boolean isCriticalStrike) {
		return NewCombat.applyDamage(dmg, target, damager, isCriticalStrike);
	}
	/**
	 * Gets the final calculated damage with all offensive and defensive multipliers applied. This is a comprehensive
	 * damage calculation with the entire game's formula packed in.
	 * @param dmg The amount of base damage to provide. Using 0 uses the weapon the damager is carrying as the source damage.
	 * @param damager The damager entity. This can include projectiles with a valid shooter, which is later identified as the damager.
	 * @param target The targeted entity. This is the entity that all defensive calculations and on-hit effects will be applied to.
	 * @param isCriticalStrike Whether or not this is a forced critical strike.
	 * @param reason The name of the base damage that will be displayed in the DPS logger.
	 * @return Returns the final calculated damage with all modifications applied, using the base damage, if provided.
	 */
	public static double getFinalDamage(double dmg, Entity damager, LivingEntity target, boolean isCriticalStrike, String reason) {
		return NewCombat.applyDamage(dmg, target, damager, isCriticalStrike, reason);
	}
	/**
	 * Makes the target vulnerable to the damager again by removing their last hit time.
	 * @param damager The damager that will have their no damage tick flag removed.
	 * @param target The target that will be vulnerable to the damager again.
	 */
	public static void removeNoDamageTick(LivingEntity damager, LivingEntity target) {
		GenericFunctions.removeNoDamageTick(target, damager);
	}

	//Message COMMANDS.
	public static void playMessageNotification(Player sender) {
		TwosideKeeper.playMessageNotification(sender);
	}
	public static void notifyBrokenItemToPlayer(ItemStack item, Player p) {
		TwosideKeeper.breakdownItem(item,p);
	}
	
	//Spleef COMMANDS.
	public static boolean isPlayingSpleef(Player p) {
		return SpleefManager.playerIsPlayingSpleef(p);
	}
	
	//Breaking COMMANDS.
	public static boolean hasPermissionToBreakSign(Sign s, Player p) {
		return GenericFunctions.hasPermissionToBreakSign(s,p);
	}
	
	//World Shop COMMANDS.
	public static boolean isWorldShop(Block b) {
		return WorldShop.shopSignExists(b);
	}
	public static boolean hasPermissionToBreakWorldShopSign(Sign s, Player p) {
		return WorldShop.hasPermissionToBreakWorldShopSign(s,p);
	}
	public static void removeWorldShopDisplayItem(Sign s) {
		WorldShop.removeShopItem(s);
	}
	public static boolean canPlaceShopSignOnBlock(Block block) {
		return WorldShop.canPlaceShopSignOnBlock(block);
	}
	
	//Recycling Center COMMANDS.
	public static boolean isRecyclingCenter(Block b) {
		return RecyclingCenter.isRecyclingCenter(b);
	}
	
	//Item Set COMMANDS.
	public static boolean isSetItem(ItemStack item) {
		return ItemSet.isSetItem(item);
	}
	/**
	 * 
	 * @param item
	 * @return The Item Set, or null if none found.
	 */
	public static ItemSet getItemSet(ItemStack item) {
		return ItemSet.GetSet(item);
	}
	public static int getItemTier(ItemStack item) {
		return ItemSet.GetTier(item);
	}
	
	//Localization COMMANDS.
	public static String getLocalizedItemName(ItemStack i) {
		return GenericFunctions.UserFriendlyMaterialName(i);
	}
	public static String getLocalizedItemName(Material i) {
		return GenericFunctions.UserFriendlyMaterialName(i);
	}
	public static String getLocalizedItemName(Material i, byte data) {
		return GenericFunctions.UserFriendlyMaterialName(i,data);
	}
	
	//Player COMMANDS.
	public static double getPlayerVelocity(Player p) {
		return GenericFunctions.GetPlayerVelocity(p);
	}
}

package sig.plugin.TwosideKeeper.HelperStructures.Common;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;

import sig.plugin.TwosideKeeper.TwosideKeeper;

public class GenericFunctions {

	public static int getHardenedItemBreaks(ItemStack item) {
		if (item.hasItemMeta() &&
				item.getItemMeta().hasLore()) {
			ItemMeta item_meta = item.getItemMeta();
			int breaks_remaining=-1;
			int loreline=-1;
			for (int i=0;i<item_meta.getLore().size();i++) {
				if (item_meta.getLore().get(i).contains(ChatColor.GRAY+"Breaks Remaining: ")) {
					return Integer.parseInt(item.getItemMeta().getLore().get(i).split(": "+ChatColor.YELLOW)[1]);
				}
			}
			return 0;
		}
		return 0;
	}

	public static ItemStack breakHardenedItem(ItemStack item) {
		int break_count = getHardenedItemBreaks(item);
		if (break_count>0) {
			ItemMeta m = item.getItemMeta();
			List<String> lore = item.getItemMeta().getLore();
			for (int i=0;i<lore.size();i++) {
				if (lore.get(i).contains(ChatColor.GRAY+"Breaks Remaining: ")) {
					lore.set(i, ChatColor.GRAY+"Breaks Remaining: "+ChatColor.YELLOW+(break_count-1));
					TwosideKeeper.log("Setting breaks remaining to "+(break_count-1),3);
				}
			}
			m.setLore(lore);
			item.setItemMeta(m);
			item.setAmount(1);
			item.setDurability((short)0);
			TwosideKeeper.log("New item is "+item.toString(),4);
			return item;
			//By setting the amount to 1, you refresh the item in the player's inventory.
		} else {
			//This item is technically destroyed.
			return new ItemStack(Material.AIR);
		}
	}

	public static String UserFriendlyMaterialName(ItemStack type) {
		switch (type.getType()) {
			case ACACIA_DOOR_ITEM:{
				return "Acacia Door";
			}
			case JUNGLE_DOOR_ITEM:{
				return "Jungle Door";
			}
			case BIRCH_DOOR_ITEM:{
				return "Birch Door";
			}
			case DARK_OAK_DOOR_ITEM:{
				return "Dark Oak Door";
			}
			case SPRUCE_DOOR_ITEM:{
				return "Spruce Door";
			}
			case WOOD_DOOR:{
				return "Wooden Door";
			}
			case BED_BLOCK:{
				return "Bed";
			}
			case BOAT_ACACIA:{
				return "Acacia Boat";
			}
			case BOAT_BIRCH:{
				return "Birch Boat";
			}
			case BOAT_DARK_OAK:{
				return "Dark Oak Boat";
			}
			case BOAT_JUNGLE:{
				return "Jungle Boat";
			}
			case BOAT_SPRUCE:{
				return "Spruce Boat";
			}
			case BREWING_STAND_ITEM:{
				return "Brewing Stand";
			}
			case BURNING_FURNACE:{
				return "Furnace";
			}
			case CAKE_BLOCK:{
				return "Cake";
			}
			case CARROT_ITEM:{
				return "Carrot";
			}
			case CARROT_STICK:{
				return "Carrot on a Stick";
			}
			case CAULDRON_ITEM:{
				return "Cauldron";
			}
			case CHORUS_FRUIT_POPPED:{
				return "Popped Chorus Fruit";
			}
			case CLAY_BALL:{
				return "Clay";
			}
			case COBBLE_WALL:{
				return "Cobblestone Wall";
			}
			case COMMAND:{
				return "Command Block";
			}
			case COMMAND_CHAIN:{
				return "Chain Command Block";
			}
			case COMMAND_MINECART:{
				return "Minecart w/Command Block";
			}
			case COMMAND_REPEATING:{
				return "Repeating Command Block";
			}
			case CROPS:{
				return "Sugar Cane";
			}
			case DAYLIGHT_DETECTOR_INVERTED:{
				return "Daylight Detector";
			}
			case WOOD_SPADE:{
				return "Wooden Shovel";
			}
			case STONE_SPADE:{
				return "Stone Shovel";
			}
			case IRON_SPADE:{
				return "Iron Shovel";
			}
			case GOLD_SPADE:{
				return "Gold Shovel";
			}
			case DIAMOND_SPADE:{
				return "Diamond Shovel";
			}
			case IRON_BARDING:{
				return "Iron Horse Armor";
			}
			case GOLD_BARDING:{
				return "Gold Horse Armor";
			}
			case DIAMOND_BARDING:{
				return "Diamond Horse Armor";
			}
			case DIODE:{
				return "Redstone Repeater";
			}
			case DIODE_BLOCK_OFF:{
				return "Redstone Repeater";
			}
			case DIODE_BLOCK_ON:{
				return "Diamond Horse Armor";
			}
			case DRAGONS_BREATH:{
				return "Dragon's Breath";
			}
			case END_CRYSTAL:{
				return "Ender Crystal";
			}
			case ENDER_STONE:{
				return "End Stone";
			}
			case EXPLOSIVE_MINECART:{
				return "TNT Minecart";
			}
			case FLOWER_POT_ITEM:{
				return "Flower Pot";
			}
			case GLOWING_REDSTONE_ORE:{
				return "Redstone Ore";
			}
			case GRILLED_PORK:{
				return "Cooked Porkchop";
			}
			case HUGE_MUSHROOM_1:{
				return "Brown Mushroom";
			}
			case HUGE_MUSHROOM_2:{
				return "Red Mushroom";
			}
			case JACK_O_LANTERN:{
				return "Jack o'Lantern";
			}
			case LEAVES:{
				switch (type.getDurability()) {
					case 0:{
						return "Oak Leaves";
					}
					case 1:{
						return "Spruce Leaves";
					}
					case 2:{
						return "Birch Leaves";
					}
					case 3:{
						return "Jungle Leaves";
					}
				}
			}
			case LEAVES_2:{
				switch (type.getDurability()) {
					case 0:{
						return "Acacia Leaves";
					}
					case 1:{
						return "Dark Oak Leaves";
					}
				}
			}
			case LOG:{
				switch (type.getDurability()) {
					case 0:{
						return "Oak Wood";
					}
					case 1:{
						return "Spruce Wood";
					}
					case 2:{
						return "Birch Wood";
					}
					case 3:{
						return "Jungle Wood";
					}
				}
			}
			case LOG_2:{
				switch (type.getDurability()) {
					case 0:{
						return "Acacia Wood";
					}
					case 1:{
						return "Dark Oak Wood";
					}
				}
			}
			case WOOD:{
				switch (type.getDurability()) {
					case 0:{
						return "Oak Wood Planks";
					}
					case 1:{
						return "Spruce Wood Planks";
					}
					case 2:{
						return "Birch Wood Planks";
					}
					case 3:{
						return "Jungle Wood Planks";
					}
					case 4:{
						return "Acacia Wood Planks";
					}
					case 5:{
						return "Dark Oak Wood Planks";
					}
				}
			}
			case MILK_BUCKET:{
				return "Milk";
			}
			case NETHER_BRICK_ITEM:{
				return "Nether Bricks";
			}
			case NETHER_WARTS:{
				return "Nether Wart";
			}
			case PISTON_BASE:{
				return "Piston";
			}
			case PISTON_STICKY_BASE:{
				return "Sticky Piston";
			}
			case PORK:{
				return "Raw Porkchop";
			}
			case POTATO_ITEM:{
				return "Potato";
			}
			case POWERED_MINECART:{
				return "Minecart w/Furnace";
			}
			case RABBIT:{
				return "Raw Rabbit";
			}
			case RABBIT_FOOT:{
				return "Rabbit's Foot";
			}
			case RECORD_10:{
				return "Music Disc";
			}
			case RECORD_11:{
				return "Music Disc";
			}
			case RECORD_12:{
				return "Music Disc";
			}
			case RECORD_3:{
				return "Music Disc";
			}
			case RECORD_4:{
				return "Music Disc";
			}
			case RECORD_5:{
				return "Music Disc";
			}
			case RECORD_6:{
				return "Music Disc";
			}
			case RECORD_7:{
				return "Music Disc";
			}
			case RECORD_8:{
				return "Music Disc";
			}
			case RECORD_9:{
				return "Music Disc";
			}
			case REDSTONE_COMPARATOR:{
				return "Comparator";
			}
			case REDSTONE_COMPARATOR_OFF:{
				return "Comparator";
			}
			case REDSTONE_COMPARATOR_ON:{
				return "Comparator";
			}
			case REDSTONE_LAMP_OFF:{
				return "Redstone Lamp";
			}
			case REDSTONE_LAMP_ON:{
				return "Redstone Lamp";
			}
			case REDSTONE_TORCH_OFF:{
				return "Redstone Torch";
			}
			case REDSTONE_TORCH_ON:{
				return "Redstone Torch";
			}
			case REDSTONE_WIRE:{
				return "Redstone";
			}
			case SAPLING:{
				switch (type.getDurability()) {
					case 0:{
						return "Oak Sapling";
					}
					case 1:{
						return "Spruce Sapling";
					}
					case 2:{
						return "Birch Sapling";
					}
					case 3:{
						return "Jungle Sapling";
					}
					case 4:{
						return "Acacia Sapling";
					}
					case 5:{
						return "Dark Oak Sapling";
					}
				}
			}
			case SIGN_POST:{
				return "Sign";
			}
			case SKULL_ITEM:{
				return "Skull";
			}
			case SMOOTH_BRICK:{
				return "Stone Brick";
			}
			case SMOOTH_STAIRS:{
				return "Stone Brick Stairs";
			}
			case STEP:{
				switch (type.getDurability()) {
					case 0:{
						return "Stone Slab";
					}
					case 1:{
						return "Sandstone Slab";
					}
					case 2:{
						return "(Stone)Wooden Slab";
					}
					case 3:{
						return "Cobblestone Slab";
					}
					case 4:{
						return "Bricks Slab";
					}
					case 5:{
						return "Stone Brick Slab";
					}
					case 6:{
						return "Nether Brick Slab";
					}
					case 7:{
						return "Quartz Slab";
					}
				}
			}
			case SULPHUR:{
				return "Gunpowder";
			}
			case POTION:{
				PotionMeta pm = (PotionMeta)type.getItemMeta();
				/*
				return "Lingering Potion of "+CapitalizeFirstLetters(pm.getBasePotionData().getType().toString().toLowerCase());
				*/
				switch (pm.getBasePotionData().getType()) {
					case AWKWARD:
						return "Awkward Potion";
					case FIRE_RESISTANCE:
						return "Potion of Fire Resistance";
					case INSTANT_DAMAGE:
							if (pm.getBasePotionData().isUpgraded()) {
								return "Potion of Harming II";
							} else {
								return "Potion of Harming";
							}
					case INSTANT_HEAL:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Potion of Instant Health II";
						} else {
							return "Potion of Instant Health";
						}
					case INVISIBILITY:
						return "Potion of Invisibility";
					case JUMP:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Potion of Jump Boost II";
						} else {
							return "Potion of Jump Boost";
						}
					case LUCK:
						return "Potion of Luck";
					case MUNDANE:
						return "Mundane Potion";
					case NIGHT_VISION:
						return "Potion of Night Vision";
					case POISON:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Potion of Poison II";
						} else {
							return "Potion of Poison";
						}
					case REGEN:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Potion of Regeneration II";
						} else {
							return "Potion of Regeneration";
						}
					case SLOWNESS:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Potion of Slowness II";
						} else {
							return "Potion of Slowness";
						}
					case SPEED:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Potion of Speed II";
						} else {
							return "Potion of Speed";
						}
					case STRENGTH:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Potion of Strength II";
						} else {
							return "Potion of Strength";
						}
					case THICK:
						return "Thick Potion";
					case UNCRAFTABLE:
						return "Potion";
					case WATER:
						return "Water Bottle";
					case WATER_BREATHING:
						return "Potion of Water Breathing";
					case WEAKNESS:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Potion of Weakness II";
						} else {
							return "Potion of Weakness";
						}
					default:
						return "Potion";
				}
			}
			case SPLASH_POTION:{
				PotionMeta pm = (PotionMeta)type.getItemMeta();
				/*
				return "Lingering Potion of "+CapitalizeFirstLetters(pm.getBasePotionData().getType().toString().toLowerCase());
				*/
				switch (pm.getBasePotionData().getType()) {
					case AWKWARD:
						return "Awkward Splash Potion";
					case FIRE_RESISTANCE:
						return "Splash Potion of Fire Resistance";
					case INSTANT_DAMAGE:
							if (pm.getBasePotionData().isUpgraded()) {
								return "Splash Potion of Harming II";
							} else {
								return "Splash Potion of Harming";
							}
					case INSTANT_HEAL:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Splash Potion of Instant Health II";
						} else {
							return "Splash Potion of Instant Health";
						}
					case INVISIBILITY:
						return "Splash Potion of Invisibility";
					case JUMP:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Splash Potion of Jump Boost II";
						} else {
							return "Splash Potion of Jump Boost";
						}
					case LUCK:
						return "Splash Potion of Luck";
					case MUNDANE:
						return "Mundane Splash Potion";
					case NIGHT_VISION:
						return "Splash Potion of Night Vision";
					case POISON:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Splash Potion of Poison II";
						} else {
							return "Splash Potion of Poison";
						}
					case REGEN:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Splash Potion of Regeneration II";
						} else {
							return "Splash Potion of Regeneration";
						}
					case SLOWNESS:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Splash Potion of Slowness II";
						} else {
							return "Splash Potion of Slowness";
						}
					case SPEED:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Splash Potion of Speed II";
						} else {
							return "Splash Potion of Speed";
						}
					case STRENGTH:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Splash Potion of Strength II";
						} else {
							return "Splash Potion of Strength";
						}
					case THICK:
						return "Thick Splash Potion";
					case UNCRAFTABLE:
						return "Splash Potion";
					case WATER:
						return "Water Bottle";
					case WATER_BREATHING:
						return "Splash Potion of Water Breathing";
					case WEAKNESS:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Splash Potion of Weakness II";
						} else {
							return "Splash Potion of Weakness";
						}
					default:
						return "Splash Potion";
				}
			}
			case TIPPED_ARROW:{
				PotionMeta pm = (PotionMeta)type.getItemMeta();
				/*
				return "Lingering Potion of "+CapitalizeFirstLetters(pm.getBasePotionData().getType().toString().toLowerCase());
				*/
				switch (pm.getBasePotionData().getType()) {
					case AWKWARD:
						return "Awkward Arrow";
					case FIRE_RESISTANCE:
						return "Arrow of Fire Resistance";
					case INSTANT_DAMAGE:
							if (pm.getBasePotionData().isUpgraded()) {
								return "Arrow of Harming II";
							} else {
								return "Arrow of Harming";
							}
					case INSTANT_HEAL:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Arrow of Instant Health II";
						} else {
							return "Arrow of Instant Health";
						}
					case INVISIBILITY:
						return "Arrow of Invisibility";
					case JUMP:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Arrow of Jump Boost II";
						} else {
							return "Arrow of Jump Boost";
						}
					case LUCK:
						return "Arrow of Luck";
					case MUNDANE:
						return "Mundane Arrow";
					case NIGHT_VISION:
						return "Arrow of Night Vision";
					case POISON:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Arrow of Poison II";
						} else {
							return "Arrow of Poison";
						}
					case REGEN:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Arrow of Regeneration II";
						} else {
							return "Arrow of Regeneration";
						}
					case SLOWNESS:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Arrow of Slowness II";
						} else {
							return "Arrow of Slowness";
						}
					case SPEED:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Arrow of Speed II";
						} else {
							return "Arrow of Speed";
						}
					case STRENGTH:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Arrow of Strength II";
						} else {
							return "Arrow of Strength";
						}
					case THICK:
						return "Thick Arrow";
					case UNCRAFTABLE:
						return "Arrow";
					case WATER:
						return "Water Bottle";
					case WATER_BREATHING:
						return "Arrow of Water Breathing";
					case WEAKNESS:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Arrow of Weakness II";
						} else {
							return "Arrow of Weakness";
						}
					default:
						return "Arrow";
				}
			}
			case LINGERING_POTION:{
				PotionMeta pm = (PotionMeta)type.getItemMeta();
				/*
				return "Lingering Potion of "+CapitalizeFirstLetters(pm.getBasePotionData().getType().toString().toLowerCase());
				*/
				switch (pm.getBasePotionData().getType()) {
					case AWKWARD:
						return "Awkward Lingering Potion";
					case FIRE_RESISTANCE:
						return "Lingering Potion of Fire Resistance";
					case INSTANT_DAMAGE:
							if (pm.getBasePotionData().isUpgraded()) {
								return "Lingering Potion of Harming II";
							} else {
								return "Lingering Potion of Harming";
							}
					case INSTANT_HEAL:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Lingering Potion of Instant Health II";
						} else {
							return "Lingering Potion of Instant Health";
						}
					case INVISIBILITY:
						return "Lingering Potion of Invisibility";
					case JUMP:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Lingering Potion of Jump Boost II";
						} else {
							return "Lingering Potion of Jump Boost";
						}
					case LUCK:
						return "Lingering Potion of Luck";
					case MUNDANE:
						return "Mundane Lingering Potion";
					case NIGHT_VISION:
						return "Lingering Potion of Night Vision";
					case POISON:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Lingering Potion of Poison II";
						} else {
							return "Lingering Potion of Poison";
						}
					case REGEN:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Lingering Potion of Regeneration II";
						} else {
							return "Lingering Potion of Regeneration";
						}
					case SLOWNESS:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Lingering Potion of Slowness II";
						} else {
							return "Lingering Potion of Slowness";
						}
					case SPEED:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Lingering Potion of Speed II";
						} else {
							return "Lingering Potion of Speed";
						}
					case STRENGTH:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Lingering Potion of Strength II";
						} else {
							return "Lingering Potion of Strength";
						}
					case THICK:
						return "Thick Lingering Potion";
					case UNCRAFTABLE:
						return "Lingering Potion";
					case WATER:
						return "Lingering Water Bottle";
					case WATER_BREATHING:
						return "Lingering Potion of Water Breathing";
					case WEAKNESS:
						if (pm.getBasePotionData().isUpgraded()) {
							return "Lingering Potion of Weakness II";
						} else {
							return "Lingering Potion of Weakness";
						}
					default:
						return "Lingering Potion";
				}
			}
			case WOOD_STEP:{
				switch (type.getDurability()) {
					case 0:{
						return "Oak Wood Slab";
					}
					case 1:{
						return "Spruce Wood Slab";
					}
					case 2:{
						return "Birch Wood Slab";
					}
					case 3:{
						return "Jungle Wood Slab";
					}
					case 4:{
						return "Acacia Wood Slab";
					}
					case 5:{
						return "Dark Oak Wood Slab";
					}
				}
			}
			case SAND:{
				switch (type.getDurability()) {
					case 0:{
						return "Sand";
					}
					case 1:{
						return "Red Sand";
					}
				}
			}
			case INK_SACK:{
				switch (type.getDurability()) {
					case 0:{
						return "Ink Sac";
					}
					case 1:{
						return "Rose Red";
					}
					case 2:{
						return "Cactus Green";
					}
					case 3:{
						return "Cocoa Beans";
					}
					case 4:{
						return "Lapis Lazuli";
					}
					case 5:{
						return "Purple Dye";
					}
					case 6:{
						return "Cyan Dye";
					}
					case 7:{
						return "Light Gray Dye";
					}
					case 8:{
						return "Gray Dye";
					}
					case 9:{
						return "Pink Dye";
					}
					case 10:{
						return "Lime Dye";
					}
					case 11:{
						return "Dandelion Yellow";
					}
					case 12:{
						return "Light Blue Dye";
					}
					case 13:{
						return "Magenta Dye";
					}
					case 14:{
						return "Orange Dye";
					}
					case 15:{
						return "Bone Meal";
					}
				}
			}
			case HARD_CLAY:{
				return "Hardened Clay";
			}
			case STAINED_CLAY:{
				switch (type.getDurability()) {
					case 0:{
						return "White Stained Clay";
					}
					case 1:{
						return "Orange Stained Clay";
					}
					case 2:{
						return "Magenta Stained Clay";
					}
					case 3:{
						return "Light Blue Stained Clay";
					}
					case 4:{
						return "Yellow Stained Clay";
					}
					case 5:{
						return "Lime Stained Clay";
					}
					case 6:{
						return "Pink Stained Clay";
					}
					case 7:{
						return "Gray Stained Clay";
					}
					case 8:{
						return "Light Gray Stained Clay";
					}
					case 9:{
						return "Cyan Stained Clay";
					}
					case 10:{
						return "Purple Stained Clay";
					}
					case 11:{
						return "Blue Stained Clay";
					}
					case 12:{
						return "Brown Stained Clay";
					}
					case 13:{
						return "Green Stained Clay";
					}
					case 14:{
						return "Red Stained Clay";
					}
					case 15:{
						return "Black Stained Clay";
					}
				}
			}
			case WOOL:{
				switch (type.getDurability()) {
					case 0:{
						return "White Wool";
					}
					case 1:{
						return "Orange Wool";
					}
					case 2:{
						return "Magenta Wool";
					}
					case 3:{
						return "Light Blue Wool";
					}
					case 4:{
						return "Yellow Wool";
					}
					case 5:{
						return "Lime Wool";
					}
					case 6:{
						return "Pink Wool";
					}
					case 7:{
						return "Gray Wool";
					}
					case 8:{
						return "Light Gray Wool";
					}
					case 9:{
						return "Cyan Wool";
					}
					case 10:{
						return "Purple Wool";
					}
					case 11:{
						return "Blue Wool";
					}
					case 12:{
						return "Brown Wool";
					}
					case 13:{
						return "Green Wool";
					}
					case 14:{
						return "Red Wool";
					}
					case 15:{
						return "Black Wool";
					}
				}
			}
			default:{
				return GenericFunctions.CapitalizeFirstLetters(type.getType().toString().replace("_", " "));
			}
		}
	}

	public static String CapitalizeFirstLetters(String s) {
		if (s.contains(" ")) {
			String[] temp = s.split(" ");
			String finalname = "";
			for (int i=0;i<temp.length;i++) {
				char first;
				if (temp[i].charAt(0)>='a') {
					first = (char)(temp[i].charAt(0)-32);
				} else {
					first = temp[i].charAt(0);
				}
				finalname+=(finalname.equals("")?"":" ")+first+temp[i].toLowerCase().substring(1);
			}
			return finalname;
		} else {
			if (s.charAt(0)>='a') {
				char first = (char)(s.charAt(0)-32);
				return first+s.toLowerCase().substring(1);
			} else {
				char first = (char)(s.charAt(0));
				return first+s.toLowerCase().substring(1);
			}
		}
	}

	public static String GetItemName(ItemStack item) {
		if (item.hasItemMeta() &&
				item.getItemMeta().hasDisplayName()) {
			return item.getItemMeta().getDisplayName();
		} else {
			return UserFriendlyMaterialName(item);
		}
	}

	/**
	 * This function will return the number of items of this type
	 * that exist in your inventory. It will not include your
	 * equipment.
	 * @param p
	 * @param item
	 * @return
	 */
	public static int CountItems(Player p, ItemStack item) {
		int totalcount=0;
		for (int i=0;i<p.getInventory().getSize();i++) {
			if (p.getInventory().getItem(i)!=null &&
					p.getInventory().getItem(i).isSimilar(item)) {
				totalcount+=p.getInventory().getItem(i).getAmount();
			}
		}
		return totalcount;
	}

	public static ItemStack convertToHardenedPiece(ItemStack item, int breaks) {
		if (item!=null && item.hasItemMeta()) {
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.BLUE+"Hardened "+UserFriendlyMaterialName(item));
			List<String> lore = new ArrayList<String>();
			if (meta.hasLore()) {
				lore.addAll(meta.getLore());
			}
			if (GenericFunctions.isArmor(item)) {
				lore.add(ChatColor.BLUE+""+ChatColor.ITALIC+"Hardened Armor");
				lore.add(ChatColor.GRAY+"Twice as strong");
			} else
			if (GenericFunctions.isTool(item)) {
				lore.add(ChatColor.BLUE+""+ChatColor.ITALIC+"Hardened Weapon");
				lore.add(ChatColor.GRAY+"Twice as strong");
			}
			lore.add(ChatColor.GRAY+"Breaks Remaining: "+ChatColor.YELLOW+breaks);
			meta.setLore(lore);
			item.setItemMeta(meta);
		}
		return item;
	}

	public static boolean isHardenedItem(ItemStack item) {
		if (item.hasItemMeta() &&
				item.getItemMeta().hasLore()) {
			for (int i=0;i<item.getItemMeta().getLore().size();i++) {
				if (item.getItemMeta().getLore().get(i).contains(ChatColor.GRAY+"Breaks Remaining:")) {
					return true;
				}
			}
			return false; //Nothing found. Return false.
		} else {
			return false;
		}
	}

	public static boolean isTool(ItemStack item) {
		if (item.getType().toString().contains("SPADE") ||
			item.getType().toString().contains("AXE") ||
			item.getType().toString().contains("SWORD") ||
			item.getType().toString().contains("HOE")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isArmor(ItemStack item) {
		if (item.getType().toString().contains("BOOTS") ||
			item.getType().toString().contains("CHESTPLATE") ||
			item.getType().toString().contains("LEGGINGS") ||
			item.getType().toString().contains("HELMET")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isDefender(LivingEntity p) {
		if (p.getEquipment().getItemInMainHand()!=null && p.getEquipment().getItemInMainHand().getType()==Material.SHIELD) {
			return true;
		} else {
			return false;
		}
	}

}

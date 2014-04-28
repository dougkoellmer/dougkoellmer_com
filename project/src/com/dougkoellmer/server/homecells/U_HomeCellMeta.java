package com.dougkoellmer.server.homecells;

import swarm.shared.structs.CellSize;

import com.dougkoellmer.shared.homecells.E_HomeCell;
import com.dougkoellmer.shared.homecells.S_HomeCell;

//--- DRK > Doing this rather inefficiently with giant switch cases (assuming java doesn't
//---		optimize them and they're just if/else chains under the hood) in the interest
//---		of keeping everything readable and easily adjustable relative to other home cell data.
public class U_HomeCellMeta
{
	public static String getDescription(E_HomeCell homeCell)
	{
		switch(homeCell)
		{
			case HOME:							return "";
			
			case PRECIOUSES:					return "";
			case SWORD:							return 	"Ceremonial sword from<br>Papua New Guinea";
			case WOODEN_CUP:					return 	"Cup machined from<br>white oak sapling";
			case HATCHET:						return 	"Multipurpose hatchet<br>handcrafted in Sweden";
			case MITER_SAW:						return 	"Precision hand miter<br>saw made in Sweden";
			case CARVING_TOOLS:					return 	"Wood carving tools<br>made in Japan";
			case VACUUM_SEALER:					return 	"Vacuum sealer<br>for mason jars";
			case LIGHTER:						return 	"Waterproof wick-style<br>survival lighter";
				
			case ABILITIES:						return "";
			case FOOT_JUGGLING:					return 	"Juggling tennis<br>ball with only feet";
			case JUGGLING:						return 	"Juggling three tennis<br>balls, various tricks";
			case FAST_SHUFFLE:					return 	"Shuffling cards as<br>fast as possible";
			case CARD_DECK_SPLIT:				return 	"Splitting deck of<br>cards with one hand";
			case CARD_THROW:					return 	"Throwing playing cards<br>into and through things";
			case PEN_TWIRL:						return 	"Twirling pen<br>through four fingers";
			case BOTTLE_OPENING:				return 	"Opening beer bottles<br>with everyday items";
			case FAST_SHOE_TIE:					return 	"Tying shoelaces as<br>fast as possible";
			case ONE_HAND_MATCH_LIGHT:			return 	"Lighting match from<br>matchbook one-handed";
			case FAST_SHIRT_SWAP:				return 	"T-shirt on/off as<br>fast as possible";
				
			case SOFTWARE:						return "";
			case FOR_COMPUTERS:					return "";
			case PRESSURE:						return "Air pressure simulation<br>& molecular movement";
			case PRESSURE_AND_HEAT:				return "Air pressure simulation<br>with heat effects";
			case RADIOACTIVE_DECAY:				return "Radioactive decay and<br>half-life simulation";
			case CORROSION_CELL:				return "Metal corrosion &<br>electricity simulation";
			case FEED_THE_BEAR:					return "Game with polar bear<br>hunting like crocodile";
			case SWARM:							return "Open-source HTML5<br>content engine";
			case B33HIVE:						return "Open-source HTML5<br>application platform";
			case QUICKPHYX:						return "Open-source physics<br>engine for games";
			case CORROSION_GAME:				return "Training module for the<br>Department of Defense";
			case GLUE:							return "2d heightfield-based<br>viscous fluid simulation";
			case WHIP_THE_RAGDOLL:				return "Evil stress reliever<br>with ragdoll beatdown";
			case TOUCH_TONE_HERO:				return "Rhythm-based game<br>using touch-tone keypad";
			case BALANCE_THING_GAME:			return "Physics game where you<br>balance crazy objects";
			case FIREWORKS:						return "Fireworks for end-game<br>celebration screen";
			case GRAVITATION:					return "Physics simulation<br>of planetary gravity";
					
			case FOR_BIOTICS:					return "";
			case RESUME:						return "Experimenting with new<br>kind of resume format";
			case PORTFOLIO:						return "Programmer-friendly<br>work portfolio";
			case POLISH_FOREST_ADVENTURE:		return "Disturbing walk in cold<br>misty forest in Poland";
			case SPANISH_OPERA_ADVENTURE:		return "Fantastical experience<br>travelling in Spain";
			case OLD_FRIEND:					return "Poem about passing old<br>friend from childhood";
			case MUSINGS:						return "Random musings on life<br>and nature of reality";
			case WHAT_IS_CORROSION:				return "Essay for the layman on<br>how corrosion works";
			case PERFECT_COFFEE:				return "How to make the<br>perfect cup of coffee";
			
			case CREATIONS:						return "";
			case USEFUL:						return "";
			case INVENTIONS:					return "";
			case BIKE_CARD_THING:				return "Polite alternative to<br>annoying bicycle bells";
			case DOWEL_HOLDER:					return "Easy way to store long<br>thin pieces of material";
			case OUTDOOR_TOOL_HOLDER:			return "Easy way to store<br>assorted outdoor tools";
			case GUITAR_CASE:					return "Guitar case that can<br>survive nuclear bombs";
			case FLASHLIGHT_LAMP:				return "Converts flashlight<br>into diffuse lamp";
			case SLING_SHOT:					return "Primitive take on<br>wrist-braced slingshots";
			case BREAKAWAY_KNOT:				return "Lanyard knot that unties<br>upon abrupt force";
			case SANDER_HOLDER:					return "Holder for running<br>random orbital sander";
			case RASP_HANDLE:					return "Easy wood handle<br>for rat-tailed tools";
			case HANGING_CRATES:				return "Hanging garage storage<br>using milk crates";
			case HEATING_BAG:					return "Really ghetto<br>heating pack";
			case SAND_WEIGHT:					return "Cheap way to get<br>exact amount of mass";
			case SHIRT_PILLOW_CASE:				return "Emergency pillow case<br>for bachelors only";
			case TORSION_KNOT:					return "Knot that gets crazy<br>tight using torsion";
					
			case SUNDRY:						return "";
			case TERMINATOR_GLASSES:			return "Glasses that make you<br>look like Terminator";
			case CANVAS_BAG:					return "Hand-sewn canvas<br>draw-string pouch";
			case CANVAS_WRAP:					return "Hand-sewn canvas<br>needle file holder";
			case SANTOKU_SHEATH:				return "Sturdy leather sheath<br>for santoku knife";
			case F1_SHEATH_MOD:					return "Modification of F1<br>survival knife sheath";
			case SAW_SHEATH:					return "Magnetic sheath for<br>rip and crosscut saws";
			case BROOM:							return "Stiff-bristle broom for<br>cleaning wood shop";
			case FIRE_POI:						return "Fire poi with kevlar<br>monkey-fist knots";
			case JUGGLING_TORCHES:				return "Juggling torches made<br>from various pipes";
			case THROWING_KNIVES:				return "Throwing knives made<br>from steel blanks";
			case STONE_CUP:						return "Caveman cup hewn from<br>block of solid granite";
			case FIRE_POKER:					return "Oaken-handled fire<br>poker with steel dowel";
						
			case WOOD:							return "";
			case TILLER_TREE:					return "Tiller tree to assist<br>in making wooden bows";
			case THROWING_KNIFE_TARGET:			return "Portable target for<br>throwing knife practice";
			case WOOD_MALLET:					return "Big hickory mallet<br>for rough woodworking";
			case COASTERS:						return "Coasters made from<br>poplar cross sections";
			case KITCHEN_UTENSILS:				return "Set of cooking utensils<br>made from white oak";
			case SHAKERS:						return "Set of salt and pepper<br>shakers from white oak";
			case BIKE_RACK:						return "Mounted bike rack<br>made from white oak";
			case BACKSCRATCHER_1:				return "Graceful backscratcher<br>made from sugar maple";
			case BACKSCRATCHER_2:				return "Manly backscratcher<br>made from red oak";
			case CUTTING_BOARD_STAND:			return "Cutting board stand<br>using no metal fasteners";
			case CUTTING_BOARD:					return "Massive cutting board<br>made from rock maple";
			case BOW_1:							return "Recurved maple flat bow<br>with canvas backing";
			case BOW_2:							return "Red oak long bow<br>with canvas backing";
			case DOORSTOP:						return "Doorstop made from<br>length of poplar sapling";
			case DOGGIE_RAMP:					return "Ramp for little doggies<br>to get to high places";
			case OAR:							return "Oar carved from<br>solid log of hickory";
			case BOOMERANG:						return "Boomerang made from<br>strips of various woods";
			case COASTER_HOLDER:				return "Decorative holder for<br>circular drink coasters";
			case RED_OAK_SPOON:					return "Head-thumping spoon<br>made from red oak";
			case PLYWOOD_PUNISHER:				return "3-person sled made<br>of steam-bent plywood";
						
			case ART:							return "";
			case ROSE:							return "Realistic rose drawn<br>with colored pencils";
			case DRAGON:						return "Muscular dragon cast<br>from liquid plastic";
			case SOLAR_SYSTEM:					return "Alien solar system from<br>acid-etched zinc plate";
			case BANANA:						return "Realistic banana<br>drawn with pencil";
			case PEACEFUL_MAN:					return "Peaceful man & flowers<br>sculpted with clay";
			case LADY:							return "Mysterious lady painted<br>with limited colors";
			case BLACKHOLE:						return "Linoleum print block<br>of singularity";
			case INFINITE_TOLERANCE:			return "Sketch supporting<br>diversity & tolerance";
			case SAVE_ANIMALS:					return "Sketch supporting <br>all the animals";
			case DINOSAURS:						return "Sketch supporting<br>dinosaurs taking over";
			case QUICKB2_LOGO:					return "Logo for open<br>source physics engine";
			case B33HIVE_LOGO:					return "Logo & icons for open<br>source content engine";
			case MILKMAN:						return "Basswood carving of<br>1950s-era milkman";
			case MOTHER_AND_CHILD:				return "Wooden sculpture of<br>mother & child";
			case WOOD_CHAIN:					return "Chain carved from <br>solid rock maple";
			case GLASSES_HOLDER:				return "Wooden desktop holder<br>for reading glasses";
		}
		
		return null;
	}
	
	public static I_HomeCellContent getContent(E_HomeCell cell)
	{
		switch(cell)
		{
			case HOME:							return new FileBasedHomeCellContent("home");
			
			case PRECIOUSES:					return new ThumbnailHomeCellContent();
			case SWORD:							return new CellComingSoonContent();
			case WOODEN_CUP:					return new CellComingSoonContent();
			case HATCHET:						return new CellComingSoonContent();
			case MITER_SAW:						return new CellComingSoonContent();
			case CARVING_TOOLS:					return new CellComingSoonContent();
			case VACUUM_SEALER:					return new CellComingSoonContent();
			case LIGHTER:						return new CellComingSoonContent();
				
			case ABILITIES:						return new ThumbnailHomeCellContent();
			case FOOT_JUGGLING:					return new CellComingSoonContent();
			case JUGGLING:						return new CellComingSoonContent();
			case FAST_SHUFFLE:					return new CellComingSoonContent();
			case CARD_DECK_SPLIT:				return new CellComingSoonContent();
			case CARD_THROW:					return new CellComingSoonContent();
			case PEN_TWIRL:						return new CellComingSoonContent();
			case BOTTLE_OPENING:				return new CellComingSoonContent();
			case FAST_SHOE_TIE:					return new CellComingSoonContent();
			case ONE_HAND_MATCH_LIGHT:			return new CellComingSoonContent();
			case FAST_SHIRT_SWAP:				return new CellComingSoonContent();
				
			case SOFTWARE:						return new FileBasedHomeCellContent("software");
			case FOR_COMPUTERS:					return new ThumbnailHomeCellContent();
			case PRESSURE:						return new IFrameContent(cell, "/games/pressure", true, "center");
			case PRESSURE_AND_HEAT:				return new IFrameContent(cell, "/games/pressure_and_heat", true, "center");
			case RADIOACTIVE_DECAY:				return new IFrameContent(cell, "/games/radioactive_decay", true, "center");
			case CORROSION_CELL:				return new CellComingSoonContent();
			case FEED_THE_BEAR:					return new IFrameContent(cell, "/games/feed_the_bear", false, "right");
			case SWARM:							return new CellComingSoonContent();
			case B33HIVE:						return new CellComingSoonContent();
			case QUICKPHYX:						return new CellComingSoonContent();
			case CORROSION_GAME:				return new CellComingSoonContent();
			case GLUE:							return new CellComingSoonContent();
			case WHIP_THE_RAGDOLL:				return new CellComingSoonContent();
			case TOUCH_TONE_HERO:				return new CellComingSoonContent();
			case BALANCE_THING_GAME:			return new CellComingSoonContent();
			case FIREWORKS:						return new CellComingSoonContent();
			case GRAVITATION:					return new CellComingSoonContent();
	
			case FOR_BIOTICS:					return new ThumbnailHomeCellContent();
			case RESUME:						return new ResumeContent();
			case PORTFOLIO:						return new PortfolioContent();
			case POLISH_FOREST_ADVENTURE:		return new CellComingSoonContent();
			case SPANISH_OPERA_ADVENTURE:		return new CellComingSoonContent();
			case OLD_FRIEND:					return new CellComingSoonContent();
			case MUSINGS:						return new CellComingSoonContent();
			case WHAT_IS_CORROSION:				return new CellComingSoonContent();
			case PERFECT_COFFEE:				return new CellComingSoonContent();
			
			case CREATIONS:						return new FileBasedHomeCellContent("creations");
			case USEFUL:						return new FileBasedHomeCellContent("useful");
			case INVENTIONS:					return new ThumbnailHomeCellContent();
			case BIKE_CARD_THING:				return new CellComingSoonContent();
			case DOWEL_HOLDER:					return new CellComingSoonContent();
			case OUTDOOR_TOOL_HOLDER:			return new CellComingSoonContent();
			case GUITAR_CASE:					return new CellComingSoonContent();
			case FLASHLIGHT_LAMP:				return new StripContent(cell);
			case SLING_SHOT:					return new CellComingSoonContent();
			case BREAKAWAY_KNOT:				return new CellComingSoonContent();
			case SANDER_HOLDER:					return new CellComingSoonContent();
			case RASP_HANDLE:					return new CellComingSoonContent();
			case HANGING_CRATES:				return new CellComingSoonContent();
			case HEATING_BAG:					return new CellComingSoonContent();
			case SAND_WEIGHT:					return new CellComingSoonContent();
			case SHIRT_PILLOW_CASE:				return new CellComingSoonContent();
			case TORSION_KNOT:					return new CellComingSoonContent();
					
			case SUNDRY:						return new ThumbnailHomeCellContent();
			case TERMINATOR_GLASSES:			return new StripContent(cell);
			case CANVAS_BAG:					return new SingleImageContent(cell);
			case CANVAS_WRAP:					return new StripContent(cell);
			case SANTOKU_SHEATH:				return new CellComingSoonContent();
			case F1_SHEATH_MOD:					return new CellComingSoonContent();
			case SAW_SHEATH:					return new StripContent(cell);
			case BROOM:							return new CellComingSoonContent();
			case FIRE_POI:						return new StripContent(cell, "left");
			case JUGGLING_TORCHES:				return new SingleImageContent(cell);
			case THROWING_KNIVES:				return new CellComingSoonContent();
			case STONE_CUP:						return new CellComingSoonContent();
			case FIRE_POKER:					return new CellComingSoonContent();
						
			case WOOD:							return new ThumbnailHomeCellContent();
			case TILLER_TREE:					return new CellComingSoonContent();
			case THROWING_KNIFE_TARGET:			return new CellComingSoonContent();
			case WOOD_MALLET:					return new CellComingSoonContent();
			case COASTERS:						return new CellComingSoonContent();
			case KITCHEN_UTENSILS:				return new SingleImageContent(cell);
			case SHAKERS:						return new SingleImageContent(cell);
			case BIKE_RACK:						return new CellComingSoonContent();
			case BACKSCRATCHER_1:				return new StripContent(cell, "right");
			case BACKSCRATCHER_2:				return new SingleImageContent(cell);
			case CUTTING_BOARD_STAND:			return new CellComingSoonContent();
			case CUTTING_BOARD:					return new CellComingSoonContent();
			case BOW_1:							return new StripContent(cell, "bottom");
			case BOW_2:							return new CellComingSoonContent();
			case DOORSTOP:						return new CellComingSoonContent();
			case DOGGIE_RAMP:					return new CellComingSoonContent();
			case OAR:							return new CellComingSoonContent();
			case BOOMERANG:						return new CellComingSoonContent();
			case COASTER_HOLDER:				return new StripContent(cell);
			case RED_OAK_SPOON:					return new StripContent(cell);
			case PLYWOOD_PUNISHER:				return new CellComingSoonContent();
						
			case ART:							return new ThumbnailHomeCellContent();
			case ROSE:							return new SingleImageContent(cell, "left");
			case DRAGON:						return new StripContent(cell, "center");
			case SOLAR_SYSTEM:					return new SingleImageContent(cell, "center");
			case BANANA:						return new SingleImageContent(cell, "center");
			case PEACEFUL_MAN:					return new SingleImageContent(cell, "center");
			case LADY:							return new SingleImageContent(cell, "top");
			case BLACKHOLE:						return new SingleImageContent(cell, "center");
			case INFINITE_TOLERANCE:			return new SingleImageContent(cell, "center");
			case SAVE_ANIMALS:					return new SingleImageContent(cell, "center");
			case DINOSAURS:						return new SingleImageContent(cell, "center");
			case QUICKB2_LOGO:					return new SingleImageContent(cell, "center");
			case B33HIVE_LOGO:					return new SingleImageContent(cell, "center");
			case MILKMAN:						return new StripContent(cell, "center");
			case MOTHER_AND_CHILD:				return new CellComingSoonContent();
			case WOOD_CHAIN:					return new StripContent(cell, "center");
			case GLASSES_HOLDER:				return new StripContent(cell, "center");
			
			default:							return null;
		}
	}
	
	private static CellSize calcThumbCellSize(E_HomeCell cell)
	{
		int paddedChildCount = cell.getPaddedChildCount();
		int rowCount = paddedChildCount/2;
		int height = (int) Math.ceil(((double)rowCount)*S_HomeCell.THUMB_ROW_HEIGHT);
		height = Math.max(height, S_HomeCell.DEFAULT_CELL_SIZE);
		
		if( rowCount == 10 )
		{
			height += 1; // wow, ghetto
		}

		return new CellSize(CellSize.DEFAULT_DIMENSION, height);
	}
	
	static CellSize getFocusedCellSize(E_HomeCell cell)
	{
		CellSize cellSize = null;
		
		//--- DRK > Some manual overrides for autogenerated cell sizes in case
		//---		they don't work perfectly.
		switch(cell)
		{
			case BACKSCRATCHER_1:			cellSize = new CellSize(674, 1034);  break;
		}
		
		if( cellSize != null )
		{
			return cellSize;
		}
		
		cellSize = U_AutoGenHomeCellSize.getFocusedCellSize(cell);
		
		if( cellSize == null )
		{
			switch(cell)
			{
				case RESUME:					cellSize = new CellSize(882, 1139);  break;
				
				case PORTFOLIO:					cellSize = new CellSize(992, 2182);  break;
				
				case FEED_THE_BEAR:				cellSize = new CellSize(950, 650);  break;
				case PRESSURE:					cellSize = new CellSize(821, 612);  break;
				case PRESSURE_AND_HEAT:			cellSize = new CellSize(928, 755);  break;
				case RADIOACTIVE_DECAY:			cellSize = new CellSize(978, 615);  break;
				
				case PRECIOUSES: case ABILITIES: case FOR_COMPUTERS: case FOR_BIOTICS:
				case INVENTIONS: case SUNDRY: case WOOD: case ART:
										cellSize = calcThumbCellSize(cell);  break;
			}
		}
		
		if( cellSize == null )
		{
			cellSize = new CellSize(CellSize.DEFAULT_DIMENSION, CellSize.DEFAULT_DIMENSION);
		}
		
		if( cellSize.isPartiallyExplicit() )
		{
			cellSize.setToDefaultsIfMatches(S_HomeCell.DEFAULT_CELL_SIZE, S_HomeCell.DEFAULT_CELL_SIZE);
		}
		
		return cellSize;
	}
}

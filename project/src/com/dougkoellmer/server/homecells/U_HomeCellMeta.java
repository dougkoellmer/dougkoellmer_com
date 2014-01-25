package com.dougkoellmer.server.homecells;

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
			case SWORD:							return 	"Ceremonial sword from Papua New Guinea";
			case WOODEN_CUP:					return 	"Cup machined from a white oak sapling";
			case HATCHET:						return 	"Multipurpose hatchet handcrafted in Sweden";
			case MITER_SAW:						return 	"Precision hand miter saw made in Sweden";
			case CARVING_TOOLS:					return 	"Wood carving tools made in Japan";
			case VACUUM_SEALER:					return 	"Convenient mason jar vacuum sealer";
			case LIGHTER:						return 	"Waterproof wick-based survival lighter";
				
			case ABILITIES:						return "";
			case FOOT_JUGGLING:					return 	"Juggling a tennis ball with only feet";
			case JUGGLING:						return 	"Juggling three tennis balls, various tricks";
			case FAST_SHUFFLE:					return 	"Shuffling cards as fast as possible";
			case CARD_DECK_SPLIT:				return 	"Splitting deck of cards with one hand";
			case CARD_THROW:					return 	"Throwing playing cards into and through things";
			case PEN_TWIRL:						return 	"Twirling pen continuously through four fingers";
			case BOTTLE_OPENING:				return 	"Opening beer bottles with everyday items";
			case FAST_SHOE_TIE:					return 	"Tying shoelaces as fast as possible";
			case ONE_HAND_MATCH_LIGHT:			return 	"Lighting match from matchbook with one hand";
			case FAST_SHIRT_SWAP:				return 	"T-shirt on and off as fast as possible";
				
			case SOFTWARE:						return "";
			case FOR_COMPUTERS:					return "";
			case PRESSURE:						return "Simulation teaching principles of air pressure";
			case PRESSURE_AND_HEAT:				return "Simulation teaching principles of air pressure and heat";
			case RADIOACTIVE_DECAY:				return "Simulation teaching radioactive decay and half-life";
			case CORROSION_CELL:				return "Simulation teaching principles of metal corrosion";
			case FEED_THE_BEAR:					return "Simulation showing novel way that polar bears hunt seals";
			case SWARM:							return "Open-source web-based content management system";
			case B33HIVE:						return "Open-source sandboxed HTML5 application platform";
			case QUICKPHYX:						return "Open-source physics engine for games and simulations";
			case CORROSION_GAME:				return "Training module for the Department of Defense";
			case GLUE:							return "2d heightfield-based viscous fluid simulation";
			case WHIP_THE_RAGDOLL:				return "Sadistic stress reliever...pretend it's your boss";
			case TOUCH_TONE_HERO:				return "Rhythm-based music game using touch-tone keypad";
			case BALANCE_THING_GAME:			return "Physics game where you balance whacky objects";
			case FIREWORKS:						return "Fireworks for end-of-game celebration screen";
			case GRAVITATION:					return "Simple physics simulation of planetary gravity";
					
			case FOR_BIOTICS:					return "";
			case POLISH_FOREST_ADVENTURE:		return "Disturbing walk in cold misty forest in Poland";
			case SPANISH_OPERA_ADVENTURE:		return "Fantastical experience travelling in Seville, Spain";
			case OLD_FRIEND:					return "Poem about fleetingly meeting old friend from childhood";
			case MUSINGS:						return "Random musings on life and nature of reality";
			case WHAT_IS_CORROSION:				return "Essay for the layman on how corrosion works";
			
			case CREATIONS:						return "";
			case USEFUL:						return "";
			case INVENTIONS:					return "";
			case BIKE_CARD_THING:				return "Polite alternative to annoying bicycle bells";
			case DOWEL_HOLDER:					return "Easy way to store long, thin pieces of material";
			case OUTDOOR_TOOL_HOLDER:			return "Easy way to store assorted outdoor tools";
			case GUITAR_CASE:					return "Guitar case that can survive nuclear blast";
			case FLASHLIGHT_LAMP:				return "Clever way to turn any flashlight into diffuse lamp";
			case SLING_SHOT:					return "Primitive take on modern-day wrist-braced slingshots";
			case BREAKAWAY_KNOT:				return "Knot for neck lanyards that disconnects with abrupt force";
			case SANDER_HOLDER:					return "Holds random orbital sander while still powered on";
			case RASP_HANDLE:					return "Clever way to make handle for rat-tailed tools";
			case HANGING_CRATES:				return "Space-saving way to store random materials in garage";
			case HEATING_BAG:					return "Really ghetto way to make a heating pack";
			case SAND_WEIGHT:					return "Cheap way to get a specific amount of mass";
			case SHIRT_PILLOW_CASE:				return "Emergency pillow case for bachelors only";
			case TORSION_KNOT:					return "Knot that gets ridiculously tight using torsion";
					
			case SUNDRY:						return "";
			case TERMINATOR_GLASSES:			return "Glasses that make you look like Terminator";
			case CANVAS_BAG:					return "Simple hand-sewn canvas draw-string pouch";
			case CANVAS_WRAP:					return "Simple hand-sewn canvas tool wrap";
			case SANTOKU_SHEATH:				return "Sturdy leather sheath for santoku knife";
			case F1_SHEATH_MOD:					return "Modification of F1 survival knife sheath";
			case SAW_SHEATH:					return "Magnetic sheath for basic wood handsaw";
			case BROOM:							return "Stiff-bristle broom for cleaning wood shop";
			case FIRE_POI:						return "Fire poi with kevlar monkey-fist knot";
			case JUGGLING_TORCHES:				return "Juggling torches made from hardware store materials";
			case THROWING_KNIVES:				return "Throwing knives cut and ground from steel blanks";
			case STONE_CUP:						return "Caveman cup hewn from block of solid granite";
			case FIRE_POKER:					return "Oaken-handled fire poker with steel dowel";
						
			case WOOD:							return "";
			case TILLER_TREE:					return "Tiller tree to assist in making wooden bows";
			case THROWING_KNIFE_TARGET:			return "Portable target for throwing knife practice";
			case WOOD_MALLET:					return "Massive hickory mallet for rough woodworking";
			case COASTERS:						return "Coasters made from poplar cross sections";
			case KITCHEN_UTENSILS:				return "Set of cooking utensils made from white oak";
			case SHAKERS:						return "Set of salt and pepper shakers from white oak";
			case BIKE_RACK:						return "Mounted bike rack made from white oak";
			case BACKSCRATCHER_1:				return "Graceful backscratcher made from sugar maple";
			case BACKSCRATCHER_2:				return "Manly backscratcher made from red oak";
			case CUTTING_BOARD_STAND:			return "Cutting board stand using no metal fasteners";
			case CUTTING_BOARD:					return "Massive cutting board made from rock maple";
			case BOW_1:							return "Recurved bamboo-reinforced flat bow";
			case BOW_2:							return "Red oak canvas-backed long bow";
			case DOORSTOP:						return "Doorstrop made from section of poplar sapling";
			case DOGGIE_RAMP:					return "Ramp for little doggies to get to high places";
			case OAR:							return "Oar carved from solid piece of hickory";
			case BOOMERANG:						return "Boomerang made from strips of various woods";
			case COASTER_HOLDER:				return "Decorative holder for circular drink coasters";
			case RED_OAK_SPOON:					return "Big head-thumping spoon made from red oak";
			case PLYWOOD_PUNISHER:				return "3-person toboggan made from steam-bent plywood";
						
			case ART:							return "";
			case ROSE:							return "Realistic looking rose drawn with colored pencil";
			case DRAGON:						return "Muscular dragon cast from 2-part liquid plastic";
			case SOLAR_SYSTEM:					return "Alien solar system from acid-etched zinc plate";
			case BANANA:						return "Realistic looking banana drawn with pencil";
			case PEACEFUL_MAN:					return "Peaceful man and flowers sculpted with clay";
			case LADY:							return "Mysterious lady painted with limited colors";
			case BLACKHOLE:						return "Linoleum print block of a black hole";
			case INFINITE_TOLERANCE:			return "Sketch supporting diversity and tolerance";
			case SAVE_ANIMALS:					return "Sketch supporting animals and the saving thereof";
			case DINOSAURS:						return "Sketch supporting dinsaurs taking shit over";
			case QUICKB2_LOGO:					return "Logo for an open source physics engine";
			case B33HIVE_LOGO:					return "Logo for an open source content management system";
			case MILKMAN:						return "Tiny wooden sculpture of mid-20th century milkman";
			case MOTHER_AND_CHILD:				return "Tiny abstract wooden sculpture of mother and child";
			case WOOD_CHAIN:					return "Wooden chain carved from solid length of rock maple";
			case GLASSES_HOLDER:				return "Decorative wooden desktop holder for reading glasses";
			
			default:							return null;
		}
	}
	
	public static I_HomeCellContent getContent(E_HomeCell homeCell)
	{
		switch(homeCell)
		{
			case HOME:							return new FileBasedHomeCellContent("home");
			
			case PRECIOUSES:					return new ThumbnailHomeCellContent();
			case SWORD:							return new ImageHomeCellContent();
			case WOODEN_CUP:					return new ImageHomeCellContent();
			case HATCHET:						return new ImageHomeCellContent();
			case MITER_SAW:						return new ImageHomeCellContent();
			case CARVING_TOOLS:					return new ImageHomeCellContent();
			case VACUUM_SEALER:					return new ImageHomeCellContent();
			case LIGHTER:						return new ImageHomeCellContent();
				
			case ABILITIES:						return new ThumbnailHomeCellContent();
			case FOOT_JUGGLING:					return new ImageHomeCellContent();
			case JUGGLING:						return new ImageHomeCellContent();
			case FAST_SHUFFLE:					return new ImageHomeCellContent();
			case CARD_DECK_SPLIT:				return new ImageHomeCellContent();
			case CARD_THROW:					return new ImageHomeCellContent();
			case PEN_TWIRL:						return new ImageHomeCellContent();
			case BOTTLE_OPENING:				return new ImageHomeCellContent();
			case FAST_SHOE_TIE:					return new ImageHomeCellContent();
			case ONE_HAND_MATCH_LIGHT:			return new ImageHomeCellContent();
			case FAST_SHIRT_SWAP:				return new ImageHomeCellContent();
				
			case SOFTWARE:						return new FileBasedHomeCellContent("software");
			case FOR_COMPUTERS:					return new ThumbnailHomeCellContent();
			case PRESSURE:						return new ImageHomeCellContent();
			case PRESSURE_AND_HEAT:				return new ImageHomeCellContent();
			case RADIOACTIVE_DECAY:				return new ImageHomeCellContent();
			case CORROSION_CELL:				return new ImageHomeCellContent();
			case FEED_THE_BEAR:					return new ImageHomeCellContent();
			case SWARM:							return new ImageHomeCellContent();
			case B33HIVE:						return new ImageHomeCellContent();
			case QUICKPHYX:						return new ImageHomeCellContent();
			case CORROSION_GAME:				return new ImageHomeCellContent();
			case GLUE:							return new ImageHomeCellContent();
			case WHIP_THE_RAGDOLL:				return new ImageHomeCellContent();
			case TOUCH_TONE_HERO:				return new ImageHomeCellContent();
			case BALANCE_THING_GAME:			return new ImageHomeCellContent();
			case FIREWORKS:						return new ImageHomeCellContent();
			case GRAVITATION:					return new ImageHomeCellContent();
					
			case FOR_BIOTICS:					return new ThumbnailHomeCellContent();
			case POLISH_FOREST_ADVENTURE:		return new ImageHomeCellContent();
			case SPANISH_OPERA_ADVENTURE:		return new ImageHomeCellContent();
			case OLD_FRIEND:					return new ImageHomeCellContent();
			case MUSINGS:						return new ImageHomeCellContent();
			case WHAT_IS_CORROSION:				return new ImageHomeCellContent();
			
			case CREATIONS:						return new FileBasedHomeCellContent("creations");
			case USEFUL:						return new FileBasedHomeCellContent("useful");
			case INVENTIONS:					return new ThumbnailHomeCellContent();
			case BIKE_CARD_THING:				return new ImageHomeCellContent();
			case DOWEL_HOLDER:					return new ImageHomeCellContent();
			case OUTDOOR_TOOL_HOLDER:			return new ImageHomeCellContent();
			case GUITAR_CASE:					return new ImageHomeCellContent();
			case FLASHLIGHT_LAMP:				return new ImageHomeCellContent();
			case SLING_SHOT:					return new ImageHomeCellContent();
			case BREAKAWAY_KNOT:				return new ImageHomeCellContent();
			case SANDER_HOLDER:					return new ImageHomeCellContent();
			case RASP_HANDLE:					return new ImageHomeCellContent();
			case HANGING_CRATES:				return new ImageHomeCellContent();
			case HEATING_BAG:					return new ImageHomeCellContent();
			case SAND_WEIGHT:					return new ImageHomeCellContent();
			case SHIRT_PILLOW_CASE:				return new ImageHomeCellContent();
			case TORSION_KNOT:					return new ImageHomeCellContent();
					
			case SUNDRY:						return new ThumbnailHomeCellContent();
			case TERMINATOR_GLASSES:			return new ImageHomeCellContent();
			case CANVAS_BAG:					return new ImageHomeCellContent();
			case CANVAS_WRAP:					return new ImageHomeCellContent();
			case SANTOKU_SHEATH:				return new ImageHomeCellContent();
			case F1_SHEATH_MOD:					return new ImageHomeCellContent();
			case SAW_SHEATH:					return new ImageHomeCellContent();
			case BROOM:							return new ImageHomeCellContent();
			case FIRE_POI:						return new ImageHomeCellContent();
			case JUGGLING_TORCHES:				return new ImageHomeCellContent();
			case THROWING_KNIVES:				return new ImageHomeCellContent();
			case STONE_CUP:						return new ImageHomeCellContent();
			case FIRE_POKER:					return new ImageHomeCellContent();
						
			case WOOD:							return new ThumbnailHomeCellContent();
			case TILLER_TREE:					return new ImageHomeCellContent();
			case THROWING_KNIFE_TARGET:			return new ImageHomeCellContent();
			case WOOD_MALLET:					return new ImageHomeCellContent();
			case COASTERS:						return new ImageHomeCellContent();
			case KITCHEN_UTENSILS:				return new ImageHomeCellContent();
			case SHAKERS:						return new ImageHomeCellContent();
			case BIKE_RACK:						return new ImageHomeCellContent();
			case BACKSCRATCHER_1:				return new ImageHomeCellContent();
			case BACKSCRATCHER_2:				return new ImageHomeCellContent();
			case CUTTING_BOARD_STAND:			return new ImageHomeCellContent();
			case CUTTING_BOARD:					return new ImageHomeCellContent();
			case BOW_1:							return new ImageHomeCellContent();
			case BOW_2:							return new ImageHomeCellContent();
			case DOORSTOP:						return new ImageHomeCellContent();
			case DOGGIE_RAMP:					return new ImageHomeCellContent();
			case OAR:							return new ImageHomeCellContent();
			case BOOMERANG:						return new ImageHomeCellContent();
			case COASTER_HOLDER:				return new ImageHomeCellContent();
			case RED_OAK_SPOON:					return new ImageHomeCellContent();
			case PLYWOOD_PUNISHER:				return new ImageHomeCellContent();
						
			case ART:							return new ThumbnailHomeCellContent();
			case ROSE:							return new ImageHomeCellContent();
			case DRAGON:						return new ImageHomeCellContent();
			case SOLAR_SYSTEM:					return new ImageHomeCellContent();
			case BANANA:						return new ImageHomeCellContent();
			case PEACEFUL_MAN:					return new ImageHomeCellContent();
			case LADY:							return new ImageHomeCellContent();
			case BLACKHOLE:						return new ImageHomeCellContent();
			case INFINITE_TOLERANCE:			return new ImageHomeCellContent();
			case SAVE_ANIMALS:					return new ImageHomeCellContent();
			case DINOSAURS:						return new ImageHomeCellContent();
			case QUICKB2_LOGO:					return new ImageHomeCellContent();
			case B33HIVE_LOGO:					return new ImageHomeCellContent();
			case MILKMAN:						return new ImageHomeCellContent();
			case MOTHER_AND_CHILD:				return new ImageHomeCellContent();
			case WOOD_CHAIN:					return new ImageHomeCellContent();
			case GLASSES_HOLDER:				return new ImageHomeCellContent();
			
			default:							return null;
		}
	}
}

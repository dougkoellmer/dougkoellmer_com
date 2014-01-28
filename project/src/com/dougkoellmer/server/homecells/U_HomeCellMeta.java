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
			case PRESSURE:						return "Simulation teaching<br>principles of pressure";
			case PRESSURE_AND_HEAT:				return "Simulation teaching principles of pressure & heat";
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
			case RESUME:						return "Experimenting with new kind of resume format";
			case PORTFOLIO:						return "Programmer-friendly work portfolio";
			case POLISH_FOREST_ADVENTURE:		return "Disturbing walk in cold misty forest in Poland";
			case SPANISH_OPERA_ADVENTURE:		return "Fantastical experience travelling in Spain";
			case OLD_FRIEND:					return "Poem about fleetingly meeting old friend from childhood";
			case MUSINGS:						return "Random musings on life and nature of reality";
			case WHAT_IS_CORROSION:				return "Essay for the layman on how corrosion works";
			case PERFECT_COFFEE:				return "How to make the perfect cup of coffee";
			
			case CREATIONS:						return "";
			case USEFUL:						return "";
			case INVENTIONS:					return "";
			case BIKE_CARD_THING:				return "Polite alternative to annoying bicycle bells";
			case DOWEL_HOLDER:					return "Easy way to store long, thin pieces of material";
			case OUTDOOR_TOOL_HOLDER:			return "Easy way to store assorted outdoor tools";
			case GUITAR_CASE:					return "Guitar case that can survive nuclear bombs";
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
			case FIRE_POI:						return "Fire poi with kevlar monkey-fist knots";
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
			case BOW_1:							return "Recurved maple flat bow with bamboo backing";
			case BOW_2:							return "Red oak long bow with canvas backing";
			case DOORSTOP:						return "Doorstop made from section of poplar sapling";
			case DOGGIE_RAMP:					return "Ramp for little doggies to get to high places";
			case OAR:							return "Oar carved from solid log of hickory";
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
		}
		
		return null;
	}
	
	public static I_HomeCellContent getContent(E_HomeCell homeCell)
	{
		switch(homeCell)
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
			case PRESSURE:						return new CellComingSoonContent();
			case PRESSURE_AND_HEAT:				return new CellComingSoonContent();
			case RADIOACTIVE_DECAY:				return new CellComingSoonContent();
			case CORROSION_CELL:				return new CellComingSoonContent();
			case FEED_THE_BEAR:					return new CellComingSoonContent();
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
			case RESUME:						return new CellComingSoonContent();
			case PORTFOLIO:						return new CellComingSoonContent();
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
			case FLASHLIGHT_LAMP:				return new CellComingSoonContent();
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
			case TERMINATOR_GLASSES:			return new CellComingSoonContent();
			case CANVAS_BAG:					return new CellComingSoonContent();
			case CANVAS_WRAP:					return new CellComingSoonContent();
			case SANTOKU_SHEATH:				return new CellComingSoonContent();
			case F1_SHEATH_MOD:					return new CellComingSoonContent();
			case SAW_SHEATH:					return new CellComingSoonContent();
			case BROOM:							return new CellComingSoonContent();
			case FIRE_POI:						return new CellComingSoonContent();
			case JUGGLING_TORCHES:				return new CellComingSoonContent();
			case THROWING_KNIVES:				return new CellComingSoonContent();
			case STONE_CUP:						return new CellComingSoonContent();
			case FIRE_POKER:					return new CellComingSoonContent();
						
			case WOOD:							return new ThumbnailHomeCellContent();
			case TILLER_TREE:					return new CellComingSoonContent();
			case THROWING_KNIFE_TARGET:			return new CellComingSoonContent();
			case WOOD_MALLET:					return new CellComingSoonContent();
			case COASTERS:						return new CellComingSoonContent();
			case KITCHEN_UTENSILS:				return new CellComingSoonContent();
			case SHAKERS:						return new CellComingSoonContent();
			case BIKE_RACK:						return new CellComingSoonContent();
			case BACKSCRATCHER_1:				return new CellComingSoonContent();
			case BACKSCRATCHER_2:				return new CellComingSoonContent();
			case CUTTING_BOARD_STAND:			return new CellComingSoonContent();
			case CUTTING_BOARD:					return new CellComingSoonContent();
			case BOW_1:							return new CellComingSoonContent();
			case BOW_2:							return new CellComingSoonContent();
			case DOORSTOP:						return new CellComingSoonContent();
			case DOGGIE_RAMP:					return new CellComingSoonContent();
			case OAR:							return new CellComingSoonContent();
			case BOOMERANG:						return new CellComingSoonContent();
			case COASTER_HOLDER:				return new CellComingSoonContent();
			case RED_OAK_SPOON:					return new CellComingSoonContent();
			case PLYWOOD_PUNISHER:				return new CellComingSoonContent();
						
			case ART:							return new ThumbnailHomeCellContent();
			case ROSE:							return new CellComingSoonContent();
			case DRAGON:						return new CellComingSoonContent();
			case SOLAR_SYSTEM:					return new CellComingSoonContent();
			case BANANA:						return new CellComingSoonContent();
			case PEACEFUL_MAN:					return new CellComingSoonContent();
			case LADY:							return new CellComingSoonContent();
			case BLACKHOLE:						return new CellComingSoonContent();
			case INFINITE_TOLERANCE:			return new CellComingSoonContent();
			case SAVE_ANIMALS:					return new CellComingSoonContent();
			case DINOSAURS:						return new CellComingSoonContent();
			case QUICKB2_LOGO:					return new CellComingSoonContent();
			case B33HIVE_LOGO:					return new CellComingSoonContent();
			case MILKMAN:						return new CellComingSoonContent();
			case MOTHER_AND_CHILD:				return new CellComingSoonContent();
			case WOOD_CHAIN:					return new CellComingSoonContent();
			case GLASSES_HOLDER:				return new CellComingSoonContent();
			
			default:							return null;
		}
	}
}

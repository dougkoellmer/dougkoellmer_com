var TOTAL_TIME = 24; // in billions of years
var HALF_LIFE = 4; // in billions of years
var HALF_LIFE_COUNT = TOTAL_TIME / HALF_LIFE;

var ATOM_COUNT = 100;

var NORMAL_VIBRATION_SPEED = 20; //pixels per second

var VIBRATION_DIRECTION_CHANGE_TIME = 1/10;

var AGITATED_VIBRATION_FACTOR = 4; // multiplies the normal speed

var AGITATED_TIME = .5; //seconds
var BLOWING_UP_TIME = .733; // seconds;

var RANDOM_PLACEMENT_ATTEMPT_COUNT = 150;

var VIBRATION_RADIUS = 20;
var VIBRATION_RADIUS_SQUARED = VIBRATION_RADIUS*VIBRATION_RADIUS;

var VIBRATION_RANGE = VIBRATION_RADIUS * 2;

var SLIDER_MAX = 100;
var SLIDER_STEP = .1;

var ATOM_OVERLAP_RADIUS = 25;

var ATOM_RADIUS = 17;

var SLIDER_START_VALUE = 0;

var DEPTH_BASE_CLASS = "atom_depth_";
var DEPTH_CHANCES = [.6, .8, 1];

var TARGET_FPS = 60;

var ONE_BILLION = 1000000000;

var LIL_CHUNK_VELOCITY = 272.85;
var BIG_CHUNK_VELOCITY = 46.38;

var LIL_CHUNK_ALPHA_FADE_START = BLOWING_UP_TIME *(16/21);
var BIG_CHUNK_ALPHA_FADE_START = BLOWING_UP_TIME *(12/21);
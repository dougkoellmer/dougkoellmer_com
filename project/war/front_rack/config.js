const PIPE_DIAM = .84;
const FORK_ANGLE = 18;
const PIPE_OVERHANG = .5;//.25;
const WHEEL_DIAM = 28.5;
const TIRE_THICKNESS = 2;
const RIM_THICKNESS = 1;
const OFFSET_FROM_AXLE = 3;
const OFFSET_FROM_TIRE_TOP = 2.5;
const BOTTOM_PIPE_LENGTH = 4;
const GRID_SIZE = 1;
const PIXELS_PER_INCH = 15;
const SPOKE_WIDTH = 2;
const GRID_LINE_WIDTH = .5;
const SPOKE_COUNT = 16;
const FORK_BOTTOM_DIAM = .85; //TODO: confirm measurement
const FORK_TOP_DIAM = 1.2; // TODO: confirm measurement
const FORK_LENGTH = 18.5;
const DEFAULT_LINE_WIDTH = 1;
const CROSS_PIPE_COUNT = 4;
const TOP_PIPE_COUNT = 3;
const CENTER_V_OVERHANG = 4;
const PIPE_THROWAWAY_OVERHANG_WITH_HOLE = 1.0;
const PIPE_THROWAWAY_OVERHANG_WITHOUT_HOLE = .5;

const START_POINT = new Point(30, 25);
const SHOWING_RIGHT_SIDE = true;


const GRID_COLOR = "black";
const TIRE_COLOR = "black";
const RIM_COLOR = "grey";
const SPOKE_COLOR = "black";
const FRAME_COLOR = "#C4E4BE";
const PIPE_COLOR = "grey";



const FORK_ANGLE_RAD = (Math.PI/180.0) * FORK_ANGLE;
const RACK_HEIGHT = WHEEL_DIAM/2.0 + OFFSET_FROM_AXLE + OFFSET_FROM_TIRE_TOP;
const PIPE_RADIUS = PIPE_DIAM/2.0;
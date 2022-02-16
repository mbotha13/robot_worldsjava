package za.co.wethinkcode.toyrobot;

public class Robot {

    private final Position TOP_LEFT = new Position(-100,100);
    private final Position BOTTOM_RIGHT = new Position(100,-200);


    public static final Position CENTRE = new Position(0,0);

    private Position position;

    private String status;
    private final String name;

    public final  Direction currentDirection;

    public Position getPosition() {
        return this.position;
    }

    public Robot(String name) {
        this.name = name;
        this.status = "Ready";
        this.position = CENTRE;
        this.currentDirection = Direction.NORTH;
    }

    public String getStatus() {                                                                         //<5>
        return this.status;
    }

    public Direction getCurrentDirection() {                                                               //<8>
        return this.currentDirection;
    }

    public boolean updatePosition(int nrSteps){
        int newY = this.position.getY();
        int newX = this.position.getX();

        if (Direction.NORTH.equals(this.currentDirection)) {
            newY = newY + nrSteps;
        }
        Position newPosition = new Position(newX ,newY);
        if (newPosition.isIn(TOP_LEFT,BOTTOM_RIGHT)){
            this.position = newPosition;
            return true;
        }
        return false;
    }

    public void setStatus (String status){
        this.status= status;
    }

    public boolean handleCommand(Command command) {
        return command.execute(this);
    }


    @Override
    public String toString() {
        return "[" + this.position.getX() + "," + this.position.getY() + "] "
                + "{" + this.currentDirection + "} "
                + this.name + "> " + this.status;
    }
}
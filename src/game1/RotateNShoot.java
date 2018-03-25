package game1;

// method that controls the AI and makes it spin and shoot all the time....
// Currently the method will shoot non stop without stopping at all.

public class RotateNShoot implements Controller {
    Action action = new Action();

    public Action action() {

        action.shoot = true;
        action.turn = 1;
        return action;
    } }



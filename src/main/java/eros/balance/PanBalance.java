package eros.balance;

import eros.data.interfaces.Weightable;

public class PanBalance<T extends Weightable> {

    private static final double ERROR_MARGIN = 0.05d;

    private final Pan<T> leftPan = new Pan<>();
    private final Pan<T> rightPan = new Pan<>();

    public void addItemToLeftPan(T x) {
        leftPan.add(x);
    }

    public void addItemToRightPan(T item) {
        rightPan.add(item);
    }

    public Response whichPaneIsHeavier() {

        var difference = leftPan.getWeight() - rightPan.getWeight();
        if (isAcceptedAsEqual(difference)) {

            return Response.EQUAL;
        }

        return isPositive(difference) ? Response.LEFT : Response.RIGTH;

    }

    private boolean isAcceptedAsEqual(double difference) {
        return Math.abs(difference) <= ERROR_MARGIN;
    }


    private boolean isPositive(double difference) {
        return difference > 0.0d;
    }

    public enum Response {

        EQUAL {

        },
        RIGTH {

        },
        LEFT {

        }

    }

}

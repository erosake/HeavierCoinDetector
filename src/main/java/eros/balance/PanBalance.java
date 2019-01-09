package eros.balance;

import eros.data.interfaces.Weightable;

public class PanBalance<T extends Weightable> {

	public static enum Response {

		EQUAL {

		},
		RIGTH {

		},
		LEFT {

		};

	}

	private Pan<T> leftPan = new Pan<>();
	private Pan<T> rightPan = new Pan<>();

	public void addItemToLeftPan(T x) {
		leftPan.add(x);
	}

	public void addItemToRightPan(T item) {
		rightPan.add(item);
	}

	public Response whichPaneIsHeavier() {

		var difference = leftPan.getWeight() - rightPan.getWeight();
		if (difference == 0.0d) {

			return Response.EQUAL;
		}

		return difference > 0.0d ? Response.LEFT : Response.RIGTH;

	}

}

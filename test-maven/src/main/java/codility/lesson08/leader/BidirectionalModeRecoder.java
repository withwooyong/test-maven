package codility.lesson08.leader;

public class BidirectionalModeRecoder {

	int forwardTotCount = 0;
	int forwardModeNum = 0;
	int forwardModeNumCount = 0;

	int reverseTotCount = 0;
	int reverseModeNum = 0;
	int reverseModeNumCount = 0;

	boolean equiLeaderYN = false;

	public void setForwardModeNum(int forwardTotCount, int forwardModeNum, int forwardModeNumCount) {
		this.forwardTotCount = forwardTotCount;
		this.forwardModeNum = forwardModeNum;
		this.forwardModeNumCount = forwardModeNumCount;
		setEquiLeader();
	}

	public void setReverseModeNum(int reverseTotCount, int reverseModeNum, int reverseModeNumCount) {
		this.reverseTotCount = reverseTotCount;
		this.reverseModeNum = reverseModeNum;
		this.reverseModeNumCount = reverseModeNumCount;
		setEquiLeader();
	}

	public void setEquiLeader() {
		if (forwardTotCount == 0 || reverseTotCount == 0)
			equiLeaderYN = false;
		if (forwardModeNum == reverseModeNum && ((double) forwardModeNumCount / (forwardTotCount) > 0.5
				&& (double) reverseModeNumCount / (reverseTotCount) > 0.5))
			equiLeaderYN = true;
		else
			equiLeaderYN = false;
	}

	public boolean isEquiLeader() {
		return equiLeaderYN;
	}

}

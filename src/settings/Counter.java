package settings;

/**
 * represents a counter amount of objects in the game -> number of balls or number of blocks.
 */
public class Counter {

    private int count = 0;

    /**
     * add number to current count.
     * @param number int.
     */
    public void increase(int number) {
        this.count += number;
    }
    // subtract number from current count.

    /**
     * subtract number from current count.
     * @param number int.
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * get count.
     * @return int.
     */
    public int getValue() {
        return count;
    }
}

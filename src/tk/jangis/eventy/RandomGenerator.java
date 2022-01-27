package tk.jangis.eventy;

import java.util.Random;

public class RandomGenerator {
    public int randomInt(int min, int max){
        Random random = new Random();
        return random.ints(min, max).findFirst().getAsInt();
    }
}
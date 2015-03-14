package idea;

import bits.BitArray;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by krzysztofkaczor on 3/10/15.
 */
public class IdeaRoundTest {
    @Test(expected = IllegalArgumentException.class)
    public void inputChecking() {
        BitArray k1 = new BitArray(0x0001, 16);
        BitArray k2 = new BitArray(0x0001, 16);
        BitArray k3 = new BitArray(0x0001, 16);
        BitArray k4 = new BitArray(0x0001, 15);
        BitArray k5 = new BitArray(0x0001, 16);
        BitArray k6 = new BitArray(0x0001, 16);
        IdeaBlock block = new IdeaBlock();

        IdeaRound ideaRound = new IdeaRound();
        ideaRound.encrypt(block, k1, k2, k3, k4, k5, k6);
    }

    @Test
    public void computeRound() {
        BitArray k1 = new BitArray(0x0001, 16);
        BitArray k2 = new BitArray(0x0002, 16);
        BitArray k3 = new BitArray(0x0003, 16);
        BitArray k4 = new BitArray(0x0004, 16);
        BitArray k5 = new BitArray(0x0005, 16);
        BitArray k6 = new BitArray(0x0006, 16);
        IdeaBlock block = new IdeaBlock(0x0000_0001, 0x0002_0003);

        IdeaRound ideaRound = new IdeaRound();
        IdeaBlock result = ideaRound.encrypt(block, k1, k2, k3, k4, k5, k6);

        Assert.assertEquals("[00 f0 00 f5 01 0a 01 05]", result.toHexString());
    }
}

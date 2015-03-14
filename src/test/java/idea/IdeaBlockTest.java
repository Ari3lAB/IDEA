package idea;

import bits.BitArray;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by krzysztofkaczor on 3/10/15.
 */
public class IdeaBlockTest {
    @Test(expected = IllegalArgumentException.class)
    public void constructorTest() {
        BitArray bitArray = new BitArray(63);
        new IdeaBlock(bitArray);
    }

    @Test
    public void valueConstructorTest() {
        IdeaBlock block = new IdeaBlock(0x0000_00af, 0x0002_0003);
        Assert.assertEquals("[00 00 00 af 00 02 00 03]", block.toHexString());
    }


    @Test
    public void split16() {
        IdeaBlock block = new IdeaBlock(0x0000_00af, 0x0002_a003);
        BitArray[] result = block.split16();
        BitArray a = result[0];
        BitArray b = result[1];
        BitArray c = result[2];
        BitArray d = result[3];

        Assert.assertEquals("[00 00]", a.toHexString());
        Assert.assertEquals("[00 af]", b.toHexString());
        Assert.assertEquals("[00 02]", c.toHexString());
        Assert.assertEquals("[a0 03]", d.toHexString());
    }

    @Test
    public void bitarraysConstructorTest() {
        BitArray a = new BitArray(0x1111, 16);
        BitArray b = new BitArray(0x2222, 16);
        BitArray c = new BitArray(0x3333, 16);
        BitArray d = new BitArray(0x4444, 16);

        IdeaBlock block = new IdeaBlock(a, b, c, d);

        Assert.assertEquals("[11 11 22 22 33 33 44 44]", block.toHexString());
    }
}

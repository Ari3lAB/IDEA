package bits;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by krzysztofkaczor on 3/10/15.
 */
public class ExclusiveOrOperatorTest
{
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfOperandAreIncompatible(){
        BitArray ba = new BitArray(2);
        BitArray ba2 = new BitArray(3);
        ExclusiveOrOperator exclusiveOrOperator = new ExclusiveOrOperator();

        exclusiveOrOperator.combine(ba, ba2);
    }

    @Test
    public void xorTest(){
        BitArray ba = new BitArray(4);
        BitArray ba2 = new BitArray(4);
        ExclusiveOrOperator exclusiveOrOperator = new ExclusiveOrOperator();

        ba.set(0, false);
        ba2.set(0, false);
        ba.set(1, true);
        ba2.set(1, false);
        ba.set(2, false);
        ba2.set(2, true);
        ba.set(3, true);
        ba2.set(3, true);

        BitArray result = exclusiveOrOperator.combine(ba, ba2);
        Assert.assertEquals(false, result.get(0));
        Assert.assertEquals(true, result.get(1));
        Assert.assertEquals(true, result.get(2));
        Assert.assertEquals(false, result.get(3));
    }

    @Test
    public void xorDigits() {
        BitArray ba = new BitArray(0x0000, 16);
        BitArray ba2 = new BitArray(0x00f0, 16);

        ExclusiveOrOperator exclusiveOrOperator = new ExclusiveOrOperator();
        Assert.assertEquals("[00 f0]", exclusiveOrOperator.combine(ba, ba2).toHexString());
    }
}

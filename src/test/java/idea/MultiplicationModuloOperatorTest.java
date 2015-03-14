package idea;

import bits.BitArray;
import idea.IdeaMultiplicationModuloOperator;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by krzysztofkaczor on 3/10/15.
 */
public class MultiplicationModuloOperatorTest {
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfOperandAreIncompatible(){
        BitArray ba = new BitArray(2);
        BitArray ba2 = new BitArray(3);
        IdeaMultiplicationModuloOperator multiplicationModuloOperator = new IdeaMultiplicationModuloOperator();

        multiplicationModuloOperator.combine(ba, ba2);
    }

    @Test
    public void multiplyDigits() {
        BitArray by = new BitArray(0x3, 16);
        BitArray by2 = new BitArray(0x4, 16);

        IdeaMultiplicationModuloOperator multiplicationModuloOperator = new IdeaMultiplicationModuloOperator();
        BitArray result = multiplicationModuloOperator.combine(by, by2);

        Assert.assertEquals("[00 0c]", result.toHexString());
    }

    @Test
    public void multiplyDigits2() {
        BitArray by = new BitArray(1290, 16);
        BitArray by2 = new BitArray(57856, 16);

        IdeaMultiplicationModuloOperator multiplicationModuloOperator = new IdeaMultiplicationModuloOperator();
        BitArray result = multiplicationModuloOperator.combine(by, by2);

        Assert.assertEquals("[53134]", result.toDecString());
    }
}

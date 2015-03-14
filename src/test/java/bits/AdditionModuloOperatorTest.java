package bits;

import bits.AdditionModuloOperator;
import bits.BitArray;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by krzysztofkaczor on 3/9/15.
 */
public class AdditionModuloOperatorTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfOperandAreIncompatible(){
        BitArray ba = new BitArray(2);
        BitArray ba2 = new BitArray(3);
        AdditionModuloOperator additionModuloOperator = new AdditionModuloOperator();

        additionModuloOperator.combine(ba, ba2);
    }

    @Test
    public void addTwoBytes() {
        BitArray by = new BitArray(8);
        BitArray by2 = new BitArray(8);

        by.set(6, true);
        by.set(7, true);
        by2.set(1, true);
        by2.set(7, true);


        AdditionModuloOperator additionModuloOperator = new AdditionModuloOperator();
        BitArray result = additionModuloOperator.combine(by, by2);
        Assert.assertEquals(false, result.get(0));
        Assert.assertEquals(true, result.get(1));
        Assert.assertEquals(false, result.get(2));
        Assert.assertEquals(false, result.get(3));
        Assert.assertEquals(false, result.get(4));
        Assert.assertEquals(true, result.get(5));
        Assert.assertEquals(false, result.get(6));
        Assert.assertEquals(false, result.get(7));
    }

    @Test
    public void ignoreOverflow() {
        BitArray by = new BitArray(2);
        BitArray by2 = new BitArray(2);

        by.set(0, true);
        by.set(1, true);
        by2.set(0, true);
        by2.set(1, true);


        AdditionModuloOperator additionModuloOperator = new AdditionModuloOperator();
        BitArray result = additionModuloOperator.combine(by, by2);
        Assert.assertEquals(true, result.get(0));
        Assert.assertEquals(false, result.get(1));
    }

    @Test
    public void addTwoDigits() {
        BitArray ba = new BitArray(0x000f, 16);
        BitArray ba2 = new BitArray(0x0019, 16);

        AdditionModuloOperator additionModuloOperator = new AdditionModuloOperator();
        Assert.assertEquals("[00 28]", additionModuloOperator.combine(ba, ba2).toHexString());
    }

    @Test
    public void addTwoDigits2() {
        BitArray ba = new BitArray(51995, 16);
        BitArray ba2 = new BitArray(29291, 16);

        AdditionModuloOperator additionModuloOperator = new AdditionModuloOperator();
        Assert.assertEquals("[15750]", additionModuloOperator.combine(ba, ba2).toDecString());
    }

    @Test
    public void addTwoDigits3() {
        BitArray ba = new BitArray(240, 16);
        BitArray ba2 = new BitArray(7, 16);

        AdditionModuloOperator additionModuloOperator = new AdditionModuloOperator();
        Assert.assertEquals("[247]", additionModuloOperator.combine(ba, ba2).toDecString());
    }
}

package bits;

import bits.BitArray;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by krzysztofkaczor on 3/9/15.
 */
public class BitArrayTest {
    @Test
    public void setterAndGetterTest() {
        BitArray ba = new BitArray(4);
        ba.set(0, true);
        ba.set(3, true);

        Assert.assertEquals(true, ba.get(0));
        Assert.assertEquals(false, ba.get(1));
        Assert.assertEquals(false, ba.get(2));
        Assert.assertEquals(true, ba.get(3));
    }

    @Test
    public void swapTest() {
        BitArray ba = new BitArray(2);
        ba.set(1, true);

        ba.swap(0);
        ba.swap(1);

        Assert.assertEquals(true, ba.get(0));
        Assert.assertEquals(false, ba.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void swapOutOfBoundsTest() {
        BitArray ba = new BitArray(1);
        ba.get(4);
    }

    @Test
    public void sizeGetterTest() {
        BitArray ba = new BitArray(5);

        Assert.assertEquals(5, ba.size());
    }

    @Test
    public void toStringTest(){
        BitArray ba = new BitArray(4);
        ba.set(0, true);
        ba.set(1, false);
        ba.set(2, true);
        ba.set(3, true);
        Assert.assertEquals("[1011]", ba.toString());
    }

    @Test
    public void byteToStringHexTest(){
        BitArray ba = new BitArray(8);
        ba.set(0, true);
        ba.set(1, false);
        ba.set(2, true);
        ba.set(3, false);
        ba.set(4, true);
        ba.set(5, true);
        ba.set(6, true);
        ba.set(7, true);
        Assert.assertEquals("[af]", ba.toString());
    }

    @Test
    public void bytesToStringHexTest(){
        BitArray ba = new BitArray(16);
        ba.set(0, false);
        ba.set(1, false);
        ba.set(2, true);
        ba.set(3, false);
        ba.set(4, false);
        ba.set(5, false);
        ba.set(6, false);
        ba.set(7, true);

        ba.set(8, true);
        ba.set(9, true);
        ba.set(10, true);
        ba.set(11, true);
        ba.set(12, true);
        ba.set(13, false);
        ba.set(14, true);
        ba.set(15, true);

        Assert.assertEquals("[21 fb]", ba.toHexString());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructor() {
        new BitArray(-1);
    }

    @Test
    public void testLeftShift() {
        BitArray ba = new BitArray(2);
        ba.set(1, true);

        BitArray newBitArray = ba.shiftLeft(2);

        Assert.assertEquals(false, newBitArray.get(0));
        Assert.assertEquals(true, newBitArray.get(1));
        Assert.assertEquals(false, newBitArray.get(2));
        Assert.assertEquals(false, newBitArray.get(3));
    }

    @Test
    public void testLeftShiftBy0() {
        BitArray ba = new BitArray(2);
        ba.set(1, true);

        BitArray newBitArray = ba.shiftLeft(0);

        Assert.assertEquals(false, newBitArray.get(0));
        Assert.assertEquals(true, newBitArray.get(1));
    }

    @Test
    public void testRightShift() {
        BitArray ba = new BitArray(2);
        ba.set(1, true);

        BitArray newBitArray = ba.shiftRight(2);

        Assert.assertEquals(false, newBitArray.get(0));
        Assert.assertEquals(false, newBitArray.get(1));
        Assert.assertEquals(false, newBitArray.get(2));
        Assert.assertEquals(true, newBitArray.get(3));
    }

    @Test
    public void testRightShiftBy0() {
        BitArray ba = new BitArray(2);
        ba.set(1, true);

        BitArray newBitArray = ba.shiftRight(0);

        Assert.assertEquals(false, newBitArray.get(0));
        Assert.assertEquals(true, newBitArray.get(1));
    }

    @Test
    public void testModuloLeftShift() {
        BitArray ba = new BitArray(2);
        ba.set(1, true);

        BitArray newBitArray = ba.moduloShiftLeft(1);

        Assert.assertEquals(true, newBitArray.get(0));
        Assert.assertEquals(false, newBitArray.get(1));
    }

    @Test
    public void testModuloLeftShiftBy0() {
        BitArray ba = new BitArray(2);
        ba.set(1, true);

        BitArray newBitArray = ba.moduloShiftLeft(0);

        Assert.assertEquals(false, newBitArray.get(0));
        Assert.assertEquals(true, newBitArray.get(1));
    }

    @Test
    public void testRotateLeft() {
        BitArray ba = new BitArray(0b1110, 4);

        BitArray newBitArray = ba.rotateLeft(2);

        Assert.assertEquals("[1011]", newBitArray.toBinString());
    }

    @Test
    public void testValueConstructor() {
        BitArray bitArray = new BitArray(0b1101, 8);

        Assert.assertEquals(false, bitArray.get(0));
        Assert.assertEquals(false, bitArray.get(1));
        Assert.assertEquals(false, bitArray.get(2));
        Assert.assertEquals(false, bitArray.get(3));
        Assert.assertEquals(true, bitArray.get(4));
        Assert.assertEquals(true, bitArray.get(5));
        Assert.assertEquals(false, bitArray.get(6));
        Assert.assertEquals(true, bitArray.get(7));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValueConstructorExceptionThrowing() {
        new BitArray(0b111, 2);
    }

    @Test
    public void testValueConstructorEdgeCase() {
        new BitArray(0b11, 2);
    }

    @Test
    public void testTwosComplement() {
        BitArray ba = new BitArray(0x00c0, 16);
        Assert.assertEquals("[ff 40]", ba.twosComplement().toHexString());
    }

    @Test
    public void testInversion() {
        BitArray ba = new BitArray(0x0080, 16);
        Assert.assertEquals(new BitArray(0xfe01, 16).toBinString(), ba.invert().toBinString());
    }
}

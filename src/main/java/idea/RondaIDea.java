package idea;

import bits.OperadorModuloAdicional;
import bits.BitArray;
import bits.OperadorOrExclusivo;

/**
 * Created by krzysztofkaczor on 3/10/15.
 */
public class RondaIDea {
    public BloqueIdea encrypt(BloqueIdea block, BitArray k1, BitArray k2, BitArray k3, BitArray k4, BitArray k5, BitArray k6) {
        if (    k1.size() != 16 ||
                k2.size() != 16 ||
                k3.size() != 16 ||
                k4.size() != 16 ||
                k5.size() != 16 ||
                k6.size() != 16) {
            throw new IllegalArgumentException();
        }

        BloqueIdea resultBlock = block.cloneDeep();

        OperadorModuloAdicional additionModuloOperator = new OperadorModuloAdicional();
        OperadorOrExclusivo exclusiveOrOperator = new OperadorOrExclusivo();
        IdeaMultiplicationModuloOperator multiplicationModuloOperator = new IdeaMultiplicationModuloOperator();

        BitArray[] block16bits = resultBlock.split16();
        BitArray a = block16bits[0];
        BitArray b = block16bits[1];
        BitArray c = block16bits[2];
        BitArray d = block16bits[3];
        BitArray e = null;
        BitArray f = null;
        BitArray swap = null;

        a = multiplicationModuloOperator.combine(a, k1);
        b = additionModuloOperator.combine(b, k2);
        c = additionModuloOperator.combine(c, k3);
        d = multiplicationModuloOperator.combine(d, k4);
        e = exclusiveOrOperator.combine(a, c);
        f = exclusiveOrOperator.combine(b, d);
        e = multiplicationModuloOperator.combine(e, k5);
        f = additionModuloOperator.combine(f, e);
        f = multiplicationModuloOperator.combine(f, k6);
        e = additionModuloOperator.combine(e, f);
        a = exclusiveOrOperator.combine(a, f);
        c = exclusiveOrOperator.combine(c, f);
        b = exclusiveOrOperator.combine(b, e);
        d = exclusiveOrOperator.combine(d, e);

        swap = c;
        c = b;
        b = swap;

        return new BloqueIdea(a, b, c, d);
    }
}

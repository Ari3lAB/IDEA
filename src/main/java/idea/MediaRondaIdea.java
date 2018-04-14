package idea;

import bits.OperadorModuloAdicional;
import bits.BitArray;

/**
 * Created by krzysztofkaczor on 3/11/15.
 */
public class MediaRondaIdea {
    public BloqueIdea encrypt(BloqueIdea block, BitArray k1, BitArray k2, BitArray k3, BitArray k4) {
        if (    k1.size() != 16 ||
                k2.size() != 16 ||
                k3.size() != 16 ||
                k4.size() != 16 ) {
            throw new IllegalArgumentException();
        }

        BloqueIdea resultBlock = block.cloneDeep();

        OperadorModuloAdicional additionModuloOperator = new OperadorModuloAdicional();
        IdeaMultiplicationModuloOperator multiplicationModuloOperator = new IdeaMultiplicationModuloOperator();

        BitArray[] block16bits = resultBlock.split16();
        BitArray a = block16bits[0];
        BitArray b = block16bits[2]; //Cambio de lugar
        BitArray c = block16bits[1]; //Cambio de lugar
        BitArray d = block16bits[3];

        a = multiplicationModuloOperator.combine(a, k1);
        b = additionModuloOperator.combine(b, k2);
        c = additionModuloOperator.combine(c, k3);
        d = multiplicationModuloOperator.combine(d, k4);

        return new BloqueIdea(a, b, c, d);
    }
}

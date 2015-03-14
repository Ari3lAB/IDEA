package idea;

import bits.BitArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krzysztofkaczor on 3/12/15.
 */
public class StringToIdeaBlockConverter {
    public List<IdeaBlock> convert(String stringToConvert) {
        List<IdeaBlock> listOfBitArrays = new ArrayList<>();
        for(int i = 0;i < (stringToConvert.length() / 8) + 1;i++) {
            int[] values = new int[8];

            for (int j = 0;j < 8;j++) {
                int index = i * 8 + j;
                if (index < stringToConvert.length()) {
                    values[j] = (int)stringToConvert.charAt(index);
                } else {
                    values[j] = 0;
                }
            }

            listOfBitArrays.add(new IdeaBlock(values));
        }

        return listOfBitArrays;
    }
}

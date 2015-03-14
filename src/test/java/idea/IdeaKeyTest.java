package idea;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by krzysztofkaczor on 3/11/15.
 */
public class IdeaKeyTest {

    @Test
    public void testSettingKeyParts() {
        IdeaKey key = new IdeaKey();
        key.setK(0, 0x001);
        key.setK(1, 0x002);
        key.setK(2, 0x003);
        key.setK(3, 0x004);
        key.setK(4, 0x005);
        key.setK(5, 0x006);
        key.setK(6, 0x007);
        key.setK(7, 0x008);

        Assert.assertEquals("[00 01 00 02 00 03 00 04 00 05 00 06 00 07 00 08]", key.toString());
    }

    @Test
    public void testGettingKeyParts() {
        IdeaKey key = new IdeaKey();
        key.setK(0, 0x001);
        key.setK(1, 0x002);
        key.setK(2, 0x003);
        key.setK(3, 0x004);
        key.setK(4, 0x005);
        key.setK(5, 0x006);
        key.setK(6, 0x007);
        key.setK(7, 0x008);

        Assert.assertEquals("[00 01]", key.getSubkey(0).toHexString());
        Assert.assertEquals("[00 02]", key.getSubkey(1).toHexString());
        Assert.assertEquals("[00 03]", key.getSubkey(2).toHexString());
        Assert.assertEquals("[00 04]", key.getSubkey(3).toHexString());
        Assert.assertEquals("[00 05]", key.getSubkey(4).toHexString());
        Assert.assertEquals("[00 06]", key.getSubkey(5).toHexString());
        Assert.assertEquals("[00 07]", key.getSubkey(6).toHexString());
        Assert.assertEquals("[00 08]", key.getSubkey(7).toHexString());
    }

    @Test
    public void generateNextKeys() {
        IdeaKey key = new IdeaKey();
        key.setK(0, 0x001);
        key.setK(1, 0x002);
        key.setK(2, 0x003);
        key.setK(3, 0x004);
        key.setK(4, 0x005);
        key.setK(5, 0x006);
        key.setK(6, 0x007);
        key.setK(7, 0x008);

        IdeaKey nextKey = null;

        nextKey = key.generateNextKey();
        Assert.assertEquals("[04 00 06 00 08 00 0a 00 0c 00 0e 00 10 00 02 00]", nextKey.toString());

        nextKey = nextKey.generateNextKey();
        Assert.assertEquals("[00 10 00 14 00 18 00 1c 00 20 00 04 00 08 00 0c]", nextKey.toString());

        nextKey = nextKey.generateNextKey();
        Assert.assertEquals("[28 00 30 00 38 00 40 00 08 00 10 00 18 00 20 00]", nextKey.toString());

        nextKey = nextKey.generateNextKey();
        Assert.assertEquals("[00 70 00 80 00 10 00 20 00 30 00 40 00 50 00 60]", nextKey.toString());

        nextKey = nextKey.generateNextKey();
        Assert.assertEquals("[00 00 20 00 40 00 60 00 80 00 a0 00 c0 00 e0 01]", nextKey.toString());
    }

}

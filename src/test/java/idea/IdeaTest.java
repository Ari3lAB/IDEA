package idea;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by krzysztofkaczor on 3/11/15.
 */
public class IdeaTest {
    @Test
    public void testEncryption() {
        IdeaKey key = new IdeaKey();
        key.setK(0, 0x001);
        key.setK(1, 0x002);
        key.setK(2, 0x003);
        key.setK(3, 0x004);
        key.setK(4, 0x005);
        key.setK(5, 0x006);
        key.setK(6, 0x007);
        key.setK(7, 0x008);

        Idea idea = new Idea(key);

        IdeaBlock block = new IdeaBlock(0x0000_0001, 0x0002_0003);
        IdeaBlock result = idea.encrypt(block);
        IdeaBlock decrypted = idea.decrypt(result);

        Assert.assertEquals("[11 fb ed 2b 01 98 6d e5]", result.toHexString());
    }

    @Test
    public void testDecryption() {
        IdeaKey key = new IdeaKey();
        key.setK(0, 0x001);
        key.setK(1, 0x002);
        key.setK(2, 0x003);
        key.setK(3, 0x004);
        key.setK(4, 0x005);
        key.setK(5, 0x006);
        key.setK(6, 0x007);
        key.setK(7, 0x008);
        Idea idea = new Idea(key);

        IdeaBlock block = new IdeaBlock(0x0000_0001, 0x0002_0003);
        IdeaBlock result = idea.encrypt(block);
        IdeaBlock decrypted = idea.decrypt(result);

        Assert.assertEquals("[00 00 00 01 00 02 00 03]", decrypted.toHexString());
    }

    @Test
    public void testEncryption2() {
        IdeaKey key = new IdeaKey();
        key.setK(0, 0xA01);
        key.setK(1, 0x002);
        key.setK(2, 0x003);
        key.setK(3, 0x004);
        key.setK(4, 0x005);
        key.setK(5, 0xB06);
        key.setK(6, 0x010);
        key.setK(7, 0x00c);
        Idea idea = new Idea(key);

        IdeaBlock block = new IdeaBlock(0x414C_4100, 0x0000_0000);
        IdeaBlock result = idea.encrypt(block);
        IdeaBlock decrypted = idea.decrypt(result);

        Assert.assertEquals("[41 4c 41 00 00 00 00 00]", decrypted.toHexString());
    }

    @Test
    public void testEncryption3() {
        IdeaKey key = new IdeaKey();
        key.setK(0, 0x050c);
        key.setK(1, 0x0a0b);
        key.setK(2, 0x00f0);
        key.setK(3, 0x0e00);
        key.setK(4, 0x0501);
        key.setK(5, 0x0103);
        key.setK(6, 0x010d);
        key.setK(7, 0x00cd);
        Idea idea = new Idea(key);

        IdeaBlock block = new IdeaBlock(0x414C_4100, 0x0000_0000);
        IdeaBlock result = idea.encrypt(block);
        IdeaBlock decrypted = idea.decrypt(result);

        Assert.assertEquals("[41 4c 41 00 00 00 00 00]", decrypted.toHexString());
    }
}

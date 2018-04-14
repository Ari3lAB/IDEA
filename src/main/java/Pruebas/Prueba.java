package Pruebas;

import bits.BitArray;
import idea.Idea;
import idea.BloqueIdea;
import idea.IdeaKey;
import idea.ConversorStringAIdea;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal
 */
public class Prueba {

    public static void main(String[] args) {
        String stringToConvert = "sesentaycuatro1";

        IdeaKey key = new IdeaKey();
        key.setK(0, 0x050c);
        key.setK(1, 0x0a0b);
        key.setK(2, 0x00f0);
        key.setK(3, 0x0e00);
        key.setK(4, 0x0501);
        key.setK(5, 0x0103);
        key.setK(6, 0x010d);
        key.setK(7, 0x00cd);

        System.out.println("String de entrada: " + stringToConvert);
        System.out.println("Llave de entrada: " + key.toString());

        //Entradas
        Idea idea = new Idea(key);

        System.out.println("Bloques de entrada:");
        ConversorStringAIdea stringToIdeaBlockConverter = new ConversorStringAIdea();
        List<BloqueIdea> blocks = stringToIdeaBlockConverter.convert(stringToConvert);
        for (BloqueIdea block : blocks) {
            System.out.println(block.toHexString());
        }

        //Encriptado
        System.out.println("Bloques encriptados:");
        List<BloqueIdea> encryptedBlocks = new ArrayList<BloqueIdea>();
        for (BloqueIdea blockToEncrypt : blocks) {
            BloqueIdea encryptedBlock = idea.encrypt(blockToEncrypt);

            encryptedBlocks.add(encryptedBlock);
            System.out.println(encryptedBlock.toHexString() + " ");
        }

        //Desencriptado
        StringBuilder stringOutput = new StringBuilder();
        System.out.println("Bloques desencriptados:");
        for (BloqueIdea blockToDecrypt : encryptedBlocks) {
            BloqueIdea decryptedBlock = idea.decrypt(blockToDecrypt);

            System.out.println(decryptedBlock.toHexString());
            stringOutput.append(decryptedBlock.getBitArray().toASCII());
        }

        System.out.println("String Desencriptada: " + stringOutput.toString());
    }
}

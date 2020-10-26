package ex00;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Map<String, String> signatures = null;

        FileInputStream fileInputStream = null;

        FileOutputStream fileOutputStream = null;

        Scanner scanner = new Scanner(System.in);

        byte[] buff = new byte[32];

        String filePath;

        String checkSignature = "";

        boolean processed = false;

        try {
            signatures = readFileSignatures();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        try {
            fileOutputStream = new FileOutputStream("result.txt");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        while (true) {
            filePath = scanner.next();
            if (filePath.equals("42")) {
                break ;
            }
            try {
                fileInputStream = new FileInputStream(filePath);
                fileInputStream.read(buff);
                checkSignature = bytesToHex(buff).toUpperCase();
                for (Map.Entry<String, String> tmp : signatures.entrySet()) {
                    if (checkSignature.contains(tmp.getKey().toUpperCase())) {
                        System.out.println("PROCESSED");
                        processed = true;
                        fileOutputStream.write(tmp.getValue().getBytes());
                        fileOutputStream.write('\n');

                    }
                }
                if (!processed) {
                    System.out.println("UNDEFINED");
                }
                else {
                    processed = false;
                }
                fileInputStream.close();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
        }
    }

    private static Map<String, String> readFileSignatures() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("Signatures.txt");

        Map<String, String> signatures = new HashMap<>();

        String format = "";

        String sign = "";

        boolean wrSign = false;

        int i;

        while ((i = fileInputStream.read()) != -1) {
            if ((char)i == ',') {
                i = fileInputStream.read();
                wrSign = true;
            }
            if ((char)i == ' ') {
                i = fileInputStream.read();
            }
            if ((char)i == '\n') {
                signatures.put(sign, format);
                format = "";
                sign = "";
                i = fileInputStream.read();
                wrSign = false;
            }
            if (!wrSign) {
                format += (char)i;
            }
            else {
                sign += (char)i;
            }
        }

        fileInputStream.close();

        return signatures;
    }

    private static String bytesToHex(byte[] hashInBytes) {
        String sb = "";

        for (byte b : hashInBytes) {
            sb += String.format("%02x", b);
        }
        return sb;
    }
}

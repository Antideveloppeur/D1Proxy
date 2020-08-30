package me.antidev.d1proxy.network;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProxyCipher {
    private static final List<Character> zkArray = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_');

    public static String decodeAXK(String rawPacket) {
        String obfIpPort = rawPacket.substring(3);
        String obfIp = obfIpPort.substring(0, 8);
        String obfPort = obfIpPort.substring(8, 11);
        int ip = 0;
        for (int i = 0; i < 8; i++) {
            int pos = 4 * (7 - i);
            ip |= (((obfIp.charAt(i) - 48) & 15) << pos);
        }
        int port = 0;
        for (int i = 0; i < 3; i++) {
            int pos = 6 * (2 - i);
            port |= (decode64(obfPort.charAt(i)) & 63) << pos;
        }
        return int2ip(ip) + ":" + port;
    }

    @SuppressWarnings("unused")
    public static String encodeAXK(String ipPort) {
        int indx = ipPort.indexOf(':');
        int ip = ip2int(ipPort.substring(0, indx));
        int port = Integer.parseInt(ipPort.substring(indx + 1));

        char[] obfIp = new char[8];
        for (int i = 0; i < 8; i++) {
            int pos = 4 * i;
            obfIp[i % 2 == 0 ? i + 1 : i - 1] = (char) (((ip >> pos) & 15) + 48);
        }

        char[] obfPort = new char[3];
        for (int i = 0; i < 3; i++) {
            int pos = 6 * (2 - i);
            obfPort[i] = encode64((port >> pos) & 63);
        }

        return new String(obfIp) + new String(obfPort);
    }

    public static List<Integer> decodeGP(String[] packet, int teamId) {
        List<Integer> cells = new ArrayList<>();
        for (int i = 0; i < packet[teamId].length(); i += 2) {
            int cellId = zkArray.indexOf(packet[teamId].charAt(i)) << 6;
            cellId += zkArray.indexOf(packet[teamId].charAt(i + 1));
            cells.add(cellId);
        }
        return cells;
    }

    @SuppressWarnings("unused")
    public static LinkedHashMap<Integer, Integer> extractFullPath(String compressedData, int mapSize) {
        LinkedHashMap<Integer, Integer> fullPath = new LinkedHashMap<>();
        char[] data = compressedData.toCharArray();
        for (int i = 0; i < data.length; i = i + 3) {
            int cellId = (decode64((data[i + 1])) & 15) << 6 | decode64(data[i + 2]);
            int direction = decode64(data[i]);
            if (cellId < 0 || cellId > mapSize) return null;
            fullPath.put(cellId, direction);
        }
        return fullPath;
    }

    private static int decode64(char data) {
        return zkArray.indexOf(data);
    }

    private static char encode64(int data) {
        return zkArray.get(data);
    }

    private static String int2ip(int ip) {
        return IntStream.of(
                ip >> 24 & 0xff,
                ip >> 16 & 0xff,
                ip >> 8 & 0xff,
                ip & 0xff)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining("."));
    }

    private static int ip2int(String ip) {
        String[] parts = ip.split("\\.");
        int iip = 0;
        for (int i = 0; i < parts.length; i++) {
            iip |= Integer.parseInt(parts[i]) << (i * 8);
        }
        return iip;
    }
}

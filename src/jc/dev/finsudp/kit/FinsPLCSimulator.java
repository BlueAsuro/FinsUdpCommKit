package jc.dev.finsudp.kit;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

/**
 * FINS通信 PLC模拟�?
 * @author BlueAsuro
 *
 * 2018-2-8 下午6:40:31
 */
public class FinsPLCSimulator {
	public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
            
        }
        return d;
    }
	
	private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
	public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(9600);
        byte[] data = new byte[2048];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        System.out.println("PLC start...");
        while(true){
	        System.out.println("-------------------------------");
	        socket.receive(packet);
	        System.out.println("rcv:" + BytesUtil.bytesToHexString(data, packet.getLength()));
	        InetAddress address = packet.getAddress();
	        int port = 12000;
			
	        byte[] dataReadRet1 = {(byte)0xC0, 0x00, 0x02, 0x00, (byte)0xC0, 0x00, 0x00, 0x03, 0x00, 0x00, 0x01, 0x01, 0x00, 0x00, 0x45, 0x23};
	        byte[] dataReadRet2 = {(byte)0xC0, 0x00, 0x02, 0x00, (byte)0xC0, 0x00, 0x00, 0x03, 0x00, 0x00, 0x01, 0x01, 0x00, 0x00, 0x03, 0x23, 0x3E, 0x00};
	        byte[] dataReadRet6 = {(byte)0xC0, 0x00, 0x02, 0x00, (byte)0xC0, 0x00, 0x00, 0x03, 0x00, 0x00, 0x01, 0x01, 0x00, 0x00, 0x32, 0x31, 0x34, 0x33, 0x36, 0x35, 0x38, 0x37, 0x30, 0x39, 0x42, 0x41};
	        byte[] dataWriteRet = {(byte)0xC0, 0x00, 0x02, 0x00, (byte)0xC0, 0x00, 0x00, 0x03, 0x00, 0x00, 0x01, 0x02, 0x00, 0x00};
	        // 80000200030000C000000101820064000001
	        if (data[11] == 0x01){
	        	if (data[17] == 0x01){
	        		int a = 0;
	        		if (data[14] == (byte)0xBA && data[13] == (byte)0x78){
	        			a = new Random().nextInt(2);
	        		}
	        		else if (data[14] == (byte)0xC2 && data[13] == (byte)0x78){
	        			a = new Random().nextInt(4) + 1;
	        		}
	        		else {
	        			a = new Random().nextInt();
	        		}
	        		dataReadRet1[14] = (byte)((a&0x0000ff00) >> 8);
        			dataReadRet1[15] = (byte)(a&0x000000ff);
	        		DatagramPacket packetRead = new DatagramPacket(dataReadRet1, dataReadRet1.length, address, port);
		        	socket.send(packetRead);
		        	System.out.println("send:" + BytesUtil.bytesToHexString(dataReadRet1, dataReadRet1.length));
	        	}
	        	else if (data[17] == 0x02){
	    	        float a = (float)Math.random();
	    			int b = Float.floatToIntBits(a);
	    			dataReadRet2[14] = (byte)((b&0x0000ff00) >> 8);
	    			dataReadRet2[15] = (byte)(b&0x000000ff);
	    			dataReadRet2[16] = (byte)((b&0xff000000) >> 24);
	    			dataReadRet2[17] = (byte)((b&0x00ff0000) >> 16);
	        		DatagramPacket packetRead = new DatagramPacket(dataReadRet2, dataReadRet2.length, address, port);
		        	socket.send(packetRead);
		        	System.out.println("send:" + BytesUtil.bytesToHexString(dataReadRet2, dataReadRet2.length));
	        	}
	        	else if (data[17] == 0x06){
	        		DatagramPacket packetRead = new DatagramPacket(dataReadRet6, dataReadRet6.length, address, port);
		        	socket.send(packetRead);
		        	System.out.println("send:"+ BytesUtil.bytesToHexString(dataReadRet6, dataReadRet6.length));
	        	}
	        }
	        else if (data[11] == 0x02){
	        	DatagramPacket packetWrite = new DatagramPacket(dataWriteRet, dataWriteRet.length, address, port);
	        	socket.send(packetWrite);
	        	System.out.println("send:" + BytesUtil.bytesToHexString(dataWriteRet, dataWriteRet.length));
	        }
	        else {
	        	DatagramPacket packetWrite = new DatagramPacket(dataWriteRet, dataWriteRet.length, address, port);
	        	socket.send(packetWrite);
	        }
	        
        }
	}
}

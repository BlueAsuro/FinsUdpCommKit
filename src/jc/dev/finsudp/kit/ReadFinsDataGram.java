package jc.dev.finsudp.kit;


import java.io.IOException;
/**
 * FINS读数据数据包
 * @author BlueAsuro
 *
 * 2018-2-8 下午5:41:01
 */
public class ReadFinsDataGram extends FinsDataGram {
	public ReadFinsDataGram(String address, int numOfItems, int node, int pcNode) {
		super();
		// 80000200030000C000000101820064000001
		FinsHead fh = new FinsHead((byte)0x80, (byte)0x00, (byte)0x02, (byte)0x00, (byte)(node&0x00ff), (byte)0x00, (byte)0x00, (byte)(pcNode&0x00ff), (byte)0x00, (byte)0x00);
		FinsCmd fc = new FinsCmd((byte)0x01, (byte)0x01);
		int memAreaCode = this.getMemAreaNum(address.substring(0, 1));
		int beginningAddress = Integer.parseInt(address.substring(1, address.length()));
		
		byte[] data = {(byte)memAreaCode, (byte)((beginningAddress&0xff00)>>8), (byte)(beginningAddress&0x00ff), 0x00, (byte)((numOfItems&0xff00)>>8), (byte)(numOfItems&0x00ff)};
		this.setFinsHead(fh);
		this.setFinsCmd(fc);
		this.setFinsParaOrData(data);
	}
	
	public static void main(String args[]){
		ReadFinsDataGram rfdg = new  ReadFinsDataGram("D100", 1, 3, 192);
		System.out.println(rfdg);
		UDPSocket udpSocket = new UDPSocket();
		udpSocket.initialize("127.0.0.1", 9600, 10000, 10000);
		try {
			byte[] retData = udpSocket.sendMsg(rfdg.getFinsDataGramBytes(), 1000);
			System.out.println(BytesUtil.bytesToHexString(retData));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

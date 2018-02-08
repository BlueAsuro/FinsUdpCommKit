package jc.dev.finsudp.kit;


import java.io.IOException;
/**
 * FINS读数据结果数据包
 * @author BlueAsuro
 *
 * 2018-2-8 下午5:42:23
 */
public class ReadRcvFinsDataGram extends FinsDataGram {
	
	public ReadRcvFinsDataGram(byte[] retData) {
		super(retData);
	}
	
	public int getReadDataInt() {
		int retData = 0;
		int a = this.getFinsParaOrData()[2];
		a = (this.getFinsParaOrData()[2] << 8) & 0xff00;
		int b = this.getFinsParaOrData()[3];
		b = b & 0x00ff;
		retData = a + b;
		return retData;
	}
	
	public float getReadDataFloat() {
		float retData = 0;
		if (this.getFinsParaOrData().length >= 6){
			int c = (this.getFinsParaOrData()[4] << 24) & 0xff000000;
			int d = (this.getFinsParaOrData()[5] << 16) & 0x00ff0000;
			int a = (this.getFinsParaOrData()[2] << 8) & 0x0000ff00;
			int b = (this.getFinsParaOrData()[3]) & 0xff000000;
			retData = Float.intBitsToFloat(a + b + c + d);
		}
		return retData;
	}
	
	public String getReadDataString() {
		byte[] data = getReadData();
		byte[] retData = new byte[data.length];
		for (int i=0; i<data.length-1; i=i+2){
			retData[i] = data[i+1];
			retData[i+1] = data[i];
		}
		return new String(retData);
	}
	
	public byte[] getReadData() {
		byte[] retData = new byte[this.getFinsParaOrData().length - 2];
		System.arraycopy(this.getFinsParaOrData(), 2, retData, 0, this.getFinsParaOrData().length - 2); 
		return retData;
	}
	
	public int[] getReadData2() {
		int[] retData = new int[this.getFinsParaOrData().length / 2 - 1];
		if (this.getFinsParaOrData().length >= 4){
			for (int i=0; i<this.getFinsParaOrData().length / 2 - 1; i++){
				int a = (this.getFinsParaOrData()[2 + 2 * i] << 8) & 0xff00;
				int b = this.getFinsParaOrData()[2 + 2 * i + 1] & 0x00ff;
				retData[i] = a + b;
			}
		}
		return retData;
	}
	
	public static void main(String args[]){
		UDPSocket udpSocket = new UDPSocket();
		udpSocket.initialize("127.0.0.1", 9600, 10000, 10000);
		try {
			ReadFinsDataGram rfdg = new  ReadFinsDataGram("D100", 1, 1, 1);
			System.out.println(rfdg);
			byte[] retData = udpSocket.sendMsg(rfdg.getFinsDataGramBytes(), 1000);
			ReadRcvFinsDataGram rrfdg = new ReadRcvFinsDataGram(retData);
			for (int i=0; i<rrfdg.getReadData2().length; i++)
			    System.out.println("data Rcv:" + rrfdg.getReadData2()[i]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

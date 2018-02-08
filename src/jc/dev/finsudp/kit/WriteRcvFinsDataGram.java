package jc.dev.finsudp.kit;


import java.io.IOException;
/**
 * FINS写数据返回数据包
 * @author BlueAsuro
 *
 * 2018-2-8 下午5:42:32
 */
public class WriteRcvFinsDataGram extends FinsDataGram {

	public WriteRcvFinsDataGram(byte[] retData) {
		super(retData);
	}
	
	public int getWriteResult() {
		int retData = 0;
		int a = this.getFinsParaOrData()[0];
		a = (this.getFinsParaOrData()[0] << 8) & 0xff00;
		int b = this.getFinsParaOrData()[1];
		b = b & 0x00ff;
		retData = a + b;
		return retData;
	}
	
	public static void main(String args[]){
		UDPSocket udpSocket = new UDPSocket();
		udpSocket.initialize("127.0.0.1", 9600, 10000, 10000);
		try {
			WriteFinsDataGram wfdg = new  WriteFinsDataGram("W005", (byte)1, 1, 1);
			System.out.println(wfdg);
			byte[] retData = udpSocket.sendMsg(wfdg.getFinsDataGramBytes(), 1000);
			WriteRcvFinsDataGram wrfdg = new WriteRcvFinsDataGram(retData);
			System.out.println("data Rcv:" + wrfdg.getWriteResult());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

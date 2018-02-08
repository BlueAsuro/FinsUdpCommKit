package jc.dev.finsudp.kit;

/**
 * FINS写数据结果数据包
 * @author BlueAsuro
 *
 * 2018-2-8 下午5:42:09
 */
public class WriteFinsDataGram extends FinsDataGram {
	
	public WriteFinsDataGram(String address, int data, int node, int pcNode) {
		FinsHead fh = new FinsHead((byte)0x80, (byte)0x00, (byte)0x02, (byte)0x00, (byte)(node&0x00ff), (byte)0x00, (byte)0x00, (byte)(pcNode&0x00ff), (byte)0x00, (byte)0x00);
		FinsCmd fc = new FinsCmd((byte)0x01, (byte)0x02);
		int memAreaCode = this.getMemAreaNum(address.substring(0, 1));
		int beginningAddress = Integer.parseInt(address.substring(1, address.length()));
		
		byte[] datatemp = {(byte)memAreaCode, (byte)((beginningAddress&0xff00)>>8), (byte)(beginningAddress&0x00ff)
				, (byte)0x00, (byte)0x00
				, (byte)(0x01)
				, (byte)((data&0xff00)>>8)
				, (byte)(data&0x00ff)};
		this.setFinsHead(fh);
		this.setFinsCmd(fc);
		this.setFinsParaOrData(datatemp);
	}

	public WriteFinsDataGram(String address, float data, int node, int pcNode) {
		// 80000200030000C000000101820064000001
		FinsHead fh = new FinsHead((byte)0x80, (byte)0x00, (byte)0x02, (byte)0x00, (byte)(node&0x00ff), (byte)0x00, (byte)0x00, (byte)(pcNode&0x00ff), (byte)0x00, (byte)0x00);
		FinsCmd fc = new FinsCmd((byte)0x01, (byte)0x02);
		int memAreaCode = this.getMemAreaNum(address.substring(0, 1));
		int beginningAddress = Integer.parseInt(address.substring(1, address.length()));
		int floatBits = Float.floatToIntBits(data);
		byte[] datatemp = {(byte)memAreaCode, (byte)((beginningAddress&0xff00)>>8), (byte)(beginningAddress&0x00ff)
				, (byte)0x00, (byte)0x00
				, (byte)(0x02)
				, (byte)((floatBits&0x0000ff00)>>8)
				, (byte)((floatBits&0x000000ff))
				, (byte)((floatBits&0xff000000)>>24)
				, (byte)((floatBits&0x00ff0000)>>16)};
		this.setFinsHead(fh);
		this.setFinsCmd(fc);
		this.setFinsParaOrData(datatemp);
	}
	
	public WriteFinsDataGram(String address, byte[] data, int node, int pcNode) {
		// 80000200030000C000000101820064000001
		FinsHead fh = new FinsHead((byte)0x80, (byte)0x00, (byte)0x02, (byte)0x00, (byte)(node&0x00ff), (byte)0x00, (byte)0x00, (byte)(pcNode&0x00ff), (byte)0x00, (byte)0x00);
		FinsCmd fc = new FinsCmd((byte)0x01, (byte)0x02);
		int memAreaCode = this.getMemAreaNum(address.substring(0, 1));
		int beginningAddress = Integer.parseInt(address.substring(1, address.length()));
		byte[] datatemp = {(byte)memAreaCode, (byte)((beginningAddress&0xff00)>>8), (byte)(beginningAddress&0x00ff), 0x00, (byte)((data.length&0xff00)>>8), (byte)(data.length&0x00ff)};
		this.setFinsHead(fh);
		this.setFinsCmd(fc);
		this.setFinsParaOrData(BytesUtil.concatenateBytes(datatemp, data));
	}

}

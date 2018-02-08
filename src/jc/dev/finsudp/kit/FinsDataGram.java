package jc.dev.finsudp.kit;


import java.util.HashMap;
import java.util.Map;
/**
 * FINS数据包封装类
 * @author BlueAsuro
 *
 * 2018-2-8 下午5:36:32
 */
public class FinsDataGram {
	private FinsHead finsHead;
	private FinsCmd finsCmd;
	private byte[] finsParaOrData;
	/**
	 * FINS 地址段代码查找表
	 */
	private Map<String, Integer> memAreaLookup = new HashMap<String, Integer>();
	
	public void init(){
		this.addinLookup("D", 0x82);
		this.addinLookup("W", 0x31);
	}
	
	public FinsDataGram() {
		init();
	}
	
	public void addinLookup(String code, int number) {
		this.memAreaLookup.put(code, number);
	}
	
	public int getMemAreaNum(String code) {
		return this.memAreaLookup.get(code);
	}
	
	public FinsDataGram(FinsHead finsHead, FinsCmd finsCmd,
			byte[] finsParaOrData) {
		init();
		this.finsHead = finsHead;
		this.finsCmd = finsCmd;
		this.finsParaOrData = finsParaOrData;
	}
	
	public FinsDataGram(byte[] data) {
		init();
		this.finsHead = new FinsHead(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9]);
		this.finsCmd = new FinsCmd(data[10], data[11]);
		this.finsParaOrData = new byte[data.length - 12];
		System.arraycopy(data, 12, this.finsParaOrData, 0, data.length - 12);
	}
	
	public String toString(){
		return BytesUtil.bytesToHexString(getFinsDataGramBytes());
	}

	public byte[] getFinsDataGramBytes(){
		byte[] finsDataGramBytes;
		finsDataGramBytes = BytesUtil.concatenateBytes(this.finsHead.getFinsHeadBytes(), this.finsCmd.getFinsCmdBytes());
		finsDataGramBytes = BytesUtil.concatenateBytes(finsDataGramBytes, this.getFinsParaOrData());
		return finsDataGramBytes;
	}
	
	public FinsHead getFinsHead() {
		return finsHead;
	}

	public void setFinsHead(FinsHead finsHead) {
		this.finsHead = finsHead;
	}

	public FinsCmd getFinsCmd() {
		return finsCmd;
	}

	public void setFinsCmd(FinsCmd finsCmd) {
		this.finsCmd = finsCmd;
	}

	public byte[] getFinsParaOrData() {
		return finsParaOrData;
	}

	public void setFinsParaOrData(byte[] finsParaOrData) {
		this.finsParaOrData = finsParaOrData;
	}

	public class FinsHead {
		public FinsHead(byte iCF, byte rSV, byte gCT, byte dNA, byte dA1,
				byte dA2, byte sNA, byte sA1, byte sA2, byte sID) {
			ICF = iCF;
			RSV = rSV;
			GCT = gCT;
			DNA = dNA;
			DA1 = dA1;
			DA2 = dA2;
			SNA = sNA;
			SA1 = sA1;
			SA2 = sA2;
			SID = sID;
		}
		
		public byte[] getFinsHeadBytes() {
			byte[] finsHeadByteArray = {ICF, RSV, GCT, DNA, DA1, DA2, SNA, SA1, SA2, SID};
			return finsHeadByteArray;
		}
		
		public String toString(){
			return BytesUtil.bytesToHexString(getFinsHeadBytes());
		}
		
		private byte ICF = 0x00;
		private byte RSV = 0x00;
		private byte GCT = 0x00;
		private byte DNA = 0x00;
		private byte DA1 = 0x00;
		private byte DA2 = 0x00;
		private byte SNA = 0x00;
		private byte SA1 = 0x00;
		private byte SA2 = 0x00;
		private byte SID = 0x00;
		
		public byte getICF() {
			return ICF;
		}
		public void setICF(byte iCF) {
			ICF = iCF;
		}
		public byte getRSV() {
			return RSV;
		}
		public void setRSV(byte rSV) {
			RSV = rSV;
		}
		public byte getGCT() {
			return GCT;
		}
		public void setGCT(byte gCT) {
			GCT = gCT;
		}
		public byte getDNA() {
			return DNA;
		}
		public void setDNA(byte dNA) {
			DNA = dNA;
		}
		public byte getDA1() {
			return DA1;
		}
		public void setDA1(byte dA1) {
			DA1 = dA1;
		}
		public byte getDA2() {
			return DA2;
		}
		public void setDA2(byte dA2) {
			DA2 = dA2;
		}
		public byte getSNA() {
			return SNA;
		}
		public void setSNA(byte sNA) {
			SNA = sNA;
		}
		public byte getSA1() {
			return SA1;
		}
		public void setSA1(byte sA1) {
			SA1 = sA1;
		}
		public byte getSA2() {
			return SA2;
		}
		public void setSA2(byte sA2) {
			SA2 = sA2;
		}
		public byte getSID() {
			return SID;
		}
		public void setSID(byte sID) {
			SID = sID;
		}
	}
	
	public class FinsCmd {
		public FinsCmd(byte mRC, byte sRC) {
			MRC = mRC;
			SRC = sRC;
		}
		public byte[] getFinsCmdBytes() {
			byte[] finsCmdByteArray = {MRC, SRC};
			return finsCmdByteArray;
		}
		public String toString(){
			return BytesUtil.bytesToHexString(getFinsCmdBytes());
		}
		
		private byte MRC = 0x00;
		private byte SRC = 0x00;
		public byte getMRC() {
			return MRC;
		}
		public void setMRC(byte mRC) {
			MRC = mRC;
		}
		public byte getSRC() {
			return SRC;
		}
		public void setSRC(byte sRC) {
			SRC = sRC;
		}
	}
}

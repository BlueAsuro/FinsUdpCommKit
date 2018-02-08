package jc.dev.finsudp.kit;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

/**
 * FINS 以UDP 方式与PLC通信工具�?
 * @author BlueAsuro
 *
 * 2018-2-8 下午5:54:48
 */
public class FinsCommUDPSocket extends UDPSocket implements FinsCommInter{
	/**
	 * PLC节点
	 */
	private int node;
	
	/**
	 * 电脑端接�?
	 */
	private int pcNode;
	
	/**
	 * 初始�?
	 * @param ip PLC IP
	 * @param port PlC 端口
	 * @param pcPort PC 端口
	 * @param node PLC 节点
	 * @param pcNode PC 节点
	 */
	public void init(String ip, int port, int pcPort, int node, int pcNode){
		this.host = ip;
		this.port = port;
		this.node = node;
		this.localPort = pcPort;
		this.pcNode = pcNode;
		this.initialize(ip, port, pcPort, 2000);
	}
	
	public DataResult readInt(String address) {
		DataResult dr = new DataResult();
		try {
			ReadFinsDataGram rfdg = new ReadFinsDataGram(address, 1, this.node, this.pcNode);
			byte[] retData = this.sendMsg(rfdg.getFinsDataGramBytes(), 20);
			ReadRcvFinsDataGram rrfdg = new ReadRcvFinsDataGram(retData);
			dr.setRet(0);
			dr.setIdata(rrfdg.getReadDataInt());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dr;
	}
	
	public DataResult readFloat(String address) {
		DataResult dr = new DataResult();
		try {
			ReadFinsDataGram rfdg = new ReadFinsDataGram(address, 2, this.node, this.pcNode);
			byte[] retData = this.sendMsg(rfdg.getFinsDataGramBytes(), 20);
			ReadRcvFinsDataGram rrfdg = new ReadRcvFinsDataGram(retData);
			dr.setRet(0);
			dr.setFdata(rrfdg.getReadDataFloat());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dr;
	}

	public DataResult read(String address, int numOfItems) {
		DataResult dr = new DataResult();
		try {
			ReadFinsDataGram rfdg = new ReadFinsDataGram(address, numOfItems, this.node, this.pcNode);
			byte[] retData = this.sendMsg(rfdg.getFinsDataGramBytes(), 20);
			ReadRcvFinsDataGram rrfdg = new ReadRcvFinsDataGram(retData);
			dr.setRet(0);
			dr.setByarrdata(rrfdg.getReadData());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dr;
	}
	
	public DataResult read2(String address, int numOfItems) {
		DataResult dr = new DataResult();
		try {
			ReadFinsDataGram rfdg = new ReadFinsDataGram(address, numOfItems, this.node, this.pcNode);
			byte[] retData = this.sendMsg(rfdg.getFinsDataGramBytes(), 20);
			ReadRcvFinsDataGram rrfdg = new ReadRcvFinsDataGram(retData);
			dr.setRet(0);
			dr.setIarrdata(rrfdg.getReadData2());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dr;
	}
	
	public DataResult readString(String address, int numOfItems) {
		DataResult dr = new DataResult();
		try {
			ReadFinsDataGram rfdg = new ReadFinsDataGram(address, numOfItems, this.node, this.pcNode);
			byte[] retData = this.sendMsg(rfdg.getFinsDataGramBytes(), 20);
			ReadRcvFinsDataGram rrfdg = new ReadRcvFinsDataGram(retData);
			dr.setRet(0);
			dr.setStrdata(rrfdg.getReadDataString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dr;
	}

	public int writeInt(String address, int data) {
		int retdata = 0;
		try {
			WriteFinsDataGram wfdg = new WriteFinsDataGram(address, (int)data, this.node, this.pcNode);
			System.out.println(BytesUtil.bytesToHexString(wfdg.getFinsDataGramBytes()));
			byte[] retData = this.sendMsg(wfdg.getFinsDataGramBytes(), 20);
			WriteRcvFinsDataGram wrfdg = new WriteRcvFinsDataGram(retData);
			retdata = wrfdg.getWriteResult();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retdata;
	}
	
	public int writeFloat(String address, float data) {
		int retdata = 0;
		try {
			WriteFinsDataGram wfdg = new WriteFinsDataGram(address, (float)data, this.node, this.pcNode);
			System.out.println(BytesUtil.bytesToHexString(wfdg.getFinsDataGramBytes()));
			byte[] retData = this.sendMsg(wfdg.getFinsDataGramBytes(), 20);
			WriteRcvFinsDataGram wrfdg = new WriteRcvFinsDataGram(retData);
			retdata = wrfdg.getWriteResult();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retdata;
	}
	
	public int write(String address, byte data) {
		int retdata = 0;
		try {
			WriteFinsDataGram wfdg = new WriteFinsDataGram(address, (byte)data, this.node, this.pcNode);
			System.out.println(BytesUtil.bytesToHexString(wfdg.getFinsDataGramBytes()));
			byte[] retData = this.sendMsg(wfdg.getFinsDataGramBytes(), 20);
			WriteRcvFinsDataGram wrfdg = new WriteRcvFinsDataGram(retData);
			retdata = wrfdg.getWriteResult();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retdata;
	}

	public int write(String address, byte[] data) {
		int retdata = 0;
		try {
			WriteFinsDataGram wfdg = new WriteFinsDataGram(address, data, this.node, this.pcNode);
			System.out.println(BytesUtil.bytesToHexString(wfdg.getFinsDataGramBytes()));
			byte[] retData = this.sendMsg(wfdg.getFinsDataGramBytes(), 20);
			WriteRcvFinsDataGram wrfdg = new WriteRcvFinsDataGram(retData);
			retdata = wrfdg.getWriteResult();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retdata;
	}

	public static void main(String args[]){
		final FinsCommUDPSocket finsCommUDPSocket = new FinsCommUDPSocket();
		finsCommUDPSocket.init("127.0.0.1", 9600, 12000, 3, 192);
		System.out.println(finsCommUDPSocket.readString("D30900", 6).getStrdata());
		System.out.println(finsCommUDPSocket.readInt("D30906").getIdata());
		System.out.println(finsCommUDPSocket.readFloat("D30910").getFdata());
	}
}

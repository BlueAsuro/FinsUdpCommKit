package jc.dev.finsudp.kit;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * UDPSocket
 * @author BlueAsuro
 *
 */
public class UDPSocket {
	/**
	 * 对端IP地址
	 */
	protected String host;
	
	/**
	 * 对端端口�?
	 */
	protected int port;
	
	/**
	 * 本地端口
	 */
	protected int localPort;
	
	/**
	 * 等待回复超时时间
	 */
	protected int timeOut;
	
	/**
	 * 是否初始�?
	 */
	private boolean initialized;
	
	/**
	 * UPDSocket
	 */
	private DatagramSocket datagramSocket;

	public UDPSocket() {
		this.initialized = false;
	}
	
	public void initialize(String host, int port, int localPort, int timeOut) {
		try {
			this.host = host;
			this.port = port;
			this.localPort = localPort;
			this.timeOut = timeOut;
			this.datagramSocket = new DatagramSocket(this.localPort);
			this.initialized = true;
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		this.initialized = false;
		if (datagramSocket != null)
			datagramSocket.close();
	}

	public byte[] sendMsg(byte[] data, int rcvlen) throws IOException{
		if (initialized){
			datagramSocket.setSoTimeout(this.timeOut);
	        DatagramPacket datagramPacketSend = new DatagramPacket(data, data.length, InetAddress.getByName(this.host), this.port);
	        datagramSocket.send(datagramPacketSend);
	        byte[] databuff = new byte[2048];
	        DatagramPacket datagramPacketRcv = new DatagramPacket(databuff, databuff.length);
	        datagramSocket.receive(datagramPacketRcv);
	        byte[] rcvdata = new byte[datagramPacketRcv.getLength()];
	        System.arraycopy(databuff, 0, rcvdata, 0, datagramPacketRcv.getLength());
	        return rcvdata;
		}
		return null;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getLocalPort() {
		return localPort;
	}

	public void setLocalPort(int localPort) {
		this.localPort = localPort;
	}
	
	public int getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	public boolean isInitialized() {
		return initialized;
	}

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	public DatagramSocket getDatagramSocket() {
		return datagramSocket;
	}

	public void setDatagramSocket(DatagramSocket datagramSocket) {
		this.datagramSocket = datagramSocket;
	}

	public static void main(String args[]){
		UDPSocket dss = new UDPSocket();
		dss.initialize("172.16.2.12", 9600, 11000, 10000);
		byte[] data = {0x33, 0x32, 0x42, 0x35};
		try {
			System.out.println("begin send data...");
			byte[] dataRcv = dss.sendMsg(data, 4);
			System.out.println(BytesUtil.bytesToHexString(dataRcv));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

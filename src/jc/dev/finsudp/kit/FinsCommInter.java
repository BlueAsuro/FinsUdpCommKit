package jc.dev.finsudp.kit;


/**
 * FINS 通讯接口
 * @author BlueAsuro
 *
 */
public interface FinsCommInter {
	/**
	 * FINS 通讯接口初始�?
	 * @param ip PLC ip地址
	 * @param port PLC 端口
	 * @param pcPort 本地监听端口
	 * @param node PLC节点�?
	 * @param pcNode 本地节点�?
	 */
	public void init(String ip, int port, int pcPort, int node, int pcNode);
	
	/**
	 * 读取2字节（字）整数类型数�?
	 * @param address 地址
	 * @return
	 */
	public DataResult readInt(String address);
	
	/**
	 * 读取浮点数类型数�?
	 * @param address 地址
	 * @return
	 */
	public DataResult readFloat(String address);
	
	/**
	 * 读取字符类型数据
	 * @param address 地址
	 * @param numOfItems 读取数据长度
	 * @return
	 */
	public DataResult readString(String address, int numOfItems);
	
	/**
	 * 读取字节数组类型数据
	 * @param address 地址
	 * @param numOfItems 读取数据长度
	 * @return
	 */
	public DataResult read(String address, int numOfItems);
	
	/**
	 * 读取双字节（字）数组类型数据
	 * @param address 地址
	 * @param numOfItems 读取数据长度
	 * @return
	 */
	public DataResult read2(String address, int numOfItems);
	
	/**
	 * 写入2字节（字）类型数�?
	 * @param address 地址
	 * @param data 2字节数据
	 * @return
	 */
	public int writeInt(String address, int data);
	
	/**
	 * 写入浮点数类型数�?
	 * @param address 地址
	 * @param data 浮点数据
	 * @return
	 */
	public int writeFloat(String address, float data);
	
	/**
	 * 写入字节类型数据
	 * @param address 地址
	 * @param data 字节数据
	 * @return
	 */
	public int write(String address, byte data);
	
	/**
	 * 写入字节数组类型数据
	 * @param address 地址
	 * @param data 字节数组数据
	 * @return
	 */
	public int write(String address, byte[] data);
}

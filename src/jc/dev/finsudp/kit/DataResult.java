package jc.dev.finsudp.kit;


public class DataResult {
	private int ret = -1;
	private String strdata;
	private int idata;
	private float fdata;
	private byte bydata;
	private byte[] byarrdata;
	private int[] iarrdata;
	
	public int getRet() {
		return ret;
	}
	public void setRet(int ret) {
		this.ret = ret;
	}
	public int getIdata() {
		return idata;
	}
	public void setIdata(int idata) {
		this.idata = idata;
	}
	public float getFdata() {
		return fdata;
	}
	public void setFdata(float fdata) {
		this.fdata = fdata;
	}
	public byte getBydata() {
		return bydata;
	}
	public void setBydata(byte bydata) {
		this.bydata = bydata;
	}
	public byte[] getByarrdata() {
		return byarrdata;
	}
	public void setByarrdata(byte[] byarrdata) {
		this.byarrdata = byarrdata;
	}
	public int[] getIarrdata() {
		return iarrdata;
	}
	public void setIarrdata(int[] iarrdata) {
		this.iarrdata = iarrdata;
	}
	public String getStrdata() {
		return strdata;
	}
	public void setStrdata(String strdata) {
		this.strdata = strdata;
	}
}

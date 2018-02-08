package jc.dev.finsudp.kit;


public class BytesUtil {
	public static final String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			//sb.append("0x");
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
			//sb.append(" ");
		}
		return sb.toString();
	}
	
	public static final String bytesToHexString(int[] iArray) {
		StringBuffer sb = new StringBuffer(iArray.length);
		String sTemp;
		for (int i = 0; i < iArray.length; i++) {
			sTemp = Integer.toHexString((0xff00 & iArray[i]) >> 8);
			//sb.append("0x");
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
			
			sTemp = Integer.toHexString(0xff & iArray[i]);
			//sb.append("0x");
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());

			sb.append(" ");
		}
		return sb.toString();
	}
	
	public static final String bytesToHexString(byte[] bArray, int length) {
		StringBuffer sb = new StringBuffer(length);
		String sTemp;
		for (int i = 0; i < length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			//sb.append("0x");
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
			//sb.append(" ");
		}
		return sb.toString();
	}
	
	public static final byte[] concatenateBytes(byte[] a, byte[] b) {
	    byte[] result = new byte[a.length + b.length]; 
	    System.arraycopy(a, 0, result, 0, a.length); 
	    System.arraycopy(b, 0, result, a.length, b.length); 
	    return result;
	} 
}

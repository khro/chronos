package site.chronos.utils.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

/**
 * @description Hessian2 序列化 适配
 * @author wulg
 * @Date   2015年9月14日
 */
public class Hessian2Serializer implements Serializer{
	
	@Override
	public byte[] serialize(Object obj){
		if(obj == null){
			return null;
		}
		if (!(obj instanceof Serializable)) {
			throw new IllegalArgumentException("object of type [" + obj.getClass().getName() + "] is not serializable");
		}
		ByteArrayOutputStream os = null;
		Hessian2Output ho = null;
	    try {
	    	os = new ByteArrayOutputStream();  
	    	ho = new Hessian2Output(os);  
			ho.writeObject(obj);
			ho.flush();
			return os.toByteArray();  
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(null != ho){
					ho.close();
				}
				if(null != os){
					os.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Object deserialize(byte[] bytes) {
		 if(bytes == null){
			return null;
		 }
		 ByteArrayInputStream is = null;
		 Hessian2Input hi = null;
		 try {
		    is = new ByteArrayInputStream(bytes);  
		    hi = new Hessian2Input(is);  
		    return hi.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(null != hi){
					hi.close();
				}
				if(null != is){
					is.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}  
		return null;
	}
}

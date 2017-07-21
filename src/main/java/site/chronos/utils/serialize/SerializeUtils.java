package site.chronos.utils.serialize;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description 序列化工具类
 * @author wulg
 * @Date   2015年8月14日
 */
public class SerializeUtils {
	
	public static Serializer serializer = new Hessian2Serializer();
	
//	static{
//		if(serializer == null){
//			serializer= SerializerFactory.createSerializer(SerializeStyle.HESSIAN2);
//		}
//	}
	
	public static byte[] serialize(Object obj) {
		return serializer.serialize(obj);
	}

	public static Object deserialize(byte[] bytes) {
		return serializer.deserialize(bytes);
	}
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("123");
		list.add("456");
		
		//byte[] bytes = serialize(list);
		long startTime = new Date().getTime();
		for(int i =1 ; i<=100000;i++){
			serialize(list);
			//deserialize(bytes);
		}
		long endTime = new Date().getTime();
		System.out.println(endTime-startTime);
	}
}

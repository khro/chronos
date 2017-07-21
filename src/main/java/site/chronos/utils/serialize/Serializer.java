package site.chronos.utils.serialize;

/**
 * @description 序列化接口
 * @author wulg
 * @Date   2015年8月14日
 */
public interface Serializer {
	
	byte[] serialize(Object obj);
	
	Object deserialize(byte[] bytes);
}

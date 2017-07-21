//package site.chronos.utils.dao;
//
//import site.chronos.utils.cache.CacheUtils;
//
///**
// * @Class RedisSelect
// * @Description Redis查询，30分钟过期
// * @Author JieHong
// * @Date 2016年5月6日 上午11:22:23
// * @param <T>
// */
//public abstract class RedisSelect<T> {
//
//	/**
//	 * @Title select
//	 * @Description 从DB获取结果
//	 * @Author JieHong
//	 * @Date 2016年5月6日 上午11:23:12
//	 * @return
//	 */
//	protected abstract T select();
//
//	/**
//	 * @Title handle
//	 * @Description 获取结果
//	 * @Author JieHong
//	 * @Date 2016年5月6日 上午11:23:46
//	 * @param redisKey
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public final T handle(String redisKey) {
//		T entity = null;
//		try{
//			String obj = CacheUtils.get(redisKey);
//			if(obj!=null){		
//				entity = (T) TransactionUtils.unSerialize(obj);
//			}
//		}catch(ClassCastException e){
//			entity = CacheUtils.get(redisKey);
//		}
//		
//		if (entity != null) {
//			return entity;
//		}
//		entity = select();
//		if (entity != null) {
//			// 30分钟过期
//			CacheUtils.set(redisKey, 1800, TransactionUtils.serialize(entity));
//		}
//		return entity;
//	}
//	
//	/**
//	 * 使用者传入过期时间
//	 * @param redisKey
//	 * @param expiredTime
//	 * @return 
//	 */
//	@SuppressWarnings("unchecked")
//	public final T handle(String redisKey,int expiredTime){
//		T entity = null;
//		try{
//			String obj = CacheUtils.get(redisKey);
//			if(obj!=null){		
//				entity = (T) TransactionUtils.unSerialize(obj);
//			}
//		}catch(ClassCastException e){
//			entity = CacheUtils.get(redisKey);
//		}
//		
//		if (entity != null) {
//			return entity;
//		}
//		entity = select();
//		if (entity != null) {
//			// 30分钟过期
//			CacheUtils.set(redisKey, expiredTime, TransactionUtils.serialize(entity));
//		}
//		return entity;
//	}
//	
//	/**
//	 * 使用者传入过期时间 在select内可以修改过期时间
//	 * @Author xw
//	 * @Date 2017年3月21日10:17:02
//	 * @param redisKey
//	 * @param expiredTime
//	 * @return 
//	 */
//	@SuppressWarnings("unchecked")
//	public final T handle(String redisKey,int[] expiredTime){
//		T entity = null;
//		try{
//			String obj = CacheUtils.get(redisKey);
//			if(obj!=null){		
//				entity = (T) TransactionUtils.unSerialize(obj);
//			}
//		}catch(ClassCastException e){
//			entity = CacheUtils.get(redisKey);
//		}
//		
//		if (entity != null) {
//			return entity;
//		}
//		entity = select();
//		if (entity != null) {
//			// 30分钟过期
//			CacheUtils.set(redisKey, expiredTime[0], TransactionUtils.serialize(entity));
//		}
//		return entity;
//	}
//}

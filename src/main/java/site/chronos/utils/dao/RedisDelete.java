//package site.chronos.utils.dao;
//
//import site.chronos.utils.cache.CacheUtils;
//
///**
// * @Class RedisDelete
// * @Description Redis删除
// * @Author JieHong
// * @Date 2016年5月6日 下午1:26:26
// */
//public abstract class RedisDelete {
//
//	/**
//	 * @Title delete
//	 * @Description 从DB删除
//	 * @Author JieHong	
//	 * @Date 2016年5月6日 下午1:26:04
//	 * @return
//	 */
//	protected abstract int delete();
//
//	/**
//	 * @Title handle
//	 * @Description 删除并验证返回结果条数与期望值的比较
//	 * @Author JieHong
//	 * @Date 2016年5月6日 上午11:32:11
//	 * @param redisKey
//	 * @param expect
//	 *            期望值
//	 * @param compare
//	 * @return
//	 */
//	public final int handle(String redisKey, int expect, Compare compare) {
//		CacheUtils.delete(redisKey);
//		int actual = delete();
//		Compare.validate(expect, compare, actual);
//		return actual;
//	}
//
//	/**
//	 * @Title handleEqOne
//	 * @Description 删除并验证返回结果条数是否为1
//	 * @Author JieHong
//	 * @Date 2016年5月6日 上午11:32:11
//	 * @param redisKey
//	 * @return
//	 */
//	public final int handleEqOne(String redisKey) {
//		return handle(redisKey, 1, Compare.EQ);
//	}
//}

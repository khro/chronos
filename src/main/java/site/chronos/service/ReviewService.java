package site.chronos.service;

import site.chronos.constant.Result;
import site.chronos.entity.Question;

public interface ReviewService {

	/**
	 * 提交审核信息
	 * @param reviewPage
	 * @return
	 */
	public Result addReview(Question question);
	
	/**
	 * 查询所有待审核
	 * @param reviewPage
	 * @return
	 */
	public Result selectReview(Integer status);
	
	/**
	 * 查询已删除审核信息
	 * @param reviewPage
	 * @return
	 */
	public Result selectReviewDel();
	

	/**
	 * 通过审核
	 * @param reviewPage
	 * @return
	 */
	public Result reviewOver(Integer reviewId , Integer status);

}

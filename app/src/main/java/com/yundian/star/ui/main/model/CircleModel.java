package com.yundian.star.ui.main.model;

import com.yundian.star.app.CommentConfig;
import com.yundian.star.been.ResultBeen;
import com.yundian.star.listener.IDataRequestListener;
import com.yundian.star.listener.OnAPIListener;
import com.yundian.star.networkapi.NetworkAPIFactoryImpl;
import com.yundian.star.utils.SharePrefUtil;


public class CircleModel {
	
	
	public CircleModel(){
		//
	}

	public void loadData(final IDataRequestListener listener){
		requestServer(listener);
	}
	
	public void deleteCircle( final IDataRequestListener listener) {
		requestServer(listener);

	}

	public void addFavort(String symbol, long circle_id, int uid, final IDataRequestListener listener) {
		NetworkAPIFactoryImpl.getInformationAPI().getPraisestar(symbol, circle_id, uid, new OnAPIListener<ResultBeen>() {
			@Override
			public void onError(Throwable ex) {

			}

			@Override
			public void onSuccess(ResultBeen resultBeen) {
				if (resultBeen.getResult()==1){
					requestServer(listener);
				}
			}
		});

	}

	public void deleteFavort(final IDataRequestListener listener) {
		requestServer(listener);
	}

	public void addComment(String content,CommentConfig config,final IDataRequestListener listener) {
		int type = -1 ;
		if (config.commentType==CommentConfig.Type.PUBLIC){
			type = 0;
		}else if (config.commentType==CommentConfig.Type.REPLY){
			type = 2 ;
		}
		NetworkAPIFactoryImpl.getInformationAPI().getUserAddComment(config.symbol_code, config.Circle_id,
				SharePrefUtil.getInstance().getUserId(), type, content, new OnAPIListener<ResultBeen>() {
					@Override
					public void onError(Throwable ex) {

					}

					@Override
					public void onSuccess(ResultBeen resultBeen) {
						if (resultBeen.getResult()==1){
							requestServer(listener);
						}
					}
				});

	}

	public void deleteComment( final IDataRequestListener listener) {
		requestServer(listener);
	}
	
	/**
	 * 
	* @Title: requestServer 
	* @Description: 与后台交互, 因为demo是本地数据，不做处理
	* @param  listener    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void requestServer(final IDataRequestListener listener) {
				listener.loadSuccess(1);
	}
}
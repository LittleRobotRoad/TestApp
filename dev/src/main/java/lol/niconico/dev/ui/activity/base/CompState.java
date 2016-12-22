package lol.niconico.dev.ui.activity.base;

/**
 * 状态
 * 
 *         EMPTY 没有数据
 *         EMPTY_INVALID_NEWWORK 网络连接不上，且没有数据
 *         EMPTY_REFRESHING 没有内容且正在刷星
 *         EMPTY_ERROR 没有内容且请求无效
 *         DATA 内容界面
 * 
 */
public enum CompState {
	EMPTY, EMPTY_INVALID_NEWWORK, EMPTY_REFRESHING, EMPTY_ERROR, DATA
}

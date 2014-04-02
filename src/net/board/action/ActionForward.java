package net.board.action;

public class ActionForward {
	private boolean isRedirect;// 리다이렉트 여부
	private String path;// 포워딩 경로
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}

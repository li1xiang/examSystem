package pub.util;

import java.util.List;


public class TreeCVO extends TreeVO {

	private String state;
	private List<TreeVO> children;
	
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state= state;
	}
	public List<TreeVO> getChildren() {
		return children;
	}
	public void setChildren(List<TreeVO> children) {
		this.children = children;
	}
	
}

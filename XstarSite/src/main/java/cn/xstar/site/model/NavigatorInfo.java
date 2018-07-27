package cn.xstar.site.model;

public class NavigatorInfo {
	public NavigatorInfo(String path, String name) {
		this.path = path;
		this.name = name;
	}

	private String path;
	private String name;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

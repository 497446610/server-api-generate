/*
 * @(#)JavaGitProject.java        1.0 2018年3月19日
 *
 *
 */

package cn.kuangxf.doc.dao.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * java 文件信息.
 *
 * @version 1.0 2018年3月19日
 * @author kuangxf
 * @history
 * 
 */
@Entity
@Table(name = "java_file")
public class JavaFile {

	/**
	 * 文件ID
	 */
	@Id
	private String fileId;

	/**
	 * 项目代码
	 */
	private String code;

	/**
	 * 分支
	 */
	private String branch;

	/**
	 * 修改时间
	 */
	private Long modifyTime;

	/**
	 * 文件相对路径
	 */
	private String relativePath;

	/**
	 * 类名(包名+类名)
	 */
	private String className;
	
	/**
	 * 类名
	 */
	private String simpleName;
	
	/**
	 * 包名
	 */
	private String packageName;

	/**
	 * 版本号
	 */
	private Long version;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getSimpleName() {
		return simpleName;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}
	
	
	
	
}

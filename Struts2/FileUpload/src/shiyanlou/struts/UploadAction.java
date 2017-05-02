package shiyanlou.struts;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String uploader;
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	private String savePath;

	public String execute() throws Exception {

		String realpath = getSavePath();

		if (upload != null) {

			File savefile = new File(realpath, getUploadFileName());

			if (!savefile.getParentFile().exists()) {
				savefile.getParentFile().mkdirs();
			}

			FileUtils.copyFile(upload, savefile);
			ActionContext.getContext().put("message", "upload succeed!");
			return "success";
		}

		return "error";
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getSavePath() {

		return ServletActionContext.getServletContext().getRealPath(savePath);
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

}
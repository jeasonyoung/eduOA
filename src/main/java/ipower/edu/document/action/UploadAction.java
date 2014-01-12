package ipower.edu.document.action;

import ipower.edu.document.pageModel.DocumentAttachmentInfo;
import ipower.edu.document.service.IDocumentAttachmentService;
import ipower.utils.IOUtil;
import ipower.utils.MD5Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 * 附件上传ACTION。
 * @author 杨勇。
 * @since 2013-12-25。
 * */
public class UploadAction extends BaseAction  {
	private static final Logger logger = Logger.getLogger(UploadAction.class);
	private String storeFolder;
	private File attachment;
	private String attachmentContentType,attachmentFileName;
	private IDocumentAttachmentService documentAttachmentService;
	/**
	 * 构造函数。
	 * */
	public UploadAction(){
		this.storeFolder = "/upload_temp";
	}	
	/**
	 * 设置公文附件管理服务。
	 * @param documentAttachmentService
	 * 	公文附件管理服务。
	 * */
	public void setDocumentAttachmentService(IDocumentAttachmentService documentAttachmentService) {
		this.documentAttachmentService = documentAttachmentService;
	}
	/**
	 * 获取附件。
	 * @return 附件。
	 * */
	public File getAttachment() {
		return attachment;
	}
	/**
	 * 设置附件。
	 * @param attachment
	 * 	附件。
	 * */
	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}
	/**
	 * 获取附件类型。
	 * @return 附件类型。
	 * */
	public String getAttachmentContentType() {
		return attachmentContentType;
	}
	/**
	 * 设置附件类型。
	 * @param attachmentContentType
	 * 	附件类型。
	 * */
	public void setAttachmentContentType(String attachmentContentType) {
		this.attachmentContentType = attachmentContentType;
	}
	/**
	 * 获取附件文件名。
	 * @return 附件文件名。
	 * */
	public String getAttachmentFileName() {
		return attachmentFileName;
	}
	/**
	 * 设置附件文件名。
	 * @param attachmentFileName
	 * 	附件文件名。
	 * */
	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}
	/**
	 * 设置附件存储目录。
	 * @param storeFolder
	 * 	附件存储目录。
	 * */
	public void setStoreFolder(String storeFolder){
		this.storeFolder = storeFolder;
	}

	@Override
	public String execute() throws IOException{
		String folder = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "/";
		String root = ServletActionContext.getServletContext().getRealPath(this.storeFolder) + "/" + folder;
		File dir = new File(root);
		if(!dir.exists()){
			dir.mkdirs();
			if(logger.isDebugEnabled())logger.debug("创建目录："+ root);
		}
		logger.info("源附件名："+ this.getAttachmentFileName());
		logger.info("附件类型："+ this.getAttachmentContentType());
		DocumentAttachmentInfo callback = null;
		File srcfile = this.getAttachment();
		if(srcfile != null){
			String id = MD5Util.MD5(new FileInputStream(srcfile));
			String newFileName = id + IOUtil.getExtension(this.getAttachmentFileName());
			logger.info("附件存储名："+ newFileName);
			if(IOUtil.copyFile(srcfile, new File(root + newFileName), false)){
				callback = new DocumentAttachmentInfo(id, this.getAttachmentFileName(), folder + newFileName);
				if(this.documentAttachmentService != null) {
					callback = this.documentAttachmentService.update(callback);
				}
				logger.info("存储相对路径："+ callback.getPath());
			}
		}
		this.writeJson(callback);
		return null;
	}
}
package ipower.edu.document.action;

import ipower.edu.document.service.IProcessService;

/**
 * 修复重复ACTION。
 * @author 杨勇。
 * @since 2013-12-30。
 * */
public class RepeatAction extends BaseAction {
	private IProcessService processService;
	/**
	 * 设置流程服务接口。
	 * @param processService
	 * 	流程服务接口。
	 * */
	public void setProcessService(IProcessService processService){
		this.processService = processService;
	}
	/**
	 * 初始化流程。
	 * */
	public String initProcesses() throws Exception {
		this.processService.initProcesses();
		return "process";
	}
}
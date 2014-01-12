package ipower.edu.document.action;

import ipower.edu.document.pageModel.ProcessInstanceInfo;

/**
 * 流程实例ACTION。
 * @author 杨勇。
 * @since 2013-12-19。
 * */
public class ProcessInstanceAction extends BaseDataAction<ProcessInstanceInfo> {
	private ProcessInstanceInfo info = new ProcessInstanceInfo();
	
	@Override
	public ProcessInstanceInfo getModel() {
		return this.info;
	}

	@Override
	protected String deletePrimaryString() {
		return this.getModel().getId();
	}

}
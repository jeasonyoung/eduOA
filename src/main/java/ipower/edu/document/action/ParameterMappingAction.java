package ipower.edu.document.action;

import ipower.edu.document.pageModel.ParameterMappingInfo;

/**
 * 流程步骤变迁参数映射ACTION。
 * @author 杨勇。
 * @since 2013-12-19。 
 * */
public class ParameterMappingAction extends BaseDataAction<ParameterMappingInfo> {
	private ParameterMappingInfo info = new ParameterMappingInfo();
	@Override
	public ParameterMappingInfo getModel() {
		return info;
	}

	@Override
	protected String deletePrimaryString() {
		return this.getModel().getId();
	}

}
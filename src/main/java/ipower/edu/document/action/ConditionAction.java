package ipower.edu.document.action;

import ipower.edu.document.pageModel.ConditionInfo;

/**
 * 流程步骤变迁条件ACTION。
 * @author 杨勇。
 * @since 2013-12-21。
 * */
public class ConditionAction extends BaseDataAction<ConditionInfo> {
	private ConditionInfo info = new ConditionInfo();
	@Override
	public ConditionInfo getModel() {
		return this.info;
	}

	@Override
	protected String deletePrimaryString() {
		return this.info.getId();
	}
}
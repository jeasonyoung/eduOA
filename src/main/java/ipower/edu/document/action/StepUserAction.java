package ipower.edu.document.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import ipower.edu.document.domain.StepUser;
import ipower.edu.document.pageModel.StepUserInfo;
import ipower.edu.document.service.IStepUserService;

/**
 * 步骤用户Action。
 * @author 杨勇。
 * @since 2013-12-18。
 * */
public class StepUserAction extends BaseDataAction<StepUserInfo> {
	private StepUserInfo info = new StepUserInfo();
	@Override
	public StepUserInfo getModel() {
		return this.info;
	}

	@Override
	protected String deletePrimaryString() {
		return this.getModel().getId();
	}
	
	public void all() throws IOException{
		if(this.service instanceof IStepUserService){
			List<StepUserInfo> results = new ArrayList<>();
			List<StepUser> list = ((IStepUserService)this.service).loadStepUsers(this.getModel().getStepId());
			if(list != null && list.size() > 0){
				for(StepUser stepUser : list){
					StepUserInfo info = new StepUserInfo();
					BeanUtils.copyProperties(stepUser, info);
					results.add(info);
				}
			}
			this.writeJson(results);
		}
	}

}
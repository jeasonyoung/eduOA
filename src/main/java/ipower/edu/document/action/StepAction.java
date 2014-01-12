package ipower.edu.document.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import ipower.edu.document.domain.Step;
import ipower.edu.document.pageModel.StepInfo;
import ipower.edu.document.service.IStepService;

/**
 * 流程步骤Action
 * @author 杨勇。
 * @since 2013-12-15。
 * */
public class StepAction extends BaseDataAction<StepInfo> {
	private StepInfo info = new StepInfo();
	@Override
	public StepInfo getModel() {
		return this.info;
	}

	@Override
	protected String deletePrimaryString() {
		return this.getModel().getId();
	}

	public void all() throws IOException{
		if(this.service instanceof IStepService){
			List<StepInfo> results = new ArrayList<>();
			List<Step> steps = ((IStepService)this.service).allSteps(this.getModel().getProcessId(), this.getModel().getId());
			if(steps != null && steps.size() > 0){
				for(Step step : steps){
					StepInfo info = new StepInfo();
					BeanUtils.copyProperties(step, info);
					results.add(info);
				}
			}
			this.writeJson(results);
		}
	}
}
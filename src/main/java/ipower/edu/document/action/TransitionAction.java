package ipower.edu.document.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import ipower.edu.document.domain.Transition;
import ipower.edu.document.pageModel.TransitionInfo;
import ipower.edu.document.service.ITransitionService;

/**
 * 流程步骤变迁ACTION。
 * @author 杨勇。
 * @since 2013-12-19。 
 * */
public class TransitionAction extends BaseDataAction<TransitionInfo> {
	private TransitionInfo info = new TransitionInfo();
	@Override
	public TransitionInfo getModel() {
		return this.info;
	}

	@Override
	protected String deletePrimaryString() {
		return this.getModel().getId();
	}
	
	public void all() throws IOException{
		if(this.service instanceof ITransitionService){
			List<TransitionInfo> results = new ArrayList<>();
			List<Transition> list = ((ITransitionService)this.service).loadTransitions(this.getModel().getProcessId());
			if(list != null && list.size() > 0){
				for(Transition transition : list){
					TransitionInfo info = new TransitionInfo();
					BeanUtils.copyProperties(transition, info);
					if(transition.getProcess() != null){
						info.setProcessId(transition.getProcess().getId());
						info.setProcessName(transition.getProcess().getName());
					}
					if(transition.getFrom() != null){
						info.setFromStepId(transition.getFrom().getId());
						info.setFromStepName(transition.getFrom().getName());
					}
					if(transition.getTo() != null){
						info.setToStepId(transition.getTo().getId());
						info.setToStepName(transition.getTo().getName());
					}
					results.add(info);
				}
			}
			this.writeJson(results);
		}
	}
}
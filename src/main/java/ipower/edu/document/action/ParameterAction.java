package ipower.edu.document.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import ipower.edu.document.domain.Parameter;
import ipower.edu.document.pageModel.ParameterInfo;
import ipower.edu.document.service.IParameterService;

/**
 * 流程步骤参数Action
 * @author 杨勇。
 * @since 2013-12-17。
 * */
public class ParameterAction extends BaseDataAction<ParameterInfo> {
	private ParameterInfo info = new ParameterInfo();
	@Override
	public ParameterInfo getModel() {
		return this.info;
	}

	@Override
	protected String deletePrimaryString() {
		return this.getModel().getId();
	}

	public void all() throws IOException{
		if(this.service instanceof IParameterService){
			List<ParameterInfo> results = new ArrayList<>();
			List<Parameter> parameters = ((IParameterService)this.service).allParameters(this.getModel().getStepId());
			 if(parameters != null && parameters.size() > 0){
				 for(Parameter parameter : parameters){
					 ParameterInfo info = new ParameterInfo();
					 BeanUtils.copyProperties(parameter,info);
					 results.add(info);
				 }
			 }
			this.writeJson(results);
		}
	}
}
package ipower.edu.document.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import ipower.edu.document.domain.Process;
import ipower.edu.document.pageModel.ProcessInfo;
import ipower.edu.document.service.IProcessService;

/**
 * 流程Action。
 * @author 杨勇。
 * @since 2013-12-14。
 * */
public class ProcessAction extends BaseDataAction<ProcessInfo> {
	private ProcessInfo info = new ProcessInfo();
	
	@Override
	public ProcessInfo getModel() {
		return this.info;
	}

	@Override
	protected String deletePrimaryString() {
		return this.info.getId();
	}
	
	public void all() throws IOException{
		if(this.service instanceof IProcessService){
			List<ProcessInfo> results = new ArrayList<>();
			List<Process> list = ((IProcessService)this.service).allProcesses();
			if(list != null && list.size() > 0){
				for(Process process : list){
					ProcessInfo info = new ProcessInfo();
					BeanUtils.copyProperties(process, info);
					results.add(info);
				}
			}
			this.writeJson(results);
		}
	}
}
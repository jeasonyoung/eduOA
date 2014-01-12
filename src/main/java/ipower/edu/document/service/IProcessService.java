package ipower.edu.document.service;

import java.util.List;

import ipower.edu.document.dao.IProcessDao;
import ipower.edu.document.domain.Process;
import ipower.edu.document.pageModel.ProcessInfo;

/**
 * 流程服务接口。
 * */
public interface IProcessService extends IDataServiceBase<ProcessInfo> {
	/**
	 * 设置流程数据访问接口。
	 * @param processDao
	 * 	流程数据访问接口。
	 * */
	void setProcessDao(IProcessDao processDao);
	/**
	 * 加载流程数据。
	 * @param processId
	 * 	流程ID。
	 * */
	Process loadProcess(String processId);
	/**
	 * 加载全部流程数据。
	 * @return 流程数据
	 * */
	List<Process> allProcesses();
	/**
	 * 初始化流程。
	 * */
	void initProcesses() throws Exception;
}
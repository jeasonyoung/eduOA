package ipower.edu.document.domain;

import java.io.Serializable;

/**
 * 步骤上的用户。
 * @author 杨勇。
 * @since 2013-12-11。
 * */
public class StepUser implements Serializable{
	private static final long serialVersionUID = 1L;
	private Step step;
	private String userId,userName;
	private Integer type;
	/**
	 * 获取步骤。
	 * @return 步骤。
	 * */
	public Step getStep() {
		return step;
	}
	/**
	 * 设置步骤。
	 * @param step
	 * 	步骤。
	 * */
	public void setStep(Step step) {
		this.step = step;
	}
	/**
	 * 获取用户ID。
	 * @return 用户ID。
	 * */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置用户ID。
	 * @param userId
	 * 	用户ID。
	 * */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取用户名称。
	 * @return 用户名称。
	 * */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置用户名称。
	 * @param userName
	 * 	用户名称。
	 * */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取类型。
	 * @return 类型(1-待办，2-待阅)。
	 * */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置类型。
	 * @param type
	 * 	类型。
	 * */
	public void setType(Integer type) {
		this.type = type;
	}
	
	@Override
	public int hashCode(){
		int result = super.hashCode() + 32;
		if(this.userId != null){
			result *= this.userId.hashCode();
		}
		if(this.step != null){
			result *= this.step.hashCode();
			if(this.step.getId() != null) result *=  this.step.getId().hashCode();
		}
		return result;
	}
	@Override
	public boolean equals(Object obj){
		if(this == obj) return true;
		if(obj == null) return false;
		if(this.getClass() != obj.getClass()) return false;
		StepUser stepUser = (StepUser)obj;
		if(this.getUserId() == null && stepUser.getUserId() != null)
			return false;
		if(!this.getUserId().equalsIgnoreCase(stepUser.getUserId()))
			return false;
		if(this.getStep() == null && stepUser.getStep() != null)
			return false;
		return this.getStep().getId().equalsIgnoreCase(stepUser.getStep().getId());
	}
}
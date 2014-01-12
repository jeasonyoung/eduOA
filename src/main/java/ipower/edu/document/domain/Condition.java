package ipower.edu.document.domain;

import java.io.Serializable;

/**
 * 变迁条件。
 * @author 杨勇。
 * @since 2013-12-11。
 * */
public class Condition implements Serializable {
	private static final long serialVersionUID = 1L;
	private Transition transition;
	private Parameter parameter;
	private String compareValue;
	/**
	 * 获取所属步骤变迁。
	 * @return 所属步骤变迁。
	 * */	
	public Transition getTransition() {
		return transition;
	}
	/**
	 * 设置所属步骤变迁。
	 * @param transition
	 * 	所属步骤变迁。
	 * */
	public void setTransition(Transition transition) {
		this.transition = transition;
	}
	/**
	 * 获取参数。
	 * @return 获取参数。
	 * */
	public Parameter getParameter() {
		return parameter;
	}
	/**
	 * 设置参数。
	 * @param parameter
	 * 	参数。
	 * */
	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}
	/**
	 * 获取比较值。
	 * @return 比较值(包括运算符)。
	 * */
	public String getCompareValue() {
		return compareValue;
	}
	/**
	 * 设置比较值。
	 * @param compareValue
	 * 	比较值(包括运算符)。
	 * */
	public void setCompareValue(String compareValue) {
		this.compareValue = compareValue;
	}
	
	@Override
	public int hashCode(){
		int result = super.hashCode() + 32;
		if(this.transition != null){
			result *= this.transition.hashCode() + (this.transition.getId() == null ? 0 : this.transition.getId().hashCode());			
		}
		if(this.parameter != null){
			result *= this.parameter.hashCode() + (this.parameter.getId() == null ? 0 : this.parameter.getId().hashCode());
		}
		 
		return result;
	}
	@Override
	public boolean equals(Object obj){
		if(this == obj) return true;
		if(obj == null) return false;
		if(this.getClass() != obj.getClass()) return false;
		Condition condition = (Condition)obj;
		
		if(this.transition == null && condition.getTransition() != null)
			return false;
		if(!this.transition.getId().equalsIgnoreCase(condition.getTransition().getId()))
			return false;
		
		if(this.parameter == null && condition.getParameter() != null)
			return false;
		if(!this.parameter.getId().equalsIgnoreCase(condition.getParameter().getId()))
			return false;
		 
		if(this.compareValue == null && condition.getCompareValue() != null)
			return false;
		return this.compareValue.equalsIgnoreCase(condition.getCompareValue());
	}
}
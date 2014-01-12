package ipower.edu.document.domain;

import java.io.Serializable;

/**
 * 前驱与后驱参数映射。
 * @author 杨勇。
 * @since 2013-12-11。
 * */
public class ParameterMapping implements Serializable {
	private static final long serialVersionUID = 1L;
	private Transition transition;
	private Parameter from, to;
	/**
	 * 构造函数。
	 * */
	public ParameterMapping(){}
	/**
	 * 构造函数。
	 * @param transition
	 * 	变迁对象。
	 * @param from
	 *  前驱参数。
	 * @param to
	 *  后驱参数。
	 * */
	public ParameterMapping(Transition transition,Parameter from,Parameter to){
		this.setTransition(transition);
		this.setFrom(from);
		this.setTo(to);
	}
	/**
	 * 获取步骤变迁。
	 * @return 步骤变迁。
	 * */
	public Transition getTransition() {
		return transition;
	}
	/**
	 * 设置步骤变迁。
	 * @param transition
	 * 	步骤变迁。
	 * */
	public void setTransition(Transition transition) {
		this.transition = transition;
	}
	/**
	 * 获取前驱参数。
	 * @return 前驱参数。
	 * */
	public Parameter getFrom() {
		return from;
	}
	/**
	 * 设置前驱参数。
	 * @param from
	 * 	前驱参数。
	 * */
	public void setFrom(Parameter from) {
		this.from = from;
	}
	/**
	 * 获取后驱参数。
	 * @return 后驱参数。
	 * */
	public Parameter getTo() {
		return to;
	}
	/**
	 * 设置后驱参数。
	 * @param 后驱参数。
	 * */
	public void setTo(Parameter to) {
		this.to = to;
	}
	
	@Override
	public int hashCode(){
		int result = super.hashCode() + 32;
		if(this.transition != null){
			result *= this.transition.hashCode() + (this.transition.getId() == null ? 0 : this.transition.getId().hashCode());			
		}
		if(this.from != null){
			result *= this.from.hashCode() + (this.from.getId() == null ? 0 : this.from.getId().hashCode());
		}
		if(this.to != null){
			result *= this.to.hashCode() + (this.to.getId() == null ? 0 : this.to.getId().hashCode());
		}
		return result;
	}
	@Override
	public boolean equals(Object obj){
		if(this == obj) return true;
		if(obj == null) return false;
		if(this.getClass() != obj.getClass()) return false;
		ParameterMapping mapping = (ParameterMapping)obj;
		
		if(this.transition == null && mapping.getTransition() != null)
			return false;
		if(!this.transition.getId().equalsIgnoreCase(mapping.getTransition().getId()))
			return false;
		
		if(this.from == null && mapping.getFrom() != null)
			return false;
		if(!this.from.getId().equalsIgnoreCase(mapping.getFrom().getId()))
			return false;
		
		if(this.to == null && mapping.getTo() != null)
			return false;
		
		return this.to.getId().equalsIgnoreCase(mapping.getTo().getId());
	}
}
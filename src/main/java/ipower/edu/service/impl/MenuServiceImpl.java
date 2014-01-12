package ipower.edu.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ClassUtils;
import org.xml.sax.SAXException;

import ipower.configuration.ModuleDefine;
import ipower.configuration.ModuleSystem;
import ipower.configuration.ModuleSystemCollection;
import ipower.edu.service.IMenuService;
import ipower.pageModel.TreeNode;

/**
 * 菜单服务实现类。
 * @author 杨勇.
 * @since 2013-11-27.
 * */
public class MenuServiceImpl implements IMenuService {
	private static Logger logger = Logger.getLogger(MenuServiceImpl.class);
	private static Map<String, ModuleSystem> mapModuleSystemCache = Collections.synchronizedMap(new HashMap<String, ModuleSystem>());
	private static Map<String, List<TreeNode>> mapNodeCache = Collections.synchronizedMap(new HashMap<String,List<TreeNode>>());
	private static Map<String, ModuleDefine> mapModuleDefineCache = Collections.synchronizedMap(new HashMap<String,ModuleDefine>());
	private ModuleSystemCollection collection;
	/**
	 * 设置菜单配置文件。
	 * @param menuFile
	 * 	菜单文件路径。
	 * */
	@Override
	public void setMenuFile(String menuFile) {
		if(menuFile != null && !menuFile.isEmpty()){
			try {
				Resource rs = new ClassPathResource(menuFile, ClassUtils.getDefaultClassLoader());
				if(rs != null){
					this.collection = ModuleSystemCollection.parse(rs.getInputStream());
				}
			} catch (IOException e) {
				logger.error(e);
				e.printStackTrace();
			} catch (SAXException e) {
				logger.error(e);
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				logger.error(e);
				e.printStackTrace();
			}
		}
	}
	/**
	 * 根据系统ID获取系统信息。
	 * @param systemId
	 * 	系统ID。
	 * @return 系统信息。
	 * */
	@Override
	public synchronized ModuleSystem loadModuleSystem(String systemId) {
		if(systemId == null || systemId.isEmpty()) return null;
		ModuleSystem ms = mapModuleSystemCache.get(systemId);
		if(ms == null && this.collection != null){ 
				ms = this.collection.item(systemId);
				if(ms != null) mapModuleSystemCache.put(systemId, ms);
		}
		return ms;
	}
	/**
	 * 生成菜单数据。
	 * @param systemId
	 * 	系统ID。
	 * */
	@Override
	public synchronized List<TreeNode> tree(String systemId) {
		List<TreeNode> results = null;
		if(systemId == null || systemId.trim().isEmpty()){
			if(this.collection == null || this.collection.size() == 0) return results;
			//全部系统。
			results = new ArrayList<>();
			for(ModuleSystem ms : this.collection){
				if(ms == null)continue;
				TreeNode node = new TreeNode();
				node.setId(ms.getId());
				node.setText(ms.getName());
				node.setChildren(this.createModuleDefines(ms.getId()));
				results.add(node);
			}
		} else {
			//指定系统ID。
			results = this.createModuleDefines(systemId);
		}
		return results;
	}
	private List<TreeNode> createModuleDefines(String systemId){
		if(systemId == null || systemId.trim().isEmpty()) return null;
		List<TreeNode> results = mapNodeCache.get(systemId);
		if(results == null || results.size() == 0){
			ModuleSystem ms = this.loadModuleSystem(systemId);
			if(ms != null && ms.getModules() != null){
				results = new ArrayList<>();
				for(ModuleDefine m : ms.getModules()){
					TreeNode n = this.createTree(m);
					if(n != null)results.add(n);
				}
				mapNodeCache.put(systemId, results);
			}
		}
		return results;
	}
	/**
	 * 递归生成菜单节点。
	 * @param m
	 * 	菜单
	 * @return 菜单节点。
	 * */
	private TreeNode createTree(ModuleDefine m){
		if(m != null){
			//缓存菜单。
			if(!mapModuleDefineCache.containsKey(m.getModuleID())){
				mapModuleDefineCache.put(m.getModuleID(), m);
			}
			//生成菜单树。
			TreeNode node = new TreeNode();
			node.setId(m.getModuleID());
			node.setText(m.getModuleName());
			Map<String,Object> attributes = new HashMap<String,Object>();
			attributes.put("url", m.getModuleUri());
			node.setAttributes(attributes);
			if(m.getModules() != null && m.getModules().size() > 0){
				List<TreeNode> children = new ArrayList<TreeNode>();
				for(ModuleDefine d: m.getModules()){
					TreeNode tn = this.createTree(d);
					if(tn != null){
						children.add(tn);
					}
				}
				if(children.size() > 0){
					node.setChildren(children);
				}
			}
			return node;
		}
		return null;
	}
	@Override
	public String loadMenuName(String menuId) {
		if(menuId == null || menuId.trim().isEmpty()) return null;
		ModuleDefine m = mapModuleDefineCache.get(menuId);
		if(m == null && this.collection != null && this.collection.size() > 0){
			for(ModuleSystem ms : this.collection){
				m = this.findModuleDefine(ms,menuId);
				if(m != null) break;
			}
		}
		return (m == null) ? null : m.getModuleName();
	}
	private ModuleDefine findModuleDefine(ModuleSystem ms,String menuId){
		if(ms == null) return null;
		if(!mapModuleSystemCache.containsKey(ms.getId()))
			mapModuleSystemCache.put(ms.getId(), ms);
		if(ms.getModules() == null|| ms.getModules().size() == 0) return null;
		for(ModuleDefine m : ms.getModules()){
			ModuleDefine define = this.findModuleDefine(m, menuId);
			if(define != null) return define;
		}
		return null;
	}
	private ModuleDefine findModuleDefine(ModuleDefine m,String menuId){
		if(m == null || m.getModuleID() == null || m.getModuleID().trim().isEmpty())return null;
		if(m.getModuleID().equalsIgnoreCase(menuId)) return m;
		if(m.getModules() != null && m.getModules().size() > 0){
			for(ModuleDefine define : m.getModules()){
				ModuleDefine md = this.findModuleDefine(define, menuId);
				if(md != null) return md;
			}
		}
		return null;
	}
}
package com.huangyiming.disjob.register.auth.node;

import java.util.ArrayList;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.ACL;

import com.huangyiming.disjob.register.auth.AuthConstants;
import com.huangyiming.disjob.register.auth.EjobOwnerACL;
import com.huangyiming.disjob.register.auth.EjobReaderACL;
import com.huangyiming.disjob.register.repository.ZnodeApi;
import com.huangyiming.disjob.register.repository.ZnodeApiCuratorImpl;

/**
 * /ejob/auth/group 下节点的创建 , 对应ejob/job/oms1 的节点权限数据
 * 在jobgroup新增时使用
 * @author chengangxiong
 *
 */
public class GroupAuthNode{

	private String groupname;
	private String ownerPath;
	private String readerPath;
	private CuratorFramework client;
	private ZnodeApi znode = new ZnodeApiCuratorImpl();
	
	private String owner;
	private String reader;
	
	
	public GroupAuthNode(CuratorFramework client, String groupname){
		this.groupname = groupname;
		this.client = client;
		init();
	}
	
	private void init(){
		this.ownerPath = AuthConstants.groupRootPath + "/" + groupname + "/owner";
		this.readerPath = AuthConstants.groupRootPath + "/" + groupname + "/reader";
		this.owner = groupname + AuthConstants.COLON_OWNER + System.currentTimeMillis();
		this.reader = groupname + AuthConstants.COLON_READER + System.currentTimeMillis();
		
	}
	
	/**
	 * 创建auth下group节点时,创建节点的权限并存储, 携带global权限
	 */
	public List<ACL> createACLs(){
		List<ACL> acllist = new ArrayList<>();
		acllist.add(new EjobOwnerACL(owner));
		acllist.add(new EjobReaderACL(reader));
		acllist.addAll(new GlobalAuthNode(client).getACLs());
		
		znode.create(client, ownerPath, owner);
		znode.create(client, readerPath, reader);
		return acllist;
	}
	
	public static List<String> getAuthAvailableJobGroup(CuratorFramework client){
		return new ZnodeApiCuratorImpl().getChildren(client, AuthConstants.groupRootPath);
	}
}
